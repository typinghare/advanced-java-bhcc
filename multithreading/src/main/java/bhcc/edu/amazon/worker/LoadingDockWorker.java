package bhcc.edu.amazon.worker;


import bhcc.edu.amazon.LoadingDock;

/**
 * A worker who works on a specified loading dock.
 * @author Zhuojian Chen (James)
 */
public abstract class LoadingDockWorker {
    /**
     * The loading dock this worker is working on.
     */
    private LoadingDock loadingDock;

    /**
     * The name of this worker.
     */
    private final String name;

    /**
     * Creates a loading dockworker.
     * @param name the name of this worker.
     */
    protected LoadingDockWorker(String name) {
        this.name = name;
    }

    /**
     * Sets the loading dock this worker is working on.
     * @param loadingDock the loading dock this worker is working on
     */
    public void setLoadingDock(LoadingDock loadingDock) {
        this.loadingDock = loadingDock;
    }

    /**
     * Returns the loading dock this worker is working on.
     * @return the loading dock this worker is working on.
     */
    public LoadingDock getLoadingDock() {
        return loadingDock;
    }

    /**
     * Returns the name of this worker.
     * @return the name of this worker.
     */
    public String getName() {
        return name;
    }
}
