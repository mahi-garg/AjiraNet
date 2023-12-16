import models.DeviceType;
import services.AjiraNetService;

public class Main {
    public static void main(String[] args) {

        AjiraNetService ajiraNetService = new AjiraNetService();

        ajiraNetService.addDevice(DeviceType.Computer, "A1");
        ajiraNetService.addDevice(DeviceType.Computer, "A2");
        ajiraNetService.addDevice(DeviceType.Computer, "A1");

        ajiraNetService.addDevice(DeviceType.Repeater, "R1");
        ajiraNetService.addDevice(DeviceType.Repeater, "R2");
        ajiraNetService.addDevice(DeviceType.Repeater, "R1");

        ajiraNetService.addDevice(DeviceType.Repeater, "A1");

        ajiraNetService.getAllDevices();


        ajiraNetService.addConnection("A1","A2");
        ajiraNetService.addConnection("A1","A1");
        ajiraNetService.addConnection("A2","A1");
        ajiraNetService.addConnection("A2","A3");

        ajiraNetService.addConnection("R2","R3");
        ajiraNetService.addConnection("R1","R2");

        ajiraNetService.setDeviceStrength("A1",  2);

        ajiraNetService.setDeviceStrength("A1",  -2);

        ajiraNetService.setDeviceStrength("A3",  2);

        ajiraNetService.setDeviceStrength("R1",  2);

        ajiraNetService.getRouteInfo("A1", "A5");
    }
}