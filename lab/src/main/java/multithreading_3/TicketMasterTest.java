package multithreading_3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TicketMasterTest {
    public static void main(String[] args) throws InterruptedException {
        TicketMaster ticketMaster = new TicketMaster(100);

        List<Runnable> runnableList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            runnableList.add(() -> {
                ticketMaster.buyTicket();
                System.out.println(Thread.currentThread().getName() + " bought a ticket");
            });
        }

        try (ExecutorService executorService = Executors.newFixedThreadPool(100)) {
            for (Runnable runnable : runnableList) {
                executorService.execute(runnable);
            }

            Thread.sleep(1000);
        }

        System.out.println("Ticket remaining: " + ticketMaster.getNumTicketsToSell());
    }
}
