package com.gamelogic.states;

import com.gamelogic.coordinates.BoardFieldCoordinate;
import com.gamelogic.core.NewBoard;
import com.gamelogic.core.Player;
import com.gamelogic.core.VictoryChecker;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class PlayState implements GameState {

    private Player currentPlayer;
    private NewBoard board;
    private VictoryChecker victoryChecker;

    public PlayState(Player startingPlayer, NewBoard board, VictoryChecker victoryChecker) {
        this.currentPlayer = startingPlayer;
        this.board = board;
        this.victoryChecker = victoryChecker;
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
            return new DrawState();
        }

        String input = userInputProvider.get();
        if (!input.matches("[1-9][0-9]*")) {
            output.accept("Wrong input! Provide a move as a single number");
            return this;
        }

        if(!this.board.addMove(BoardFieldCoordinate.parse(input), currentPlayer, output)){
            return this;
        }

        Optional<Player> optionalWinner = victoryChecker.isThereAWinner();
        if (optionalWinner.isPresent()) {
            return new VictoryState(optionalWinner.get());
        } else {
            currentPlayer = currentPlayer.getOppositePlayer();
            return this;
        }
    }

}
