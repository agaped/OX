package com.gamelogic.states;

import com.gamelogic.core.GameConfig;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class DrawState implements GameState {


    @Override
    public void beginCurrentState(Consumer<String> output, Supplier<String> userInputProvider) {
        output.accept("Draw!!!\n");
    }

    @Override
    public GameState moveToTheNextState(Supplier<String> userInputProvider, Consumer<String> output) {
        return new InitialState(new GameConfig());
    }
}
