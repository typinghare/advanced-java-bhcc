package bhcc.edu.amazon;


import bhcc.edu.amazon.worker.Robot;
import bhcc.edu.amazon.worker.Truck;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Main class.
 * @author Zhuojian Chen (James)
 * @date Feb 17, 2022
 */
public class Main {
    public static void main(String[] args) {
        final int ROBOT_NUMBER = 3, TRUCK_NUMBER = 2;

        // create a loading dock
        final LoadingDock loadingDock = new LoadingDock();

        // create robots and trucks to work on the loading dock
        final List<Robot> robotList = new ArrayList<>();
        for (int i = 1; i <= ROBOT_NUMBER; i++) {
            final Robot robot = new Robot("R" + i);
            robot.setLoadingDock(loadingDock);
            robotList.add(robot);
        }

        final List<Truck> truckList = new ArrayList<>();
        for (int i = 1; i <= TRUCK_NUMBER; i++) {
            final Truck truck = new Truck("T" + i);
            truck.setLoadingDock(loadingDock);
            truckList.add(truck);
        }

        // boot the program
        System.out.println("Welcome to Amazon.com!");
        try (final ExecutorService executorService = Executors.newFixedThreadPool(5)) {
            robotList.forEach(executorService::execute);
            truckList.forEach(executorService::execute);

            executorService.shutdown();
        }
    }
}
