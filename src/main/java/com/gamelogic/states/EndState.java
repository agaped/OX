package com.gamelogic.states;

import com.gamelogic.GameConfig;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class EndState implements GameState {
    @Override
    public void beginCurrentState(Consumer<String> output) {
        output.accept("\nThanks for playing!\nStatistics:....");
    }

    @Override
    public GameState moveToTheNextState(Supplier<String> userInputProvider, Consumer<String> output) {
        return new InitialState(new GameConfig());
    }
}
