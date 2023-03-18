package bhcc.edu.amazon;


import bhcc.edu.amazon.worker.Truck;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Loading dock.
 * @author Zhuojian Chen (James)
 */
public class LoadingDock {
    /**
     * A lock.
     */
    private final Lock lock = new ReentrantLock();

    /**
     * Condition.
     */
    private final Condition packageAdded = lock.newCondition();

    /**
     * The number of packages on the dock.
     */
    private int numPackages = 0;

    /**
     * A specified robot adds packages to the loading dock.
     * @param robotName        the name of the robot to add packages to the loading dock
     * @param numPackagesToAdd the number of packages to add
     */
    public void addPackages(String robotName, int numPackagesToAdd) {
        lock.lock();
        try {
            numPackages += numPackagesToAdd;
            System.out.printf("[Robot %s] added %d packages, Total = %d.%n",
                robotName, numPackagesToAdd, numPackages);
            packageAdded.signalAll();
        } finally {
            lock.unlock();
        }
    }

    /**
     * A specified truck takes packages from the loading dock.
     * @param truckName         the name of the truck to take packages from the loading dock
     * @param numPackagesToTake the number of packages to take
     */
    public void takePackages(String truckName, int numPackagesToTake) {
        lock.lock();
        try {
            while (numPackages < Truck.LOADING_CAPACITY) {
                // wait for the "packagedAdded" condition
                System.out.printf(
                    "[Truck %s] is waiting for %d packages, but there are only: %d.%n",
                    truckName, Truck.LOADING_CAPACITY, numPackages
                );
                packageAdded.await();
            }

            numPackages -= numPackagesToTake;
            System.out.printf("[Truck %s] is departing with %d packages.%n", truckName, numPackagesToTake);
        } catch (InterruptedException ignored) {
        } finally {
            lock.unlock();
        }
    }
}
