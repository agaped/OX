package com.gamelogic.states;

import com.gamelogic.GameConfig;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class EndStateTest {
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
