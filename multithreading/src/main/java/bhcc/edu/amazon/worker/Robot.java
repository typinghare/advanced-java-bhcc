package bhcc.edu.amazon.worker;

import java.util.Random;
import java.util.random.RandomGenerator;

/**
 * A robot.
 * @author Zhuojian Chen (James)
 */
public class Robot extends LoadingDockWorker implements Runnable {
    /**
     * The maximum number of packages a robot can add at a time.
     */
    private static final int MAX_PACKAGES_ADD = 3;

    /**
     * The break time a robot takes after it adds packages onto the loading dock.
     */
    private static final int BREAK_TIME = 1000;

    /**
     * A random generator.
     */
    private static final RandomGenerator randomGenerator = new Random(System.currentTimeMillis());

    /**
     * Creates a robot.
     * @param name the name of this robot
     */
    public Robot(String name) {
        super(name);
    }

    @Override
    public void run() {
        while (true) {
            // the robot will transfer a random number (between 1 and 3) of packages to the loading dock;
            // after the transfer, the robot will sleep for a break time (one second)
            int numPackages = randomGenerator.nextInt(Integer.MAX_VALUE) % MAX_PACKAGES_ADD + 1;
            getLoadingDock().addPackages(this.getName(), numPackages);

            try {
                Thread.sleep(BREAK_TIME);
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}
