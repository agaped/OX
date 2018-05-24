package com.ox.states;

import com.ox.core.*;
import com.ox.language.Language;
import com.ox.language.LanguageLoader;
import com.ox.validators.GameConfigValidator;
import com.ox.validators.LanguageValidator;
import com.ox.validators.PlayerNameAndSignValidator;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class InitialState implements GameState {

    private GameConfig gameConfig;
    private ScoreBoard scoreBoard;
    private GameConfigValidator gameConfigValidator;
    private PlayerNameAndSignValidator playerNameAndSignValidator;
    private LanguageValidator languageValidator;
    private Language lan;
    private LanguageLoader loader;
    private Player startingPlayer;
    private Player nextPlayer;

    public InitialState(GameConfig gameConfig, ScoreBoard scoreBoard, GameConfigValidator gameConfigValidator, PlayerNameAndSignValidator playerNameAndSignValidator, LanguageValidator languageValidator) {
        this.gameConfig = gameConfig;
        this.scoreBoard = scoreBoard;
        this.gameConfigValidator = gameConfigValidator;
        this.playerNameAndSignValidator = playerNameAndSignValidator;
        this.languageValidator=languageValidator;
    }

    @Override
    public void beginCurrentState(Consumer<String> output, Supplier<String> userInputProvider) {
        output.accept("Do you want to change default settings (play with board 3x3 with default players X and O)? y/n");
        String setup = userInputProvider.get().trim();
        setup = validateDefaultSetup(output, userInputProvider, setup);
        if (setup.equals("n")) {
            loadDefaultSettings();
        } else {
            chooseLanguage(output, userInputProvider);
            gameConfig.setBoardSize(output, userInputProvider, gameConfigValidator);
            gameConfig.setLengthOfCombinationToWin(output, userInputProvider, gameConfigValidator);
            configurePlayersSettings(output, userInputProvider);
        }
    }

    @Override
    public GameState moveToTheNextState(Supplier<String> userInputProvider, Consumer<String> output) {
        output.accept("\n"+Language.get("quit")+"\n");
        return new PlayState(startingPlayer, nextPlayer, new Board(gameConfig), new VictoryChecker(), gameConfig, scoreBoard);
    }

    private String validateDefaultSetup(Consumer<String> output, Supplier<String> userInputProvider, String setup) {
        while (!setup.matches("[y|n]")) {
            output.accept("Do you want to change default settings (play with board 3x3 with default players X and O)? y/n");
            setup = userInputProvider.get().trim();
        }
        return setup;
    }

    private void loadDefaultSettings() {
        lan = new Language();
        loader = new LanguageLoader(lan, "en");
        loader.load();
        gameConfig.setDefaultBoardSize();
        gameConfig.setDefaultLengthOfCombinationToWin();
        startingPlayer = Player.valueOf("X");
        startingPlayer.setPlayerName("X");
        startingPlayer.getOppositePlayer().setPlayerName("O");
        returnNextPlayer();
    }

    private void chooseLanguage(Consumer<String> output, Supplier<String> userInputProvider) {
        String language = languageValidator.validateLanguageChosenByPlayer(userInputProvider, output);
        Language lan = new Language();
        LanguageLoader loader = new LanguageLoader(lan, language);
        loader.load();
    }

    private void configurePlayersSettings(Consumer<String> output, Supplier<String> userInputProvider) {
        playerNameAndSignValidator.validatePlayerName(output, userInputProvider, Player.X);
        playerNameAndSignValidator.validatePlayerName(output, userInputProvider, Player.O);

        output.accept(Language.get("initWhoStarts"));
        String userInput = userInputProvider.get().trim();
        userInput = playerNameAndSignValidator.validateGivenSign(output, userInputProvider, userInput);
        startingPlayer = Player.valueOf(userInput);
        returnNextPlayer();
    }

    private void returnNextPlayer() {
        WhoGoesNext whoGoesNext = new WhoGoesNext(startingPlayer);
        nextPlayer = whoGoesNext.getNextPlayer();
    }


}
