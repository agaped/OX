package com.ox.core;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class WhoGoesNextTest {

    @Test
    public void testGetNextPlayerO() {
        //given and when
        WhoGoesNext whoGoesNext = new WhoGoesNext(Player.X);
        //then
        assertEquals(whoGoesNext.getNextPlayer(), Player.O);
    }

    @Test
    public void testGetNextPlayerX() {
        //given and when
        WhoGoesNext whoGoesNext = new WhoGoesNext(Player.O);
        //then
        assertEquals(whoGoesNext.getNextPlayer(), Player.X);
    }
}