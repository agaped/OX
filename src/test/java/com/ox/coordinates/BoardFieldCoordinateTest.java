package com.ox.coordinates;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class BoardFieldCoordinateTest {

    private BoardFieldCoordinate bc;

    @BeforeMethod
    public void setUp() {
        bc = BoardFieldCoordinate.parse("4");
    }

    @Test
    public void checkIfParseReturnsCorrectCoordinate() {
        //given and when

        //then
        assertEquals(bc, new BoardFieldCoordinate(4));
    }
}