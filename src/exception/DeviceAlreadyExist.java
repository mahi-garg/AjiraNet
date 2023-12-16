package exception;

public class DeviceAlreadyExist extends Exception{
    public DeviceAlreadyExist(String deviceId) {
        super("DeviceId " + deviceId + " already exist.");
    }
}
