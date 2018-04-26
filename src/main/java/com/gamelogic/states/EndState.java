package com.gamelogic.states;

import com.gamelogic.Coordinates;
import com.gamelogic.GameConfig;

import java.util.function.Consumer;

public class EndState implements GameState {
    @Override
    public void beginCurrentState(Consumer<String> output) {
        output.accept("\nThanks for playing!\nStatistics:....");
    }

    @Override
    public GameState moveToTheNextState(String userInput) {
        return new InitialState(new GameConfig());
    }
}
