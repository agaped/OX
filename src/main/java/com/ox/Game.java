package com.ox;

import com.ox.core.GameConfig;
import com.ox.core.ScoreBoard;
import com.ox.core.TurnNumber;
import com.ox.language.Language;
import com.ox.language.LanguageLoader;
import com.ox.states.EndState;
import com.ox.states.GameState;
import com.ox.states.InitialState;
import com.ox.validators.GameConfigValidator;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Game {

    private GameState currentState;
    private Supplier<String> userInputProvider;
    private Consumer<String> output;
    private GameConfig gameConfig;
    private ScoreBoard scoreBoard;

    public Game(Supplier<String> userInputProvider, Consumer<String> output, GameConfig gameConfig, ScoreBoard scoreBoard) {
        this.userInputProvider = userInputProvider;
        this.output = output;
        this.gameConfig = gameConfig;
        this.scoreBoard = scoreBoard;
    }

    public void start() {
        chooseLanguage();
        int turn = 1;

        output.accept(Language.get("gameWelcome"));
        output.accept(Language.get("gameSetup"));
        this.currentState = new InitialState(gameConfig, scoreBoard, new GameConfigValidator());

        while (turn <= TurnNumber.TURN_NUMBER.getMaxTurnNumberInOneGame()) {
            playGame();
            if (this.currentState.getClass().equals(new InitialState(gameConfig, scoreBoard, new GameConfigValidator()).getClass())) {
                turn++;
            }
        }

        this.currentState = new EndState(scoreBoard);
        playGame();
    }

    private void chooseLanguage() {
        String language = "src/main/java/com/ox/resources/" + validateLanguageChosenByPlayer();
        Language lan = new Language();
        LanguageLoader loader = new LanguageLoader(lan, language);
        loader.load();
    }

    private String validateLanguageChosenByPlayer() {
        output.accept("Choose language: en/pl");
        String input = userInputProvider.get();
        while (!input.matches("(en|pl)")) {
            output.accept("Wrong input. Choose again");
            input = userInputProvider.get();
        }
        return input;
    }

    private void playGame() {
        this.currentState.beginCurrentState(output, userInputProvider);
        this.currentState = this.currentState.moveToTheNextState(userInputProvider, output);
    }
}
