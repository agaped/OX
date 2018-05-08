package com.ox.states;

import com.ox.core.*;
import com.ox.language.Language;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class VictoryState implements GameState {

    private Player winner;
    private Player startingPlayer;
    private ScoreBoard scoreBoard;
    private GameConfig gameConfig;

    public VictoryState(Player winner, ScoreBoard scoreBoard, GameConfig gameConfig, Player nextPlayer) {
        this.winner = winner;
        this.scoreBoard = scoreBoard;
        this.gameConfig = gameConfig;
        this.startingPlayer = nextPlayer;
    }


    @Override
    public void beginCurrentState(Consumer<String> output, Supplier<String> userInputProvider) {
        scoreBoard.givePoints(winner, Judge.WINNER.getPoints());
        scoreBoard.givePoints(winner.getOppositePlayer(), Judge.DEFEAT.getPoints());
        output.accept(Language.get("vic") + " " + winner+". "+winner+":"+scoreBoard.getPlayerScores(winner)+" ,"+winner.getOppositePlayer()+":"+scoreBoard.getPlayerScores(winner.getOppositePlayer())+"\n");
    }

    @Override
    public GameState moveToTheNextState(Supplier<String> userInputProvider, Consumer<String> output) {
//        return new InitialState(new GameConfig(), scoreBoard, new GameConfigValidator());
        return new PlayState(startingPlayer, startingPlayer.getOppositePlayer(), new Board(gameConfig), new VictoryChecker(), gameConfig, scoreBoard);
    }
}
