package com.ox.states;

import com.ox.core.*;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class InitialState implements GameState {

    private GameConfig gameConfig;
    private ScoreBoard scoreBoard;

    public InitialState(GameConfig gameConfig, ScoreBoard scoreBoard) {
        this.gameConfig = gameConfig;
        this.scoreBoard = scoreBoard;
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

        return new PlayState(startingPlayer, new NewBoard(gameConfig), new VictoryChecker(), gameConfig, scoreBoard);
    }

}
