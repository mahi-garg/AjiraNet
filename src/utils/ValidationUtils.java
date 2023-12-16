package utils;
import exception.InvalidArgumentCountException;
import exception.InvalidCommandException;
import exception.InvalidDeviceException;
import exception.InvalidStrengthException;
import models.*;


public class ValidationUtils {
    public static Command validateAndGet(String commandStr) throws InvalidArgumentCountException, InvalidCommandException, InvalidDeviceException, InvalidStrengthException {
        String[] tokens = commandStr.split(" ");
        String commandTypeStr = tokens[0].toUpperCase();
        if (tokens.length != 3) {
            throw new InvalidArgumentCountException("Number of arguments given to Add statement are not 3!");
        }
        CommandType commandType = getCommandType(commandTypeStr);
        switch (commandType) {
            case ADD:
                DeviceType deviceType = getDeviceType(tokens[1]);
                String id = tokens[2];
                return new AddCommand(deviceType, id);
            case CONNECT:
                return new ConnectCommand(tokens[1], tokens[2]);
            case INFO_ROUTE:
                return new InfoRouteCommand(tokens[1], tokens[2]);
            case SET_DEVICE_STRENGTH:
                return new SetDeviceStrengthCommand(tokens[1], getIntStrength(tokens[2]));
            default:
                throw new InvalidCommandException("Invalid command " + commandTypeStr);
        }
    }

    private static int getIntStrength(String token) throws InvalidStrengthException {
        try {
            return Integer.parseInt(token);
        } catch (Exception e) {
            throw new InvalidStrengthException(token + " is not valid strength");
        }
    }

    private static CommandType getCommandType(String commandTypeStr) throws InvalidCommandException {
        try {
            return CommandType.valueOf(commandTypeStr);
        } catch (Exception e) {
            throw new InvalidCommandException(commandTypeStr + " doesn't exist!");
        }
    }

    private static DeviceType getDeviceType(String token) throws InvalidDeviceException {
        try {
            return DeviceType.valueOf(token);
        } catch (Exception e) {
            throw new InvalidDeviceException(token + " doesn't exist!");
        }
    }
}
