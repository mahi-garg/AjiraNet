package exception;

public class DeviceNotExist extends Exception{
    public DeviceNotExist(String deviceId) {
        super("device with id " + deviceId + " does not exist");
    }
}
