package com.ox.states;

import com.ox.core.GameConfig;
import com.ox.core.Player;
import com.ox.core.ScoreBoard;
import org.testng.annotations.Test;

import java.util.Scanner;

import static org.testng.Assert.*;

public class DrawStateTest {

    @Test
    public void checkIfMoveToTheNextStateWorksForDrawState(){
        //given
        GameState draw =new DrawState(new ScoreBoard(), Player.X);

        //when
        draw = draw.moveToTheNextState(new Scanner(System.in)::nextLine,System.out::println);

        //then
        assertEquals(draw.getClass(), new InitialState(new GameConfig(), new ScoreBoard()).getClass());
    }
}