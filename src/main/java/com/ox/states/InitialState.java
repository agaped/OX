package com.ox.states;

import com.ox.core.*;
import com.ox.language.Language;
import com.ox.language.LanguageLoader;
import com.ox.validators.GameConfigValidator;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class InitialState implements GameState {

    private GameConfig gameConfig;
    private ScoreBoard scoreBoard;
    private GameConfigValidator gameConfigValidator;
    private Language lan;
    private LanguageLoader loader;
    private Player startingPlayer;
    private Player nextPlayer;

    public InitialState(GameConfig gameConfig, ScoreBoard scoreBoard, GameConfigValidator gameConfigValidator) {
        this.gameConfig = gameConfig;
        this.scoreBoard = scoreBoard;
        this.gameConfigValidator = gameConfigValidator;
    }

    @Override
    public void beginCurrentState(Consumer<String> output, Supplier<String> userInputProvider) {
        output.accept("Do you want to change default settings? y/n");
        String setup = userInputProvider.get();
        setup = validateDefaultSetup(output, userInputProvider, setup);
        if (setup.equals("n")) {
            lan = new Language();
            loader = new LanguageLoader(lan, "en");
            loader.load();
            gameConfig.setDefaultBoardSize();
            gameConfig.setDefaultLengthOfCombinationToWin();
            startingPlayer = Player.valueOf("X");
            startingPlayer.setPlayerName("X");
            startingPlayer.getOppositePlayer().setPlayerName("O");
            returnNextPlayer();

        } else {
            chooseLanguage(output, userInputProvider);
            gameConfig.setBoardSize(output, userInputProvider, gameConfigValidator);
            gameConfig.setLengthOfCombinationToWin(output, userInputProvider, gameConfigValidator);

            output.accept(Language.get("setPlayerNameForX"));
            Player.X.setPlayerName(userInputProvider.get());
            output.accept(Language.get("setPlayerNameForO"));
            Player.O.setPlayerName(userInputProvider.get());

            output.accept(Language.get("initWhoStarts"));
            String userInput = userInputProvider.get();
            userInput = validateGivenSign(output, userInputProvider, userInput);
            startingPlayer = Player.valueOf(userInput);
            returnNextPlayer();
        }
    }

    private String validateGivenSign(Consumer<String> output, Supplier<String> userInputProvider, String userInput) {
        while (!userInput.matches("[XO]")) {
            output.accept(Language.get("initWrongChar"));
            userInput = userInputProvider.get();
        }
        return userInput;
    }

    private String validateDefaultSetup(Consumer<String> output, Supplier<String> userInputProvider, String setup) {
        while (!setup.matches("[y|n]")) {
            output.accept("Do you want to change default settings? y/n");
            setup = userInputProvider.get();
        }
        return setup;
    }

    private void returnNextPlayer() {
        WhoGoesNext whoGoesNext = new WhoGoesNext(startingPlayer);
        nextPlayer = whoGoesNext.getNextPlayer();
    }

    @Override
    public GameState moveToTheNextState(Supplier<String> userInputProvider, Consumer<String> output) {
        return new PlayState(startingPlayer, nextPlayer, new Board(gameConfig), new VictoryChecker(), gameConfig, scoreBoard);
    }

    private void chooseLanguage(Consumer<String> output, Supplier<String> userInputProvider) {
        String language = validateLanguageChosenByPlayer(userInputProvider, output);
        Language lan = new Language();
        LanguageLoader loader = new LanguageLoader(lan, language);
        loader.load();
    }

    private String validateLanguageChosenByPlayer(Supplier<String> userInputProvider, Consumer<String> output) {
        output.accept("Choose language: en/pl");
        String input = userInputProvider.get();
        while (!input.matches("(en|pl)")) {
            output.accept("Wrong input. Choose again");
            input = userInputProvider.get();
        }
        return input;
    }

}
