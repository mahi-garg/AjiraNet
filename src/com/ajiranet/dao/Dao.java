package com.ajiranet.dao;

import com.ajiranet.models.Device;

import java.util.Collection;

public interface Dao {
    Boolean doesDeviceExist(String deviceId);
    void addDevice(Device device);
    Collection<Device> getAllDevices();
    Device getDevice(String deviceId);
    Boolean doesConnectionExist(Device deviceA, Device deviceB);
    void addConnection(Device deviceA, Device deviceB);
    void setDeviceStrength(String deviceId,  int strength);
    Boolean isRepeater(String deviceId);
}
