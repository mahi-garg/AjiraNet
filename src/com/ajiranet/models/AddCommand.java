package com.ajiranet.models;

public class AddCommand extends Command {
    DeviceType deviceType;
    String deviceId;

    public AddCommand(DeviceType deviceType, String deviceId) {
        super(CommandType.ADD);
        this.deviceType = deviceType;
        this.deviceId = deviceId;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public String getDeviceId() {
        return deviceId;
    }
}
