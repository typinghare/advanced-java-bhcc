package edu.bhcc;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TitanicReaderTest {
    @Test
    public void readTitanic() throws Exception {
        final File file = new File("src/test/test_data/titanic.csv");
        final TitanicReader titanicReader = new TitanicReader(file);

        // tests
        assertEquals(titanicReader.getNumPassengers(), 891);
        assertEquals(titanicReader.getNumFirstClassPassengers(), 216);
        assertEquals(titanicReader.getNumSecondClassPassengers(), 184);
        assertEquals(titanicReader.getNumThirdClassPassengers(), 491);

        System.out.println("All test cases in readTitanic pass.");
    }
}
