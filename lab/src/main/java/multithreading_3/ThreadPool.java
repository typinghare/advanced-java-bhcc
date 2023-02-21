package multithreading_3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {
    public static void main(String[] args) {
        Runnable print1 = new PrintString("1", 1000);
        Runnable print2 = new PrintString("2", 1000);
        Runnable print3 = new PrintString("3", 1000);
        Runnable print4 = new PrintString("4", 1000);

        try (ExecutorService executorService = Executors.newFixedThreadPool(3)) {
            executorService.execute(print1);
            executorService.execute(print2);
            executorService.execute(print3);
            executorService.execute(print4);
        }
    }

    public record PrintString(String stringToPrint, int times) implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < times; i++) {
                System.out.print(stringToPrint);
            }
        }
    }
}
