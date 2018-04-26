package com.gamelogic;

import java.util.function.Consumer;

public class InitialState implements GameState{

    private GameConfig gameConfig;

    public InitialState(GameConfig gameConfig) {
        this.gameConfig=gameConfig;
    }

    @Override
    public void beginCurrentState(Consumer<String> output) {
        output.accept("Welcome to XO, who shall start?");
    }

    @Override
    public GameState moveToTheNextState(String userInput) {
        Player startingPlayer = Player.valueOf(userInput);
        return new PlayState(startingPlayer, new Board());
    }

}
