package com.ox.states;

import com.ox.language.Language;
import com.ox.core.GameConfig;
import com.ox.core.Judge;
import com.ox.core.Player;
import com.ox.core.ScoreBoard;
import com.ox.validators.GameConfigValidator;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class VictoryState implements GameState {

    private Player winner;
    private ScoreBoard scoreBoard;

    public VictoryState(Player winner, ScoreBoard scoreBoard) {
        this.winner = winner;
        this.scoreBoard = scoreBoard;
    }


    @Override
    public void beginCurrentState(Consumer<String> output, Supplier<String> userInputProvider) {
        output.accept(Language.get("vic") +" "+ winner + "\n");
        scoreBoard.givePoints(winner, Judge.WINNER.getPoints());
        scoreBoard.givePoints(winner.getOppositePlayer(), Judge.DEFEAT.getPoints());
    }

    @Override
    public GameState moveToTheNextState(Supplier<String> userInputProvider, Consumer<String> output) {
        return new InitialState(new GameConfig(), scoreBoard, new GameConfigValidator());
    }
}
