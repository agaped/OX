package com.ox.core;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Map;

import static org.testng.Assert.assertEquals;

public class ScoreBoardTest {

    private ScoreBoard scoreBoard;
    private Map<Player, Integer> points;

    @BeforeMethod
    public void setUp() {
        scoreBoard = new ScoreBoard();
    }

    @Test
    public void checkIfGivePointsCorrectlyToX() {
        //given and when
        scoreBoard.givePoints(Player.X, 3);
        scoreBoard.givePoints(Player.X, 3);

        points = scoreBoard.getScores();
        //then
        assertEquals(points.get(Player.X), new Integer(6));
    }

    @Test
    public void checkIfGivePointsCorrectlyToO() {
        //given and when
        scoreBoard.givePoints(Player.X, 3);
        scoreBoard.givePoints(Player.O, 1);

        points = scoreBoard.getScores();
        //then
        assertEquals(points.get(Player.O), new Integer(1));
    }
}