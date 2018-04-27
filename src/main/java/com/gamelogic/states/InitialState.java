package com.gamelogic.states;

import com.gamelogic.Board;
import com.gamelogic.GameConfig;
import com.gamelogic.Player;
import com.gamelogic.VictoryChecker;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class InitialState implements GameState {

    private GameConfig gameConfig;

    public InitialState(GameConfig gameConfig) {
        this.gameConfig = gameConfig;
    }

    @Override
    public void beginCurrentState(Consumer<String> output) {
        output.accept("Who shall start, X or O?");
    }

    @Override
    public GameState moveToTheNextState(Supplier<String> userInputProvider, Consumer<String> output) {
        String userInput = userInputProvider.get();
        if (!userInput.matches("[XO]")) {
            output.accept("Wrong character, choose X or O");
            //todo: probably move to Message/Error handling State
            return this;
        }

        Player startingPlayer = Player.valueOf(userInput);
        return new PlayState(startingPlayer, new Board(gameConfig), new VictoryChecker());
    }

}
