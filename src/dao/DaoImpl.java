package dao;

import db.InMemoryDataBase;
import models.Device;
import models.DeviceType;

import java.util.Collection;
import java.util.List;

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
        Collection<Device> devices = db.getDb().values();
        return devices;
    }

    @Override
    public Device getDevice(String deviceId){
        return db.getDb().get(deviceId);
    }

    public Boolean doesConnectionExist(Device device1, Device device2){

        String id1 = device1.getId();
        String id2 = device2.getId();
        return db.getDb().get(id1).getDevices().contains(device2);
    }
    @Override
    public void addConnection(Device device1, Device device2) {
        String id1 = device1.getId();
        String id2 = device2.getId();
        db.getDb().get(id1).getDevices().add(device2);
        db.getDb().get(id2).getDevices().add(device1);
    }

    @Override
    public void setDeviceStrength(String deviceId,  int strength){
        db.getDb().get(deviceId).setStrength(strength);
    }

    @Override
    public Boolean isRepeater(String deviceId) {
        return db.getDb().get(deviceId).getDeviceType().equals(DeviceType.Repeater);
    }

//    @Override
//    public List<Device> getRouteInfo(Device device1, Device device2) {
//        return null;
//    }
}
