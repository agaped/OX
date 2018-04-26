package com.gamelogic.states;

import java.util.function.Consumer;

public interface GameState {

    void beginCurrentState(Consumer<String> output);

    GameState moveToTheNextState(String userInput);
}
