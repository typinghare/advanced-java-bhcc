package edu.bhcc.amazon;

import edu.bhcc.amazon.worker.Truck;

public class LoadingDock {
    /**
     * The number of packages on the dock.
     */
    private int numPackages = 0;

    /**
     * A specified robot adds packages to the dock.
     * @param robotName        the name of the robot
     * @param numPackagesToAdd the number of packages to add
     */
    public synchronized void addPackages(String robotName, int numPackagesToAdd) {
        int _numPackages = numPackages + numPackagesToAdd;
        numPackages += numPackagesToAdd;
        System.out.printf("[Robot %s] added %d packages, Total = %d.%n", robotName, numPackagesToAdd, _numPackages);
    }

    /**
     * A specified truck takes packages from the dock.
     * @param truckName         the name of the truck
     * @param numPackagesToTake the number of packages to take
     */
    public synchronized void takePackages(String truckName, int numPackagesToTake) {
        final int _numPackages = getNumPackages();
        if (_numPackages > Truck.LOADING_CAPACITY) {
            numPackages -= numPackagesToTake;
            System.out.printf("[Truck %s] is departing with %d packages.%n", truckName, numPackagesToTake);
        } else {
            System.out.printf(
                "[Truck %s] is waiting for %d packages, but there are only: %d.%n",
                truckName, Truck.LOADING_CAPACITY, _numPackages
            );
        }
    }

    /**
     * Returns the number of packages on the dock.
     * @return the number of packages on the dock.
     */
    public synchronized int getNumPackages() {
        return numPackages;
    }
}
