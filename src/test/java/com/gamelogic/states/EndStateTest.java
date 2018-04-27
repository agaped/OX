package com.gamelogic.states;

import com.gamelogic.GameConfig;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.testng.Assert.assertEquals;

public class EndStateTest {

    @Test
    public void checkIfMoveToTheNextStateWorksForEndState(){
        //given
        GameState end=new EndState();
        String input = "1 1";
        InputStream in=new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        //when
        end=end.moveToTheNextState(new Scanner(System.in)::nextLine,System.out::println);

        //then
        assertEquals(end.getClass(), new InitialState(new GameConfig()).getClass());
    }
}
