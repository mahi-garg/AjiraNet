package com.ajiranet.models;

import java.util.ArrayList;
import java.util.List;

public class Device {

    String id;
    DeviceType deviceType;

    int strength = 5;

    List<Device> devices;

    public Device(String id, DeviceType deviceType) {
        this.id = id;
        this.deviceType = deviceType;
        this.devices = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public List<Device> getDevices() {
        return devices;
    }

}
