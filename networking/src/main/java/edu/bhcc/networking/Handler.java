package edu.bhcc.networking;

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * The abstract handler that implements runnable.
 * @author James (Zhuojian Chen)
 */
public abstract class Handler implements Runnable, Closeable {
    /**
     * Client socket.
     */
    protected final Socket clientSocket;

    /**
     * Server input stream. This input stream receives objects from the client socket.
     */
    protected final ObjectInputStream inputStream;

    /**
     * Server output stream. This out put stream sends data to the client socket.
     */
    protected final ObjectOutputStream outputStream;

    /**
     * Creates a weather request handle.
     * @param clientSocket client socket
     * @throws IOException if there are issues with input stream or output stream
     */
    public Handler(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        this.inputStream = new ObjectInputStream(clientSocket.getInputStream());
        this.outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
    }

    @Override
    public void close() throws IOException {
        inputStream.close();
        outputStream.close();
    }
}
