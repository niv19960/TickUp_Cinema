package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Classes.MultiDimTheater;
import Classes.EnumDimensions;


public class MultiDimTheaterTest {
    private MultiDimTheater multiDim = null;

    @BeforeEach
    void setUp()
    {
        multiDim = new MultiDimTheater(7, EnumDimensions.IMAX);
    }

    @Test
    void testAddRemoveItem() {
        multiDim.setTheaterType("THEATER_IMAX");
        assertEquals("THEATER_IMAX", multiDim.getTheaterType());

    }

}