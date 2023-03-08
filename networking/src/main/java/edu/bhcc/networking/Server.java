package edu.bhcc.networking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Virtual abstract server.
 * @author James (Zhuojian Chen)
 */
public abstract class Server implements Closeable {
    /**
     * Server logger.
     */
    protected static final Logger logger = LoggerFactory.getLogger(Server.class);

    /**
     * The port to listen on.
     */
    private final int listenPort;

    /**
     * the number of threads of the server pool.
     */
    private final int threadNumber;

    /**
     * The server socket.
     */
    private ServerSocket serverSocket;

    /**
     * The server pool.
     */
    private ServerPool serverPool;

    /**
     * Whether the server is listening.
     */
    private boolean isListening;

    /**
     * Creates a server.
     * @param listenPort the port to listening on
     */
    public Server(int listenPort, int threadNum) {
        this.listenPort = listenPort;
        this.threadNumber = threadNum;
    }

    /**
     * Starts listening on the port.
     */
    public void start() throws Exception {
        // start server
        serverSocket = new ServerSocket(listenPort);
        isListening = true;
        logger.info("The server is listening on: " + listenPort + '.');

        // start server pool
        serverPool = new ServerPool(this, threadNumber);
        serverPool.start();
        logger.info("Server pool is created, there are " + threadNumber + " threads in the pool.");
    }

    /**
     * Returns the socket of this server.
     * @return the socker of this server.
     */
    public ServerSocket getSocket() {
        return serverSocket;
    }

    /**
     * Returns the server pool.
     * @return the server pool.
     */
    public ServerPool getServerPool() {
        return serverPool;
    }

    /**
     * Whether the server is listening.
     * @return true if the server is listening; else otherwise.
     */
    public boolean isListening() {
        return isListening;
    }

    /**
     * Returns a handler.
     * @param clientSocket client socket
     * @return a handler.
     */
    public abstract Handler getHandler(Socket clientSocket);

    @Override
    public void close() throws IOException {
        if (serverSocket != null) {
            serverSocket.close();
        }
        logger.info("The server has been closed. Previous listening port: " + listenPort + '.');
    }
}
