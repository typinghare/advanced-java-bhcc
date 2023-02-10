public class Drink extends Product {
    public Drink(String productName) {
        super(productName);
    }

    @Override
    public void consume() {
        System.out.printf("Yum, you drink the %s.%n", productName);
    }
}
