package edu.bhcc.networking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Server pool.
 * @author James (Zhuojian Chen)
 */
public class ServerPool implements Closeable {
    /**
     * Server pool logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(ServerPool.class);

    /**
     * The server this pool pertains to.
     */
    private final Server server;

    /**
     * The number of threads in the pool.
     */
    private final int threadNumber;

    /**
     * The executor service.
     */
    private ExecutorService executorService;

    /**
     * Whether this server pool is called to close. This server pool will be closing if it is true.
     */
    private boolean isCalledToClosed = false;

    /**
     * Creates a server pool.
     * @param server the server this pool pertains to
     */
    public ServerPool(Server server, int threadNumber) {
        this.server = server;
        this.threadNumber = threadNumber;
    }

    /**
     * Starts to accept sockets and handle requests.
     */
    public void start() throws Exception {
        if (executorService != null) {
            executorService.shutdown();
            final boolean terminationResult =
                executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

            if (!terminationResult) {
                logger.error("The program was unable to update the thread " +
                    "because the running executor service could not be shutdown.");
                return;
            }
        }

        executorService = Executors.newFixedThreadPool(server.getServerPool().getThreadNumber());

        while (!isCalledToClosed) {
            // wait for a new connection
            final Socket socket = server.getSocket().accept();



            // handle new request
            final Handler handler = server.getHandler(socket);
            executorService.execute(handler);
        }
    }

    /**
     * Returns the number of threads in this server pool.
     * @return the number of threads in this server pool.
     */
    public int getThreadNumber() {
        return threadNumber;
    }

    @Override
    public void close() {
        isCalledToClosed = true;
    }
}
