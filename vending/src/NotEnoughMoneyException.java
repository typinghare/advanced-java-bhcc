public class NotEnoughMoneyException extends RuntimeException {
    public NotEnoughMoneyException(String productName, double unitPrice) {
        super(String.format("The unit price of [%s] is %s. Please put more money!", productName, unitPrice));
    }
}
