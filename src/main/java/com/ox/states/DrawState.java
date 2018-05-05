package com.ox.states;

import com.ox.validators.GameConfigValidator;
import com.ox.core.*;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class DrawState implements GameState {

    private ScoreBoard scoreBoard;
    private Player player;

    public DrawState(ScoreBoard scoreBoard, Player player) {
        this.scoreBoard = scoreBoard;
        this.player = player;
    }

    @Override
    public void beginCurrentState(Consumer<String> output, Supplier<String> userInputProvider) {
        output.accept("Draw!!!\n");
        scoreBoard.givePoints(player, Judge.DRAW.getPoints());
        scoreBoard.givePoints(player.getOppositePlayer(),Judge.DRAW.getPoints());
    }

    @Override
    public GameState moveToTheNextState(Supplier<String> userInputProvider, Consumer<String> output) {
        return new InitialState(new GameConfig(), scoreBoard, new GameConfigValidator());
    }
}
