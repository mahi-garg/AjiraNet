package com.ajiranet.utils;
import com.ajiranet.exception.InvalidArgumentCountException;
import com.ajiranet.exception.InvalidCommandException;
import com.ajiranet.exception.InvalidDeviceException;
import com.ajiranet.exception.InvalidStrengthException;
import com.ajiranet.models.*;

public class ValidationUtils {

    private static void validateCommandLength(int length, int requiredLength, CommandType commandType) throws InvalidArgumentCountException {
        if (length != requiredLength) {
            throw new InvalidArgumentCountException("Invalid arguments given to " + commandType  + ". Required length is " + requiredLength + "!");
        }
    }
    public static Command validateAndGet(String commandStr) throws InvalidArgumentCountException, InvalidCommandException, InvalidDeviceException, InvalidStrengthException {
        String[] tokens = commandStr.split(" ");
        if(tokens.length == 0){
            throw new InvalidCommandException("command is null");
        }

        String commandTypeStr = tokens[0].toUpperCase();
        CommandType commandType = getCommandType(commandTypeStr);

        switch (commandType) {
            case ADD -> {
                validateCommandLength(tokens.length, 3, CommandType.ADD);
                DeviceType deviceType = getDeviceType(tokens[1]);
                String id = tokens[2];
                return new AddCommand(deviceType, id);
            }
            case CONNECT -> {
                validateCommandLength(tokens.length, 3, CommandType.CONNECT);
                return new ConnectCommand(tokens[1], tokens[2]);
            }
            case INFO_ROUTE -> {
                validateCommandLength(tokens.length, 3, CommandType.INFO_ROUTE);
                return new InfoRouteCommand(tokens[1], tokens[2]);
            }
            case SET_DEVICE_STRENGTH -> {
                validateCommandLength(tokens.length, 3, CommandType.SET_DEVICE_STRENGTH);
                return new SetDeviceStrengthCommand(tokens[1], getIntStrength(tokens[2]));
            }
            case EXIT -> {
                validateCommandLength(tokens.length, 1, CommandType.EXIT);
                return new ExitCommand();
            }
            default -> throw new InvalidCommandException("Invalid command " + commandTypeStr);
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
