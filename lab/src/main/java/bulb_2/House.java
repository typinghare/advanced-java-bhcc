package bulb_2;

public class House {
    public static void main(String[] args) {
        SmartBulb smartBulb1 = new SmartBulb("Living Room");
        System.out.println(smartBulb1.getLightSummary());
        smartBulb1.turnOn();
        System.out.println(smartBulb1.getLightSummary());
        smartBulb1.setDimIntensity(60);
        System.out.println(smartBulb1.getLightSummary());
        smartBulb1.turnOff();
        System.out.println(smartBulb1.getLightSummary());
    }
}
