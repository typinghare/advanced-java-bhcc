package edu.bhcc.amazon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool implements Closeable {
    private static final Logger logger = LoggerFactory.getLogger(ThreadPool.class);

    private final ExecutorService executorService;

    /**
     * Creates a thread pool.
     * @param size the size of this thread pool.
     */
    public ThreadPool(int size) {
        executorService = Executors.newFixedThreadPool(size);
    }

    /**
     * Executes a function.
     * @param runnable closure function to execute
     */
    public void execute(Runnable runnable) {
        executorService.execute(runnable);
    }

    /**
     * Closes this thread pool; shutdowns the executor service.
     */
    @Override
    public void close() {
        executorService.shutdown();

        logger.info("The thread pool is being shutting down.");
        while (true) {
            if (executorService.isTerminated()) {
                logger.info("The thread pool has completely shut down.");
                break;
            }
        }
    }
}
