package com.gamelogic.states;

import com.gamelogic.Board;
import com.gamelogic.GameConfig;
import com.gamelogic.Player;
import com.gamelogic.VictoryChecker;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class InitialStateTest {


    @Test
    public void checkIfMoveToTheNextStateWorksForInitialState(){
        //given
        GameState init=new InitialState(new GameConfig());

        //when
        init=init.moveToTheNextState("X");

        //then
        assertEquals(init.getClass(), new PlayState(Player.X,new Board(new GameConfig()), new VictoryChecker()).getClass());
    }
}