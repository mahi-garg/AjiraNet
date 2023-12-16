package services;
import dao.Dao;
import dao.DaoImpl;
import models.Device;
import models.DeviceType;

import java.util.Collection;
import java.util.List;

public class AjiraNetService {

    Dao dao;

    public AjiraNetService(){
        dao = new DaoImpl();
    }
    public void addDevice(DeviceType deviceType, String deviceId){

        Device device = new Device(deviceId, deviceType);
        if(!dao.doesDeviceExist(deviceId)){
            dao.addDevice(device);
            System.out.println("Device get added Successfully");
        }
        else{
            System.out.println("deviceId " + deviceId  + " already exist");
        }
    }

    public void getAllDevices(){
        Collection<Device> devices= dao.getAllDevices();

        for( Device device: devices){
            System.out.println(device.getId() + " " + device.getDeviceType());
        }
    }

    public void addConnection(String id1, String id2){

        if(id1 == id2){
            System.out.println("device cannot be connected to itself.");
            return;
        }

        if(dao.doesDeviceExist(id1)){
            if(dao.doesDeviceExist(id2)){
                Device device1 = dao.getDevice(id1);
                Device device2 = dao.getDevice(id2);
                if(!dao.doesConnectionExist(device1, device2)) {
                    dao.addConnection(device1, device2);
                    System.out.println("Connection added between " + id1 + " and " + id2 );
                }
                else{
                    System.out.println("Connection exist between " + id1 + " and " + id2 + " exists");
                }
            }
            else{
                System.out.println("device with id " + id2 + " does not exist");
            }
        }
        else{
            System.out.println("device with id" + id1 + " does not exist");
        }
    }

    public void setDeviceStrength(String deviceId,  int strength){

        if(strength < 0){
            System.out.println("Device Strength can not be negative");
        }

        if(dao.doesDeviceExist(deviceId)){
            if(dao.isRepeater(deviceId)){
                System.out.println("device with id " + deviceId + " ia a repeater and hence, Strength can not be added");
            }
            else {
                dao.setDeviceStrength(deviceId, strength);
                System.out.println("Strength of device " + deviceId + " is set to " + strength);
            }
        }
        else{
            System.out.println("device with id " + deviceId + " does not exist");
        }
    }

    public void getRouteInfo( String id1, String id2){

        if(!dao.doesDeviceExist(id1) || !dao.doesDeviceExist(id2)){
            System.out.println("either source or the destination device does not exist");
            return;
        }

        if(dao.isRepeater(id1) || dao.isRepeater(id2)){
            System.out.println("source or the destination device cannot be a repeater.");
            return;
        }
        Device device1 = dao.getDevice(id1);
        Device device2 = dao.getDevice(id2);

        Collection<Device> devices = dao.getAllDevices();

        List<String> visited;


//        markallVisitedFalse();
//        List<String> list;
//        dfs(device, list, visited);
    }
}
