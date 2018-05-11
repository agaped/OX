package com.ox.states;

import com.ox.coordinates.BoardFieldCoordinate;
import com.ox.core.*;
import com.ox.language.Language;
import com.ox.validators.PlayerMoveValidator;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class PlayState implements GameState {

    private Player currentPlayer;
    private Player nextPlayer;
    private Board board;
    private VictoryChecker victoryChecker;
    private GameConfig gameConfig;
    private ScoreBoard scoreBoard;
    private BoardPrinter boardPrinter;

    public PlayState(Player startingPlayer,Player nextPlayer, Board board, VictoryChecker victoryChecker, GameConfig gameConfig, ScoreBoard scoreBoard) {
        this.currentPlayer = startingPlayer;
        this.nextPlayer = nextPlayer;
        this.board = board;
        this.victoryChecker = victoryChecker;
        this.gameConfig = gameConfig;
        this.scoreBoard = scoreBoard;
        this.boardPrinter=new BoardPrinter(board);
    }

    @Override
    public void beginCurrentState(Consumer<String> output, Supplier<String> userInputProvider) {
        if (!this.board.isBoardFull()) {
            output.accept(Language.get("playPlayer") + " " + currentPlayer.getPlayerName() + Language.get("playMove"));
        }

        this.boardPrinter.printBoardState(output);
    }

    @Override
    public GameState moveToTheNextState(Supplier<String> userInputProvider, Consumer<String> output) {
        if (this.board.isBoardFull()) {
            return new DrawState(scoreBoard, currentPlayer, nextPlayer, gameConfig);
        }

        String input = userInputProvider.get();
        if (!PlayerMoveValidator.validateInput(input, output)) {
            return this;
        }

        if (!PlayerMoveValidator.validateMoveAccordingToBoardState(BoardFieldCoordinate.parse(input), board, output)) {
            return this;
        } else {
            this.board.addMove(BoardFieldCoordinate.parse(input), currentPlayer);
        }

        Optional<Player> optionalWinner = victoryChecker.isThereAWinner(BoardFieldCoordinate.parse(input), this.board, this.currentPlayer, this.gameConfig);
        if (optionalWinner.isPresent()) {
            return new VictoryState(optionalWinner.get(), scoreBoard, gameConfig, nextPlayer);
        } else {
            currentPlayer = currentPlayer.getOppositePlayer();
            return this;
        }
    }

}
