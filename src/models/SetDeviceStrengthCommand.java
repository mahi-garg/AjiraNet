package models;

public class SetDeviceStrengthCommand extends Command{
    String deviceId;
    int strength;

    public SetDeviceStrengthCommand(String deviceId, int strength) {
        super(CommandType.SET_DEVICE_STRENGTH);
        this.deviceId = deviceId;
        this.strength = strength;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }
}
