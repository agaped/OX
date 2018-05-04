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
    public void checkIfMoveToTheNextStateWorksForVictoryState(){
        //given
        GameState victory=new VictoryState(Player.X, new ScoreBoard());
        //when
        victory=victory.moveToTheNextState(new Scanner(System.in)::nextLine,System.out::println);

        //then
        assertEquals(victory.getClass(), new InitialState(new GameConfig(), new ScoreBoard()).getClass());
    }

}
