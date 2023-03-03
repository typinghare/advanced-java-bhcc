package edu.bhcc.networking;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * This class handles a weather request from clients.
 */
public class WeatherRequestHandler implements Runnable {
    /**
     * Client socket.
     */
    private final Socket clientSocket;

    /**
     * Server input stream. This input stream receives objects from the client socket.
     */
    private final ObjectInputStream inputStream;

    /**
     * Server output stream. This out put stream sends data to the client socket.
     */
    private final BufferedOutputStream outputStream;

    /**
     * Creates a weather request handle.
     * @param clientSocket client socket
     * @throws IOException if there are issues with input stream or output stream
     */
    public WeatherRequestHandler(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        this.inputStream = new ObjectInputStream(clientSocket.getInputStream());
        this.outputStream = new BufferedOutputStream(clientSocket.getOutputStream());
    }

    @Override
    public void run() {
        try {
            final String weatherString = (String) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("There");
            throw new RuntimeException(e);
        }
    }
}
