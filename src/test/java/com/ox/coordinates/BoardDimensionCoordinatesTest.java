package com.ox.coordinates;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class BoardDimensionCoordinatesTest {

    private BoardDimensionCoordinates bc;

    @BeforeMethod
    public void setUp() {
        bc = BoardDimensionCoordinates.parse("1 2");
    }

    @Test
    public void checkIfParsesFirstCoordinate() {
        //given and when

        //then
        assertEquals(bc.getX(), 1);
    }

    @Test
    public void checkIfParsesSecondCoordinate() {
        //given and when

        //then
        assertEquals(bc.getY(), 2);
    }

    @Test
    public void checkIfParseReturnsCorrectCoordinates() {
        //given and when

        //then
        assertEquals(bc, new BoardDimensionCoordinates(1, 2));
    }
}