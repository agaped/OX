package com.gamelogic.states;

import com.gamelogic.Board;
import com.gamelogic.GameConfig;
import com.gamelogic.Player;
import com.gamelogic.VictoryChecker;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PlayStateTest {

    @BeforeMethod
    public void setUp() {
    }

    @AfterMethod
    public void tearDown() {
    }


    @Test
    public void checkIfMoveToTheNextStateWorksForPlayState_staysAtPlayState(){
        //given
        GameState play=new PlayState(Player.X,new Board(), new VictoryChecker());

        //when
        play=play.moveToTheNextState("1 1");


        //then
        assertEquals(play.getClass(), new PlayState(Player.X,new Board(), new VictoryChecker()).getClass());
    }

    @Test
    public void checkIfMoveToTheNextStateWorksForPlayState_moveToVictoryState(){
        //given
        GameState play=new PlayState(Player.X,new Board(), new VictoryChecker());

        //when
        play=play.moveToTheNextState("1 1");
        play=play.moveToTheNextState("1 1");
        play=play.moveToTheNextState("1 1");
        play=play.moveToTheNextState("1 1");

        //then
        assertEquals(play.getClass(), new VictoryState(Player.X).getClass());
    }
    



}