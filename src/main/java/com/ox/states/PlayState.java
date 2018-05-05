package com.ox.states;

import com.ox.validators.PlayerMoveValidator;
import com.ox.core.*;
import com.ox.coordinates.BoardFieldCoordinate;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class PlayState implements GameState {

    private Player currentPlayer;
    private Board board;
    private VictoryChecker victoryChecker;
    private GameConfig gameConfig;
    private ScoreBoard scoreBoard;

    public PlayState(Player startingPlayer, Board board, VictoryChecker victoryChecker, GameConfig gameConfig, ScoreBoard scoreBoard) {
        this.currentPlayer = startingPlayer;
        this.board = board;
        this.victoryChecker = victoryChecker;
        this.gameConfig = gameConfig;
        this.scoreBoard = scoreBoard;
    }

    @Override
    public void beginCurrentState(Consumer<String> output, Supplier<String> userInputProvider) {
        output.accept("");
        this.board.printBoardState(output);
        if (!this.board.isBoardFull())
            output.accept("Player " + currentPlayer + ", make your move");
    }

    @Override
    public GameState moveToTheNextState(Supplier<String> userInputProvider, Consumer<String> output) {
        if (this.board.isBoardFull()) {
            return new DrawState(scoreBoard, currentPlayer);
        }

        String input = userInputProvider.get();
        if (!PlayerMoveValidator.validateInput(input,output)) {
            return this;
        }

        if(!PlayerMoveValidator.validateMoveAccordingToBoardState(BoardFieldCoordinate.parse(input),board,output)){
            return this;
        }else {
            this.board.addMove(BoardFieldCoordinate.parse(input), currentPlayer);
        }

        Optional<Player> optionalWinner = victoryChecker.isThereAWinner(BoardFieldCoordinate.parse(input),this.board,this.currentPlayer,this.gameConfig);
        if (optionalWinner.isPresent()) {
            return new VictoryState(optionalWinner.get(), scoreBoard);
        } else {
            currentPlayer = currentPlayer.getOppositePlayer();
            return this;
        }
    }

}
