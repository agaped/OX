package com.ox.states;

import com.ox.core.*;
import com.ox.language.Language;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class DrawState implements GameState {

    private ScoreBoard scoreBoard;
    private Player player;
    private Player nextPlayer;
    private GameConfig gameConfig;

    public DrawState(ScoreBoard scoreBoard, Player player, Player nextPlayer, GameConfig gameConfig) {
        this.scoreBoard = scoreBoard;
        this.player = player;
        this.nextPlayer = nextPlayer;
        this.gameConfig = gameConfig;
    }

    @Override
    public void beginCurrentState(Consumer<String> output, Supplier<String> userInputProvider) {
        scoreBoard.givePoints(player, Judge.DRAW.getPoints());
        scoreBoard.givePoints(player.getOppositePlayer(), Judge.DRAW.getPoints());
        output.accept(Language.get("draw") +" "+player+":"+scoreBoard.getPlayerScores(player)+" ,"+player.getOppositePlayer()+":"+scoreBoard.getPlayerScores(player.getOppositePlayer())+ "\n");
    }

    @Override
    public GameState moveToTheNextState(Supplier<String> userInputProvider, Consumer<String> output) {
//        return new InitialState(new GameConfig(), scoreBoard, new GameConfigValidator());
        return new PlayState(nextPlayer, nextPlayer.getOppositePlayer(), new Board(gameConfig), new VictoryChecker(), gameConfig, scoreBoard);
    }
}
