package edu.bhcc.database;

import edu.bhcc.database.constant.DatabaseConstant;
import edu.bhcc.database.model.Weather;

import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;

/**
 * A server that particularly deal with weather requests. This server accepts date string and request, and
 * response a weather object.
 * @author James (Zhuojian Chen)
 */
public class WeatherServer extends Server {
    /**
     * To run the server.
     */
    public static void main(String[] args) throws Exception {
        try (final WeatherServer weatherServer = new WeatherServer(8080, 5)) {
            weatherServer.start();
        }
    }

    /**
     * Database instance.
     */
    private final Database database;

    /**
     * Creates a weather server.
     * @param listenPort the port to listening on
     */
    public WeatherServer(int listenPort, int threadNumber) throws SQLException {
        super(listenPort, threadNumber);

        database = new Database(new Database.Config(
            DatabaseConstant.URL,
            DatabaseConstant.USERNAME,
            DatabaseConstant.PASSWORD,
            DatabaseConstant.DATABASE_NAME,
            true
        ));
    }

    /**
     * Returns the weather by a specified date string.
     * @param dateString date string
     * @return the weather by a specified date string.
     */
    public Weather getWeather(String dateString) {
        return database.find(Weather.class, "`date` = '" + dateString + "'");
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
