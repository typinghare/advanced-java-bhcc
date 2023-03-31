package edu.bhcc.database;

import com.google.common.io.Files;
import edu.bhcc.database.constant.DatabaseConstant;
import edu.bhcc.database.model.Weather;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This class provides a command line tool to parse CSV file and store data into database.
 * @author James (Zhuojian Chen)
 */
public class DataLoader {
    /**
     * Main function.
     */
    public static void main(String[] args) {
        new DataLoader().loadCsvFile();
    }

    /**
     * Loads a CSV file and inserts the data into database.
     * @param filepath the path of the CSV file
     */
    public void loadCsvFile(String filepath) {
        // we assume that the CSV file given matches the Weather model
        // read the CSV file
        System.out.println("Reading weather data file: " + filepath);
        final List<Weather> weatherList = new ArrayList<>();

        try {
            final Reader reader = new FileReader(filepath);
            final Stream<CSVRecord> recordStream = CSVFormat.EXCEL.parse(reader).stream();

            // read all records and stores them into the `dateWeatherMap`
            recordStream.skip(1).forEach(record -> {
                final Weather weather = new Weather();
                weather.setDate(record.get(0));
                weather.setPrecipitation(Double.parseDouble(record.get(1)));
                weather.setTempMax(Double.parseDouble(record.get(2)));
                weather.setTempMin(Double.parseDouble(record.get(3)));
                weather.setWind(Double.parseDouble(record.get(4)));
                weather.setWeather(record.get(5));

                weatherList.add(weather);
            });

            System.out.println("Complete reading weather data file. There were " +
                weatherList.size() + " weathers to be recorded.");
        } catch (IOException e) {
            System.out.println("Fail to read the weather data file.");
        }

        // inserts the weather models into the database
        final Database.Config databaseConfig = new Database.Config(
            DatabaseConstant.URL,
            DatabaseConstant.USERNAME,
            DatabaseConstant.PASSWORD,
            DatabaseConstant.DATABASE_NAME,
            false
        );
        try (final Database database = new Database(databaseConfig)) {
            int i = 0;
            long lastId = 0;
            for (final Weather weather : weatherList) {
                lastId = database.insert(weather);
                if (++i % 100 == 0) {
                    System.out.println(i + " records were recorded.");
                }
            }

            System.out.println("The id of the last record is: " + lastId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Loads a CSV file and inserts the data into database. This method requires the user to input the path of
     * the CSV file on the console/terminal.
     */
    public void loadCsvFile() {
        final Scanner scanner = new Scanner(System.in);

        // this function reads a valid CSV file path (absolute path) from the console input
        // returns null if: the file is not a CSV file; the file does not exist
        final Supplier<String> readCsvFilepath = () -> {
            System.out.print("Please input the path of the CSV file: ");
            final String _filepath = scanner.nextLine();

            if (!Files.getFileExtension(_filepath).equals("csv")) {
                System.out.println("This file is not a CSV file.");
                return null;
            }

            final File file = new File(_filepath);
            if (!file.exists()) {
                System.out.println("The file does not exist: " + file.getAbsolutePath());
                return null;
            }

            return file.getAbsolutePath();
        };

        // read the filepath of the CSV file from the console input
        String filepath = null;
        while (filepath == null) {
            filepath = readCsvFilepath.get();
            if (filepath == null) {
                System.out.println("Please check and input the filepath again.");
            }
        }

        loadCsvFile(filepath);
    }
}
