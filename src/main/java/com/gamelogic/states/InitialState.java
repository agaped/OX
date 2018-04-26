package com.gamelogic.states;

import com.gamelogic.Board;
import com.gamelogic.GameConfig;
import com.gamelogic.Player;
import com.gamelogic.VictoryChecker;

import java.util.function.Consumer;
import java.util.function.Supplier;

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
    public GameState moveToTheNextState(Supplier<String> userInputProvider, Consumer<String> output) {
        Player startingPlayer = Player.valueOf(userInputProvider.get());
        return new PlayState(startingPlayer, new Board(gameConfig), new VictoryChecker());
    }

}
