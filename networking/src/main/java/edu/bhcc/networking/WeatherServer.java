package edu.bhcc.networking;

import edu.bhcc.networking.model.Weather;

import java.io.IOException;
import java.net.Socket;

/**
 * A server that particularly deal with weather requests. This server accepts date string and request, and
 * response a weather object.
 * @author James (Zhuojian Chen)
 */
public class WeatherServer extends Server {
    public static void main(String[] args) throws Exception {
        try (final WeatherServer weatherServer = new WeatherServer(8080, 5)) {
            weatherServer.start();
        }
    }

    /**
     * The filepath of weather data file.
     */
    public static final String WEATHER_DATA_FILEPATH = "src/main/resources/seattle-weather.csv";

    /**
     * The weather reader
     */
    private final WeatherReader weatherReader;

    /**
     * Creates a weather server.
     * @param listenPort the port to listening on
     */
    public WeatherServer(int listenPort, int threadNumber) throws IOException {
        super(listenPort, threadNumber);

        weatherReader = new WeatherReader(WEATHER_DATA_FILEPATH);
    }

    /**
     * Returns the weather by a specified date string.
     * @param dateString date string
     * @return the weather by a specified date string.
     */
    public Weather getWeather(String dateString) {
        return weatherReader.getWeather(dateString);
    }

    @Override
    public Handler getHandler(Socket clientSocket) {
        try {
            return new WeatherRequestHandler(clientSocket, this);
        } catch (IOException e) {
            logger.error("Fail to create the handler.", e);
        }

        return null;
    }
}
