package com.gamelogic.states;

import com.gamelogic.GameConfig;
import com.gamelogic.Player;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.testng.Assert.assertEquals;

public class VictoryStateTest {

    @Test
    public void checkIfMoveToTheNextStateWorksForVictoryState_moveToInitialState(){
        //given
        GameState victory=new VictoryState(Player.X);
        String input = "1 1";
        InputStream in=new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        //when
        victory=victory.moveToTheNextState(new Scanner(System.in)::nextLine,System.out::println);

        //then
        assertEquals(victory.getClass(), new EndState().getClass());
    }

    @Test
    public void checkIfMoveToTheNextStateWorksForVictoryState_moveToEndState(){
        //given
        GameState victory=new VictoryState(Player.X);
        String input = "y";
        InputStream in=new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        //when
        victory=victory.moveToTheNextState(new Scanner(System.in)::nextLine,System.out::println);

        //then
        assertEquals(victory.getClass(), new InitialState(new GameConfig()).getClass());
    }
}
