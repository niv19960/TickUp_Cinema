package JUnitTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Model.Theaters.StandardTheater;


class StandardTheaterTests {
    private StandardTheater standard = null;

    @BeforeEach
    void setUp() {
        standard = new StandardTheater(1);
    }

    // Test for set the right theater type
    @Test
    void testSetGetTheaterType() {
//        standard.setTheaterType("THEATER_STANDARD");
//        assertEquals("THEATER_STANDARD", standard.getTheaterType());
    }
}
