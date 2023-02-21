package edu.bhcc.amazon.worker;

import java.util.Random;
import java.util.random.RandomGenerator;

public class Robot extends LoadingDockWorker implements Runnable {
    private static final int MAX_PACKAGES_ADD = 3;

    private static final int BREAK_TIME = 1000;

    private static final RandomGenerator randomGenerator = new Random(System.currentTimeMillis());

    private final String name;

    /**
     * Creates a robot.
     * @param name the name of this robot
     */
    public Robot(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            // the robot will transfer a random number (between 1 and 3) of packages to the loading dock;
            // after the transfer, the robot will sleep for a break time (1 second)
            int numPackages = randomGenerator.nextInt(Integer.MAX_VALUE) % MAX_PACKAGES_ADD + 1;
            getLoadingDock().addPackages(name, numPackages);

            try {
                Thread.sleep(BREAK_TIME);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
