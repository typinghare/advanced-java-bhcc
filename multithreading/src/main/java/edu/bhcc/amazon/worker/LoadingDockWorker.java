package edu.bhcc.amazon.worker;

import edu.bhcc.amazon.LoadingDock;

public abstract class LoadingDockWorker {
    private LoadingDock loadingDock;

    /**
     * Sets the loading dock this object is working on.
     * @param loadingDock the loading dock this object is working on
     */
    public void setLoadingDock(LoadingDock loadingDock) {
        this.loadingDock = loadingDock;
    }

    /**
     * Returns the loading dock this object is working on.
     * @return the loading dock this object is working on.
     */
    public LoadingDock getLoadingDock() {
        return loadingDock;
    }
}
