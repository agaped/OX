package com.ox.states;

import com.ox.core.GameConfig;
import com.ox.core.Player;
import com.ox.core.ScoreBoard;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.testng.Assert.assertEquals;

public class VictoryStateTest {

    @Test
    public void checkIfMoveToTheNextStateWorksForVictoryState_moveToInitialState(){
        //given
        GameState victory=new VictoryState(Player.X, new ScoreBoard());
        String input = "y";
        InputStream in=new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        //when
        victory=victory.moveToTheNextState(new Scanner(System.in)::nextLine,System.out::println);

        //then
        assertEquals(victory.getClass(), new InitialState(new GameConfig(), new ScoreBoard()).getClass());
    }
    @Test
    public void checkIfMoveToTheNextStateWorksForVictoryState_moveToEndState(){
        //given
        GameState victory=new VictoryState(Player.X, new ScoreBoard());
        String input = "n";
        InputStream in=new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        //when
        victory=victory.moveToTheNextState(new Scanner(System.in)::nextLine,System.out::println);

        //then
        assertEquals(victory.getClass(), new EndState(new ScoreBoard()).getClass());
    }
}
