package edu.bhcc.amazon;

import edu.bhcc.amazon.worker.Robot;
import edu.bhcc.amazon.worker.Truck;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author James Chan / Zhuojian Chen
 * @date Feb 17, 2022
 */
public class Main {
    public static void main(String[] args) {
        final LoadingDock loadingDock = new LoadingDock();

        final Robot R1 = new Robot("R1");
        final Robot R2 = new Robot("R2");
        final Robot R3 = new Robot("R3");
        final Truck T1 = new Truck("T1");
        final Truck T2 = new Truck("T2");

        R1.setLoadingDock(loadingDock);
        R2.setLoadingDock(loadingDock);
        R3.setLoadingDock(loadingDock);
        T1.setLoadingDock(loadingDock);
        T2.setLoadingDock(loadingDock);

        System.out.println("Welcome to Amazon.com");
        try (final ExecutorService executorService = Executors.newFixedThreadPool(5)) {
            executorService.execute(R1);
            executorService.execute(R2);
            executorService.execute(R3);
            executorService.execute(T1);
            executorService.execute(T2);
        }
    }
}
