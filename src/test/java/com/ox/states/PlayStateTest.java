package com.ox.states;

import com.ox.core.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Scanner;

import static org.testng.Assert.assertEquals;

public class PlayStateTest {

    @BeforeMethod
    public void setUp() {
    }

    @AfterMethod
    public void tearDown() {
    }


    @Test
    public void checkIfMoveToTheNextStateWorksForPlayState_staysAtPlayState() {
        //given
        GameState play = new PlayState(Player.X, new Board(new GameConfig()), new VictoryChecker(), new GameConfig(), new ScoreBoard());

        //when
        play = play.moveToTheNextState(new Scanner(System.in)::nextLine, System.out::println);


        //then
        assertEquals(play.getClass(), new DrawState(new ScoreBoard(), Player.X).getClass());

    }


}