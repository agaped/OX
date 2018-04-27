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
    public void beginCurrentState(Consumer<String> output, Supplier<String> userInputProvider) {
        gameConfig.setBoardSize(output, userInputProvider);
        gameConfig.setNumberCombinationToWin(output, userInputProvider);
    }

    @Override
    public GameState moveToTheNextState(Supplier<String> userInputProvider, Consumer<String> output) {
        output.accept("Who shall start, X or O?");
        String userInput = userInputProvider.get();
        while (!userInput.matches("[XO]"))
        {
            output.accept("Wrong character, choose X or O");
            userInput = userInputProvider.get();
            //todo: probably move to Message/Error handling State
        }
        Player startingPlayer = Player.valueOf(userInput);

        return new PlayState(startingPlayer, new Board(gameConfig), new VictoryChecker());
    }

}
