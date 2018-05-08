package com.ox.states;

import com.ox.core.GameConfig;
import com.ox.core.Judge;
import com.ox.core.Player;
import com.ox.core.ScoreBoard;
import com.ox.language.Language;
import com.ox.validators.GameConfigValidator;

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
        scoreBoard.givePoints(player, Judge.DRAW.getPoints());
        scoreBoard.givePoints(player.getOppositePlayer(), Judge.DRAW.getPoints());
        output.accept(Language.get("draw") +" "+player+":"+scoreBoard.getPlayerScores(player)+" ,"+player.getOppositePlayer()+":"+scoreBoard.getPlayerScores(player.getOppositePlayer())+ "\n");
    }

    @Override
    public GameState moveToTheNextState(Supplier<String> userInputProvider, Consumer<String> output) {
        return new InitialState(new GameConfig(), scoreBoard, new GameConfigValidator());
    }
}
