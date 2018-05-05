package com.ox.core;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PlayerTest {

    @BeforeMethod
    public void setUp() {
    }

    @Test
    public void testGetOppositePlayerToX() {
        //given when then
        assertEquals(Player.X.getOppositePlayer(), Player.O);
    }

    @Test
    public void testGetOppositePlayerToO() {
        //given when then
        assertEquals(Player.O.getOppositePlayer(), Player.X);
    }
}