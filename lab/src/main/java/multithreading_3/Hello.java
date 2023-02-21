package multithreading_3;

public class Hello {
    public static void main(String[] args) {
        final Runnable printHello = new PrintString("hello", 1000);
        final Runnable printGoodbye = new PrintString("goodbye", 1000);

        final Thread helloThread = new Thread(printHello);
        final Thread goodbyeThread = new Thread(printGoodbye);

        helloThread.start();
        goodbyeThread.start();
    }

    public record PrintString(String stringToPrint, int times) implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < times; i++) {
                System.out.println(stringToPrint);
            }
        }
    }
}
