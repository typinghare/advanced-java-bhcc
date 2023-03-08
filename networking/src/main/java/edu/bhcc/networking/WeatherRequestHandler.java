package edu.bhcc.networking;

import edu.bhcc.networking.model.Weather;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author James (Zhuojian Chen)
 * This class handles a weather request from clients.
 */
public class WeatherRequestHandler extends Handler {
    /**
     * Weather request handler logger.
     */
    private static final Logger logger = LoggerFactory.getLogger(WeatherRequestHandler.class);

    /**
     * The weather server.
     */
    private final WeatherServer weatherServer;

    /**
     * Creates a weather request handler.
     * @param clientSocket  client socket
     * @param weatherServer weather server that this handler pertains to
     */
    public WeatherRequestHandler(Socket clientSocket, WeatherServer weatherServer) throws IOException {
        super(clientSocket);
        this.weatherServer = weatherServer;
    }

    @Override
    public void run() {
        try {
            final String dateString = (String) inputStream.readObject();
            final InetAddress inetAddress = clientSocket.getInetAddress();
            logger.info("Received request from: " + inetAddress.toString() +
                ". The request is: \"" + dateString + "\".");

            final Weather weather = weatherServer.getWeather(dateString);

            // send the result (weather object) to the requester
            outputStream.writeObject(weather);
            outputStream.flush();
        } catch (IOException | ClassNotFoundException e) {
            final String threadName = Thread.currentThread().getName();
            logger.error("Unexpected error encountered when processing request. Thread: " + threadName);
            throw new RuntimeException(e);
        }
    }
}
