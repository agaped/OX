package com.ox.states;

import com.ox.core.*;
import com.ox.coordinates.BoardFieldCoordinate;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class PlayState implements GameState {

    private Player currentPlayer;
    private NewBoard board;
    private VictoryChecker victoryChecker;
    private GameConfig gameConfig;
    private ScoreBoard scoreBoard;

    public PlayState(Player startingPlayer, NewBoard board, VictoryChecker victoryChecker, GameConfig gameConfig, ScoreBoard scoreBoard) {
        this.currentPlayer = startingPlayer;
        this.board = board;
        this.victoryChecker = victoryChecker;
        this.gameConfig = gameConfig;
        this.scoreBoard = scoreBoard;
    }

    @Override
    public void beginCurrentState(Consumer<String> output, Supplier<String> userInputProvider) {
        output.accept("");
        this.board.printState(output);
        if (!this.board.isBoardFull())
            output.accept("Player " + currentPlayer + ", make your move");
    }

    //todo: probably move to Message/Error handling State
    @Override
    public GameState moveToTheNextState(Supplier<String> userInputProvider, Consumer<String> output) {
        if (this.board.isBoardFull()) {
            return new DrawState(scoreBoard, currentPlayer);
        }

        String input = userInputProvider.get();
        if (!input.matches("[1-9][0-9]*")) {
            output.accept("Wrong input! Provide a move as a single number");
            return this;
        }

        if(!this.board.addMove(BoardFieldCoordinate.parse(input), currentPlayer, output)){
            return this;
        }

        Optional<Player> optionalWinner = victoryChecker.isThereAWinner(BoardFieldCoordinate.parse(input),this.board,this.currentPlayer,this.gameConfig);
//        Optional<Player> optionalWinner = RowCondition.isThereAVictory(BoardFieldCoordinate.parse(input),this.board,this.currentPlayer,this.gameConfig);
        if (optionalWinner.isPresent()) {
            return new VictoryState(optionalWinner.get(), scoreBoard);
        } else {
            currentPlayer = currentPlayer.getOppositePlayer();
            return this;
        }
    }

}
