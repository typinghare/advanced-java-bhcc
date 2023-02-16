package edu.bhcc;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TitanicReaderTest {
    /**
     * A junit test suite for testing titanic reader.
     * @see TitanicReader
     */
    @Test
    public void readTitanic() {
        final File file = new File("src/test/test_data/titanic.csv");
        final TitanicReader titanicReader = new TitanicReader(file);

        // test cases
        assertEquals(titanicReader.getNumPassengers(), 891);
        assertEquals(titanicReader.getNumFirstClassPassengers(), 216);
        assertEquals(titanicReader.getNumSecondClassPassengers(), 184);
        assertEquals(titanicReader.getNumThirdClassPassengers(), 491);

        System.out.println("All test cases in [readTitanic] test suite pass.");
    }
}
