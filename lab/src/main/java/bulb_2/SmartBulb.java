package bulb_2;

public class SmartBulb {
    String location;

    boolean on = false;

    /**
     * This value is between 0 and 100
     */
    int dimIntensity = 100;

    public SmartBulb(String location) {
        this.location = location;
    }

    void turnOn() {
        on = true;
    }

    void turnOff() {
        on = false;
    }

    void setDimIntensity(int dimIntensity) {
        if (!on) {
            System.out.println("You should turn on the bulb before setting dim intensity.");
            return;
        }

        if (dimIntensity < 0 || dimIntensity > 100) {
            System.out.println("The dim intensity should between 0 to 100.");
            return;
        }

        this.dimIntensity = dimIntensity;
    }

    String getLightSummary() {
        return String.format(
            "Light @ %s is %s", location, on ? "on  @ dim intensity of " + dimIntensity : "off");
    }
}
