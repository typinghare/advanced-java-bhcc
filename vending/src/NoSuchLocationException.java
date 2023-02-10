public class NoSuchLocationException extends RuntimeException {
    public NoSuchLocationException(String location) {
        super("No such location: " + location);
    }
}
