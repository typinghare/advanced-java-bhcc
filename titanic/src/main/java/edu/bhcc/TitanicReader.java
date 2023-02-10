package edu.bhcc;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
    private static final Logger logger = LogManager.getLogger(TitanicReader.class);

    /**
     * Passengers. Relation: passenger id => passenger instance
     */
    private final Map<Integer, Passenger> passengerMap = new HashMap<>();

    /**
     * Creates a titanic reader.
     * @param file target data file
     */
    public TitanicReader(File file) throws IOException {
        // read data file and fill passengers into passengerMap
        final Reader reader = new FileReader(file);
        logger.info("Reading file: " + file.getAbsolutePath());

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
    }

    public int getNumPassengers() {
        return passengerMap.size();
    }

    public int getNumFirstClassPassengers() {
        return (int) passengerMap.values().stream()
            .filter(passenger -> passenger.getPClass() == 1)
            .count();
    }

    public int getNumSecondClassPassengers() {
        return (int) passengerMap.values().stream()
            .filter(passenger -> passenger.getPClass() == 2)
            .count();
    }

    public int getNumThirdClassPassengers() {
        return (int) passengerMap.values().stream()
            .filter(passenger -> passenger.getPClass() == 3)
            .count();
    }
}
