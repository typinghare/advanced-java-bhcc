package edu.bhcc;

import edu.bhcc.networking.WeatherReader;
import edu.bhcc.networking.model.Weather;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Weather Reader test suite.
 * @author James (Zhuojian Chen)
 */
public class WeatherReaderTest {
    private static final String WEATHER_DATA_FILEPATH = "src/main/resources/seattle-weather.csv";

    @Test
    @Order(1)
    public void testCreatingInstance() {
        final WeatherReader weatherReader = new WeatherReader(WEATHER_DATA_FILEPATH);
        assertNotNull(weatherReader);
    }

    @Test
    @Order(2)
    public void testSpecificWeather1() {
        final WeatherReader weatherReader = new WeatherReader(WEATHER_DATA_FILEPATH);
        final Weather weather = weatherReader.getWeather("2012-01-25");

        assertEquals(weather.getPrecipitation(), 8.1);
        assertEquals(weather.getTempMax(), 8.9);
        assertEquals(weather.getTempMin(), 4.4);
        assertEquals(weather.getWind(), 5.4);
        assertEquals(weather.getWeather(), "rain");
    }

    @Test
    @Order(3)
    public void testSpecificWeather2() {
        final WeatherReader weatherReader = new WeatherReader(WEATHER_DATA_FILEPATH);
        final Weather weather = weatherReader.getWeather("2015-12-31");

        assertEquals(weather.getPrecipitation(), 0.0);
        assertEquals(weather.getTempMax(), 5.6);
        assertEquals(weather.getTempMin(), -2.1);
        assertEquals(weather.getWind(), 3.5);
        assertEquals(weather.getWeather(), "sun");
    }

    @Test
    @Order(4)
    public void testNotExistDate() {
        final WeatherReader weatherReader = new WeatherReader(WEATHER_DATA_FILEPATH);
        final Weather weather = weatherReader.getWeather("2023-03-07");

        assertNull(weather);
    }

    @Test
    @Order(5)
    public void testWrongFormat() {
        final WeatherReader weatherReader = new WeatherReader(WEATHER_DATA_FILEPATH);
        final Weather weather = weatherReader.getWeather("20140209");

        assertNull(weather);
    }
}
