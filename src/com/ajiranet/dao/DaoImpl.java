package com.ajiranet.dao;
import com.ajiranet.db.InMemoryDataBase;
import com.ajiranet.models.Device;
import com.ajiranet.models.DeviceType;
import java.util.Collection;
public class DaoImpl implements Dao{
    public InMemoryDataBase db = InMemoryDataBase.getInstance();
    public Boolean doesDeviceExist(String deviceId){
        return db.getDb().containsKey(deviceId);
    }
    @Override
    public void addDevice(Device device) {
        String deviceId = device.getId();
        db.getDb().put(deviceId, device);
    }
    @Override
    public Collection<Device> getAllDevices() {
        return  db.getDb().values();
    }
    @Override
    public Device getDevice(String deviceId){
        return db.getDb().get(deviceId);
    }
    public Boolean doesConnectionExist(Device deviceA, Device deviceB){
        String id1 = deviceA.getId();
        return db.getDb().get(id1).getDevices().contains(deviceB);
    }
    @Override
    public void addConnection(Device deviceA, Device deviceB) {
        String id1 = deviceA.getId();
        String id2 = deviceB.getId();
        db.getDb().get(id1).getDevices().add(deviceB);
        db.getDb().get(id2).getDevices().add(deviceA);
    }
    @Override
    public void setDeviceStrength(String deviceId,  int strength){
        db.getDb().get(deviceId).setStrength(strength);
    }
    @Override
    public Boolean isRepeater(String deviceId) {
        return db.getDb().get(deviceId).getDeviceType().equals(DeviceType.REPEATER);
    }
}
