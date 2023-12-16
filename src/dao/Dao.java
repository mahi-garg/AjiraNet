package dao;

import models.Device;

import java.util.Collection;
import java.util.List;

public interface Dao {

    public Boolean doesDeviceExist(String deviceId);

    public void addDevice(Device device);

    public Collection<Device> getAllDevices();
    public Device getDevice(String deviceId);

    public Boolean doesConnectionExist(Device deviceA, Device deviceB);

    public void addConnection(Device deviceA, Device deviceB);

    public void setDeviceStrength(String deviceId,  int strength);

    public Boolean isRepeater(String deviceId);

}
