package com.ox;

import com.ox.core.GameConfig;
import com.ox.core.ScoreBoard;
import com.ox.states.EndState;
import com.ox.states.GameState;
import com.ox.states.InitialState;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Game {

    private GameState currentState;
    private Supplier<String> userInputProvider;
    private Consumer<String> output;
    private GameConfig gameConfig;
    private int turnNumberInOneGame = 3;
    private ScoreBoard scoreBoard;

    public Game(Supplier<String> userInputProvider, Consumer<String> output, GameConfig gameConfig, ScoreBoard scoreBoard) {
        this.userInputProvider = userInputProvider;
        this.output = output;
        this.gameConfig = gameConfig;
        this.scoreBoard = scoreBoard;
    }

    public void start() {
        //todo: game configuration to implement
        int turn = 1;
        output.accept("Welcome to OX game!!!\nSome setup at the beginning...\n");
        this.currentState = new InitialState(gameConfig, scoreBoard);

        while (turn <= turnNumberInOneGame) {
            playGame();
            if (this.currentState.getClass().equals(new InitialState(gameConfig, scoreBoard).getClass())) {
                turn++;
            }
        }

        this.currentState = new EndState(scoreBoard);
        playGame();
    }

    private void playGame() {
        this.currentState.beginCurrentState(output, userInputProvider);
        this.currentState = this.currentState.moveToTheNextState(userInputProvider, output);
    }
}
