package com.ox.states;

import com.ox.Game;
import com.ox.core.GameConfig;
import com.ox.core.Player;
import com.ox.core.ScoreBoard;

import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class EndState implements GameState {

    private ScoreBoard scoreBoard;

    public EndState(ScoreBoard scoreBoard) {
        this.scoreBoard = scoreBoard;
    }


    @Override
    public void beginCurrentState(Consumer<String> output, Supplier<String> userInputProvider) {
        output.accept("\nThanks for playing!\n\nStatistics:");
        for (Player key: this.scoreBoard.getScores().keySet()) {
             output.accept("Player "+key+" points: "+this.scoreBoard.getScores().get(key));
        }
        output.accept("\nDou you want to play again? y/n");
    }

    @Override
    public GameState moveToTheNextState(Supplier<String> userInputProvider, Consumer<String> output) {
        if (userInputProvider.get().equals("y")) {
            new Game(new Scanner(System.in)::nextLine, System.out::println, new GameConfig(), new ScoreBoard()).start();
            return null;
        } else {
            return null;
        }
    }
}
