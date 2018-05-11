package com.ox;

import com.ox.core.GameConfig;
import com.ox.core.Player;
import com.ox.core.ScoreBoard;
import com.ox.core.TurnNumber;
import com.ox.states.*;
import com.ox.validators.GameConfigValidator;
import com.ox.validators.LanguageValidator;
import com.ox.validators.PlayerNameAndSignValidator;

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
        int turn = 1;

        output.accept("Welcome in XO game!!!");
        this.currentState = new InitialState(gameConfig, scoreBoard, new GameConfigValidator(), new PlayerNameAndSignValidator(), new LanguageValidator());

        while (turn <= TurnNumber.TURN_NUMBER.getMaxTurnNumberInOneGame()) {
            if (this.currentState.getClass().equals((new VictoryState(Player.X,scoreBoard, gameConfig, Player.X)).getClass()) || this.currentState.getClass().equals((new DrawState(scoreBoard,Player.X, Player.X, gameConfig)).getClass()) ) {
                turn++;
            }
            playGame();
        }

        this.currentState = new EndState(scoreBoard);
        playGame();
    }

    private void playGame() {
        this.currentState.beginCurrentState(output, userInputProvider);
        this.currentState = this.currentState.moveToTheNextState(userInputProvider, output);
    }
}
