package com.ajiranet.models;

public class InfoRouteCommand extends Command{
    String deviceA;
    String deviceB;

    public InfoRouteCommand(String deviceA, String deviceB) {
        super(CommandType.INFO_ROUTE);
        this.deviceA = deviceA;
        this.deviceB = deviceB;
    }

    public String getDeviceA() {
        return deviceA;
    }


    public String getDeviceB() {
        return deviceB;
    }
}
