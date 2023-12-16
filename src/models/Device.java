package models;

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

    public void setId(String id) {
        this.id = id;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
    }

    public int getWeight() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }
}
