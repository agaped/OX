package com.gamelogic.states;

import com.gamelogic.core.GameConfig;
import com.gamelogic.core.NewBoard;
import com.gamelogic.core.Player;
import com.gamelogic.core.VictoryChecker;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.testng.Assert.*;

public class InitialStateTest {


    @Test
    public void checkIfMoveToTheNextStateWorksForInitialState_WhenCorrectInputProvided() {
        //given
        GameState init = new InitialState(new GameConfig());
        String input = "W\nX";
        InputStream in=new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        //when
        init = init.moveToTheNextState(new Scanner(System.in)::nextLine,System.out::println);

        //then
        assertEquals(init.getClass(), new PlayState(Player.X, new NewBoard(new GameConfig()), new VictoryChecker()).getClass());
    }
}