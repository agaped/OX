package com.ox.states;

import com.ox.core.*;
import com.ox.validators.GameConfigValidator;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class InitialState implements GameState {

    private GameConfig gameConfig;
    private ScoreBoard scoreBoard;
    private GameConfigValidator gameConfigValidator;

    public InitialState(GameConfig gameConfig, ScoreBoard scoreBoard, GameConfigValidator gameConfigValidator) {
        this.gameConfig = gameConfig;
        this.scoreBoard = scoreBoard;
        this.gameConfigValidator = gameConfigValidator;
    }

    @Override
    public void beginCurrentState(Consumer<String> output, Supplier<String> userInputProvider) {
        gameConfig.setBoardSize(output, userInputProvider, gameConfigValidator);
        gameConfig.setLengthOfCombinationToWin(output, userInputProvider, gameConfigValidator);
    }

    @Override
    public GameState moveToTheNextState(Supplier<String> userInputProvider, Consumer<String> output) {
        output.accept("Who shall start, X or O?");
        String userInput = userInputProvider.get();
        while (!userInput.matches("[XO]")) {
            output.accept("Wrong character, choose X or O");
            userInput = userInputProvider.get();
        }
        Player startingPlayer = Player.valueOf(userInput);

        return new PlayState(startingPlayer, new Board(gameConfig), new VictoryChecker(), gameConfig, scoreBoard);
    }

}
