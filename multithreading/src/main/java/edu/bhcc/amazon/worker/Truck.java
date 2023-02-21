package edu.bhcc.amazon.worker;

public class Truck extends LoadingDockWorker implements Runnable {
    public static final int LOADING_CAPACITY = 20;

    private static final int BREAK_TIME = 1000;

    private final String name;

    /**
     * Creates a truck.
     * @param name the name of this truck
     */
    public Truck(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            // the truck will only take off if there are at least 20 packages on the loading dock;
            // the truck will take a break time (1 second) after checking the number of packages
            // or departing from the loading dock
            getLoadingDock().takePackages(name, LOADING_CAPACITY);

            try {
                Thread.sleep(BREAK_TIME);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
