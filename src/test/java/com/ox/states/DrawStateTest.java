package com.ox.states;

import com.ox.core.*;
import com.ox.validators.GameConfigValidator;
import org.testng.annotations.Test;

import java.util.Scanner;

import static org.testng.Assert.assertEquals;

public class DrawStateTest {

    @Test
    public void checkIfMoveToTheNextStateWorksForDrawState() {
        //given
        GameState draw = new DrawState(new ScoreBoard(), Player.X, Player.X, new GameConfig());

        //when
        draw = draw.moveToTheNextState(new Scanner(System.in)::nextLine, System.out::println);

        //then
        assertEquals(draw.getClass(), new PlayState(Player.X, Player.X.getOppositePlayer(), new Board(new GameConfig()), new VictoryChecker(), new GameConfig(), new ScoreBoard()).getClass());
    }
}