package com.gamelogic.states;

import com.gamelogic.Board;
import com.gamelogic.GameConfig;
import com.gamelogic.Player;
import com.gamelogic.VictoryChecker;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class GameStateTest {

    @BeforeMethod
    public void setUp() {
    }

    @AfterMethod
    public void tearDown() {
    }

    @Test
    public void checkIfMoveToTheNextStateWorksForInitialState(){
        //given
        GameState init=new InitialState(new GameConfig());

        //when
        init=init.moveToTheNextState("X");

        //then
        assertEquals(init.getClass(), new PlayState(Player.X,new Board(), new VictoryChecker()).getClass());
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
    
    @Test
    public void checkIfMoveToTheNextStateWorksForVictoryState_moveToInitialState(){
        //given
        GameState victory=new VictoryState(Player.X);

        //when
        victory=victory.moveToTheNextState("y");

        //then
        assertEquals(victory.getClass(), new InitialState(new GameConfig()).getClass());
    }

    @Test
    public void checkIfMoveToTheNextStateWorksForVictoryState_moveToEndState(){
        //given
        GameState victory=new VictoryState(Player.X);

        //when
        victory=victory.moveToTheNextState("n");

        //then
        assertEquals(victory.getClass(), new EndState().getClass());
    }

    @Test
    public void checkIfMoveToTheNextStateWorksForEndState(){
        //given
        GameState end=new EndState();

        //when
        end=end.moveToTheNextState("X");

        //then
        assertEquals(end.getClass(), new InitialState(new GameConfig()).getClass());
    }
}