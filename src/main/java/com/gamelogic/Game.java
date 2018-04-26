package com.gamelogic;

import com.gamelogic.states.GameState;
import com.gamelogic.states.InitialState;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Game {

    private GameState currentState;
    private Supplier<String> userInputProvider;
    private Consumer<String> output;
    private GameConfig gameConfig;

    public Game(Supplier<String> userInputProvider, Consumer<String> output, GameConfig gameConfig) {
        this.userInputProvider = userInputProvider;
        this.output = output;
        this.gameConfig = gameConfig;
    }

    public void start() {
        //todo: game configuration to implement
        this.currentState=new InitialState(gameConfig);
        gameConfig.setBoardSize(output,userInputProvider);
        while (true) {
            playGame();
        }
    }

    private void playGame() {
        this.currentState.beginCurrentState(output);
        this.currentState=this.currentState.moveToTheNextState(userInputProvider.get());
    }
}
