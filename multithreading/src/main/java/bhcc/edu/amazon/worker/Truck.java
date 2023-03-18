package bhcc.edu.amazon.worker;

/**
 * @author Zhuojian Chen (James)
 */
public class Truck extends LoadingDockWorker implements Runnable {
    /**
     * The maximum number of loading capacity of a truck. A truck will not take packages if the number of
     * packages on the loading dock is less than this number.
     */
    public static final int LOADING_CAPACITY = 20;

    /**
     * Creates a truck.
     * @param name the name of this truck
     */
    public Truck(String name) {
        super(name);
    }

    @Override
    public void run() {
        while (true) {
            getLoadingDock().takePackages(getName(), LOADING_CAPACITY);
        }
    }
}
