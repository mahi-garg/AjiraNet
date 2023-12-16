package models;

public class ConnectCommand extends Command{
    String deviceA;
    String deviceB;

    public ConnectCommand(String deviceA, String deviceB) {
        super(CommandType.CONNECT);
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
