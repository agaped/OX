package com.gamelogic.states;

import com.gamelogic.GameConfig;

import java.util.function.Consumer;

public class EndState implements GameState {
    @Override
    public void beginCurrentState(Consumer<String> output) {
        output.accept("\nThanks for playing!\nStatistics:....\nDo you want to play again? y/n");
    }

    @Override
    public GameState moveToTheNextState(String userInput) {
//        if (userInput.equals("y"))
//            return new InitialState(new GameConfig());
//        else return null;
        return new InitialState(new GameConfig());
    }
}
