package com.gamelogic.states;

import com.gamelogic.Board;
import com.gamelogic.Coordinates;
import com.gamelogic.Player;
import com.gamelogic.VictoryChecker;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class PlayState implements GameState {

    private Player currentPlayer;
    private Board board;
    private VictoryChecker victoryChecker;

    public PlayState(Player startingPlayer, Board board, VictoryChecker victoryChecker) {
        this.currentPlayer = startingPlayer;
        this.board = board;
        this.victoryChecker = victoryChecker;
    }

    @Override
    public void beginCurrentState(Consumer<String> output, Supplier<String> userInputProvider) {
        this.board.printState(output);
        output.accept("Player " + currentPlayer + ", make your move");
    }

    @Override
    public GameState moveToTheNextState(Supplier<String> userInputProvider, Consumer<String> output) {
        String input=userInputProvider.get();
        if(!input.matches("[1-9][0-9]*[\" \"][1-9][0-9]*")){
            output.accept("Wrong input! Provide move in a form: x y");
            return this;
        }
        this.board.addMove(Coordinates.parse(input), currentPlayer, output);

            Optional<Player> optionalWinner= victoryChecker.isThereAWinner();
                    if(optionalWinner.isPresent()){
                        return new VictoryState(optionalWinner.get());
                    }else {
                        currentPlayer = currentPlayer.getOppositePlayer();
                        return this;
                    }
    }

}
