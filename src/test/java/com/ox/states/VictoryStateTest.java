package com.ox.states;

import com.ox.core.*;
import com.ox.validators.GameConfigValidator;
import org.testng.annotations.Test;

import java.util.Scanner;

import static org.testng.Assert.assertEquals;

public class VictoryStateTest {

    @Test
    public void checkIfMoveToTheNextStateWorksForVictoryState() {
        //given
        GameState victory = new VictoryState(Player.X, new ScoreBoard(), new GameConfig(), Player.X);
        //when
        victory = victory.moveToTheNextState(new Scanner(System.in)::nextLine, System.out::println);

        //then
        assertEquals(victory.getClass(), new PlayState(Player.X, Player.X.getOppositePlayer(), new Board(new GameConfig()), new VictoryChecker(), new GameConfig(), new ScoreBoard()).getClass());
    }

}
