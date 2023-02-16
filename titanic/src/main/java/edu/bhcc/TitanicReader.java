package edu.bhcc;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @author James Chan
 */
public final class TitanicReader {
    /**
     * The logger instance, whose name is "edu.bhcc.TitanicReader".
     */
    private static final Logger logger = LoggerFactory.getLogger(TitanicReader.class);

    /**
     * Passengers. Relation: passenger id => passenger instance
     */
    private final Map<Integer, Passenger> passengerMap = new HashMap<>();

    /**
     * Creates a titanic reader.
     * @param file target data file to read
     */
    public TitanicReader(File file) {
        logger.info("Trying to read file: " + file.getAbsolutePath());

        try {
            // read data file and fill passengers into the passengerMap
            final Reader reader = new FileReader(file);
            final Stream<CSVRecord> recordStream = CSVFormat.EXCEL.parse(reader).stream();

            recordStream.skip(1).forEach(record -> {
                final Passenger passenger = new Passenger();
                final Integer passengerId = Integer.parseInt(record.get(0));
                final Double age = record.get(5).equals("") ? null : Double.parseDouble(record.get(5));

                passenger.setPassengerId(passengerId);
                passenger.setSurvived(Boolean.parseBoolean(record.get(1)));
                passenger.setPClass(Integer.parseInt(record.get(2)));
                passenger.setName(record.get(3));
                passenger.setSex(Sex.parse(record.get(4)));
                passenger.setAge(age);
                passenger.setSibSP(Integer.parseInt(record.get(6)));
                passenger.setParch(Integer.parseInt(record.get(7)));
                passenger.setTicket(record.get(8));
                passenger.setFare(Double.parseDouble(record.get(9)));
                passenger.setCabin(record.get(10));

                passengerMap.put(passengerId, passenger);
            });
            logger.warn("Just want to log something here.");
        } catch (IOException e) {
            logger.error("Encounter an IO Exception when reading file.", e);
        }

        logger.info(String.format("There are %d records in the CSV file.", passengerMap.size()));
    }

    /**
     * Returns the number of passengers.
     * @return the number of passengers.
     */
    public int getNumPassengers() {
        return passengerMap.size();
    }

    /**
     * Returns the number of passengers that in the first class.
     * @return the number of passengers that in the first class.
     */
    public int getNumFirstClassPassengers() {
        return (int) passengerMap.values().stream()
            .filter(passenger -> passenger.getPClass() == 1)
            .count();
    }

    /**
     * Returns the number of passengers that in the second class.
     * @return the number of passengers that in the second class.
     */
    public int getNumSecondClassPassengers() {
        return (int) passengerMap.values().stream()
            .filter(passenger -> passenger.getPClass() == 2)
            .count();
    }

    /**
     * Returns the number of passengers that in the third class.
     * @return the number of passengers that in the third class.
     */
    public int getNumThirdClassPassengers() {
        return (int) passengerMap.values().stream()
            .filter(passenger -> passenger.getPClass() == 3)
            .count();
    }
}
