package com.ajiranet.models;

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

    public int getStrength() {
        return strength;
    }
}
