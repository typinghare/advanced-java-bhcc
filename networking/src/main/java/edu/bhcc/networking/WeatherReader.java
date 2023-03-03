package edu.bhcc.networking;

import edu.bhcc.networking.model.Weather;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Weather reader. This class uses common-csv to parse .csv files.
 * @see <a href="https://commons.apache.org/proper/commons-csv/user-guide.html">common-csv<a>
 */
public class WeatherReader {
    /**
     * Mapping: date string ("yyyy-mm-dd") => weather
     * @see Weather
     */
    private final Map<String, Weather> dateWeatherMap = new HashMap<>();

    /**
     * Reads a weather csv file and create a weather reader.
     * @param filepath the path of file to read
     */
    public WeatherReader(String filepath) {
        try {
            // read the .csv file
            final Reader reader = new FileReader(filepath);
            final Stream<CSVRecord> recordStream = CSVFormat.EXCEL.parse(reader).stream();

            // reads all records and stores them into the `dateWeatherMap`
            recordStream.skip(1).forEach(record -> {
                final String dateString = record.get(0);
                final Weather weather = new Weather();
                dateWeatherMap.put(dateString, weather);

                weather.setPrecipitation(Double.parseDouble(record.get(1)));
                weather.setTempMax(Double.parseDouble(record.get(2)));
                weather.setTempMin(Double.parseDouble(record.get(3)));
                weather.setWind(Double.parseDouble(record.get(4)));
                weather.setWeather(record.get(5));
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the weather by a specified date string.
     * @param dateString date string
     * @return the weather by a specified date string.
     */
    public Weather getWeather(String dateString) {
        return dateWeatherMap.get(dateString);
    }
}
