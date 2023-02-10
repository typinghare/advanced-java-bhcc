public class NotEnoughProductException extends RuntimeException {
    public NotEnoughProductException(String location) {
        super(String.format("Not enough product in the slot: %s", location));
    }
}
