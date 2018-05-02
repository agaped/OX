package com.gamelogic.states;

import com.gamelogic.core.GameConfig;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class EndState implements GameState {
    @Override
    public void beginCurrentState(Consumer<String> output, Supplier<String> userInputProvider) {
        output.accept("\nThanks for playing!\nStatistics:....");
        output.accept("Dou you want to play again? y/n");
    }

    @Override
    public GameState moveToTheNextState(Supplier<String> userInputProvider, Consumer<String> output) {
        if (userInputProvider.get().equals("y")) {
            return new InitialState(new GameConfig());
        } else {
            return null;
        }
    }
}
