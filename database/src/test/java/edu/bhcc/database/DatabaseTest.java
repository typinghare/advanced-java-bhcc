package edu.bhcc.database;

import edu.bhcc.database.constant.DatabaseConstant;
import edu.bhcc.database.model.Weather;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * This test suite tests retrieving data from the database.
 */
public class DatabaseTest {
    /**
     * Database instance.
     */
    private final Database database;

    public DatabaseTest() throws SQLException {
        // initialize a database instance
        database = new Database(new Database.Config(
            DatabaseConstant.URL,
            DatabaseConstant.USERNAME,
            DatabaseConstant.PASSWORD,
            DatabaseConstant.DATABASE_NAME,
            true
        ));
    }

    @Test
    @Order(1)
    public void testSpecificWeather1() {
        final Weather weather = database.find(Weather.class, "`date` = '2012-01-25'");

        assertEquals(weather.getPrecipitation(), 8.1);
        assertEquals(weather.getTempMax(), 8.9);
        assertEquals(weather.getTempMin(), 4.4);
        assertEquals(weather.getWind(), 5.4);
        assertEquals(weather.getWeather(), "rain");
    }

    @Test
    @Order(2)
    public void testSpecificWeather2() {
        final Weather weather = database.find(Weather.class, "`date` = '2015-12-31'");

        assertEquals(weather.getPrecipitation(), 0.0);
        assertEquals(weather.getTempMax(), 5.6);
        assertEquals(weather.getTempMin(), -2.1);
        assertEquals(weather.getWind(), 3.5);
        assertEquals(weather.getWeather(), "sun");
    }

    @Test
    @Order(3)
    public void testNotExistDate() {
        final Weather weather = database.find(Weather.class, "`date` = '2023-03-07'");

        assertNull(weather);
    }

    @Test
    @Order(4)
    public void testWrongFormat() {
        final Weather weather = database.find(Weather.class, "`date` = '20140209'");

        assertNull(weather);
    }
}
