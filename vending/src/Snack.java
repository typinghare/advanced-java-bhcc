public class Snack extends Product {
    public Snack(String productName) {
        super(productName);
    }

    @Override
    public void consume() {
        System.out.printf("Yum, you eat the %s.%n", productName);
    }
}
