package com.ajiranet.services;

import com.ajiranet.dao.Dao;
import com.ajiranet.dao.DaoImpl;
import com.ajiranet.exception.*;
import com.ajiranet.models.Device;
import com.ajiranet.models.DeviceType;

import java.util.*;

public class AjiraNetService {

    Dao dao;

    public AjiraNetService() {
        dao = new DaoImpl();
    }

    public void addDevice(DeviceType deviceType, String deviceId) throws DeviceAlreadyExist {

        Device device = new Device(deviceId, deviceType);
        if (!dao.doesDeviceExist(deviceId)) {
            dao.addDevice(device);
            System.out.println("Device get added Successfully");
        } else {
            throw new DeviceAlreadyExist(deviceId);
        }
    }

    public void addConnection(String id1, String id2) throws InvalidArgumentException, DeviceNotExist {

        if (id1.equals(id2)) {
            throw new InvalidArgumentException("device cannot be connected to itself.");
        }

        if (dao.doesDeviceExist(id1)) {
            if (dao.doesDeviceExist(id2)) {
                Device deviceA = dao.getDevice(id1);
                Device deviceB = dao.getDevice(id2);
                if (!dao.doesConnectionExist(deviceA, deviceB)) {
                    dao.addConnection(deviceA, deviceB);
                    System.out.println("Connection added between " + id1 + " and " + id2);
                } else {
                    System.out.println("Connection exist between " + id1 + " and " + id2 + " exists");
                }
            } else {
                throw new DeviceNotExist(id2);
            }
        } else {
            throw new DeviceNotExist(id1);
        }
    }

    public void setDeviceStrength(String deviceId, int strength) throws InvalidStrengthException, InvalidArgumentException, DeviceNotExist {

        if (strength < 0) {
            throw new InvalidStrengthException("Device Strength can not be negative");
        }

        if (dao.doesDeviceExist(deviceId)) {
            if (dao.isRepeater(deviceId)) {
                throw new InvalidArgumentException("device with id " + deviceId + " ia a repeater and hence, Strength can not be added");
            } else {
                dao.setDeviceStrength(deviceId, strength);
                System.out.println("Strength of device " + deviceId + " is set to " + strength);
            }
        } else {
            throw new DeviceNotExist(deviceId);
        }
    }

    private void dfs(Device src, Map<Device, Boolean> visited, List<Device> path, int strength, Device dest, List<Device> route) {

        if(strength<0){
            return;
        }
        if (src.equals(dest)) {
            path.add(dest);
            if (route.size() == 0 || route.size() > path.size()) {
                route.clear();
                route.addAll(path);
            }
            path.remove(dest);
            return;
        }

        visited.put(src, true);

        path.add(src);

        List<Device> devices = src.getDevices();
        for (Device device : devices) {

            if (!visited.get(device) && !path.contains(device)) {
                if (dao.isRepeater(device.getId())) {
                    dfs(device, visited, path, (strength) * 2, dest, route);
                } else {
                    dfs(device, visited, path, strength - 1, dest, route);
                }
            }
        }
        visited.put(src, false);
        path.remove(src);
    }

    public void getRouteInfo(String id1, String id2) throws DeviceNotExist, InvalidArgumentException, RouteNotExist {

        if (!dao.doesDeviceExist(id1)){

            throw new DeviceNotExist(id1);

        }
        if(!dao.doesDeviceExist(id2)) {
            throw new DeviceNotExist(id2);
        }

        if (dao.isRepeater(id1) || dao.isRepeater(id2)){
            throw new InvalidArgumentException("route can't be calculated with repeater.");

        }

        Device src = dao.getDevice(id1);
        Device dest = dao.getDevice(id2);

        Collection<Device> devices = dao.getAllDevices();

        Map<Device, Boolean> visited = new HashMap<>();

        for (Device device : devices) {
            visited.put(device, false);
        }

        List<Device> path = new ArrayList<>();
        List<Device> route = new ArrayList<>();

        int strength = src.getStrength();

        if (src.equals(dest)) {
            route.add(src);
            route.add(dest);
        } else {
            dfs(src, visited, path, strength, dest, route);
        }

        if (route.size() == 0) {
            throw new RouteNotExist();
        }
        int routeSize = route.size();
        System.out.print("Route from " + id1 + " to " + id2 + " is: ");
        int cnt=0;
        for (Device device : route) {
            cnt++;
            if (cnt == routeSize) {
                System.out.print(device.getId());
            } else {

                System.out.print(device.getId() + " -> ");
            }
        }

        System.out.println("  ");

    }
}
