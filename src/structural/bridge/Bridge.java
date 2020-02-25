package structural.bridge;

/**
 * Bridge Pattern: solves the need of building a simple and flexible hierarchy. By creating a bridge between 2 hierarchies (features and implementation).
 *  This example is for an universal control app with hierarchy to be changed to the one in Classes.
 *  Before (one complex hierarchy):
 *      RemoteControl (Feature)
 *          SonyTV (Implementation)
 *          LGTV (Implementation)
 *          AdvancedRemoteControl (Feature)
 *              SonyTV (Implementation)
 *              LGTV (Implementation)
 *
 *  After (two simpler hierarchies):
 *      RemoteControl (Feature)           Device (Implementation)
 *          AdvancedRemoteControl             SonyTV
 *                                            LGTV
 * Classes:
 *  RemoteControl (Feature)
 *      device: Device (Bridge)
 *      turnOn()
 *      turnOff()
 *
 *  AdvancedRemoteControl (Feature)
 *      super(device)
 *      setChannel()
 *
 *  interface Device (Implementation)
 *      turnOn()
 *      turnOff()
 *
 *  SonyTV (Implementation)
 *
 *  LGTV (Implementation)
 *
 * Relationships:
 *  RemoteControl is compose with a Device
 *  AdvancedRemoteControl extends RemoteControl
 *  SonyTV implements Device
 *  LGTV implements Device
 *
 *  RemoteControl feature has a Device that acts as a bridge to a TV control implementation.
 */
public class Bridge {
    public static void main(String[] args) {
        RemoteControl remoteControl = new RemoteControl(new SonyTV());
        remoteControl.turnOn();
        remoteControl.turnOff();

        AdvancedRemoteControl advancedRemoteControl = new AdvancedRemoteControl(new LGTV());
        advancedRemoteControl.setChannel(5);
    }
}

class RemoteControl {
    protected Device device;
    public RemoteControl(Device device) {
        this.device = device;
    }
    public void turnOn() {
        device.turnOn();
    }
    public void turnOff() {
        device.turnOff();
    }
}

class AdvancedRemoteControl extends RemoteControl {
    public AdvancedRemoteControl(Device device) {
        super(device);
    }
    public void setChannel(int number) {
        device.setChannel(number);
    }
}

interface Device {
    void turnOn();
    void turnOff();
    void setChannel(int number);
}

class SonyTV implements Device {
    @Override
    public void turnOn() {
        System.out.println("Sony: turnOn");
    }
    @Override
    public void turnOff() {
        System.out.println("Sony: turnOff");
    }
    @Override
    public void setChannel(int number) {
        System.out.println("Sony: setChannel");
    }
}

class LGTV implements Device {
    @Override
    public void turnOn() {
        System.out.println("LG: turnOn");
    }
    @Override
    public void turnOff() {
        System.out.println("LG: turnOff");
    }
    @Override
    public void setChannel(int number) {
        System.out.println("LG: setChannel");
    }
}