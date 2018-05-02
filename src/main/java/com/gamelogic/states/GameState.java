package com.gamelogic.states;

import java.util.function.Consumer;
import java.util.function.Supplier;

public interface GameState {

    void beginCurrentState(Consumer<String> output, Supplier<String> userInputProvider);

    GameState moveToTheNextState(Supplier<String> userInputProvider, Consumer<String> output);


}
