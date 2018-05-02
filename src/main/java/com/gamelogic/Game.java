package com.gamelogic;

import com.gamelogic.core.GameConfig;
import com.gamelogic.core.TurnNumber;
import com.gamelogic.states.EndState;
import com.gamelogic.states.GameState;
import com.gamelogic.states.InitialState;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Game {

    private GameState currentState;
    private Supplier<String> userInputProvider;
    private Consumer<String> output;
    private GameConfig gameConfig;
    private TurnNumber turnNumber;
    private int turnNumberInOneGame=3;

    public Game(Supplier<String> userInputProvider, Consumer<String> output, GameConfig gameConfig) {
        this.userInputProvider = userInputProvider;
        this.output = output;
        this.gameConfig = gameConfig;
    }

    public void start() {
        //todo: game configuration to implement
        int turn=1;
        output.accept("Welcome to OX game!!!\nSome setup at the beginning...\n");
        this.currentState=new InitialState(gameConfig);

        while (turn <=turnNumberInOneGame) {
            playGame();
            if(this.currentState.getClass().equals(new InitialState(gameConfig).getClass())){
                turn++;
            }
        }

        this.currentState=new EndState();
        playGame();
    }

    private void playGame() {
        this.currentState.beginCurrentState(output, userInputProvider);
        this.currentState=this.currentState.moveToTheNextState(userInputProvider,output);
    }
}
