package com.gamelogic.states;

import com.gamelogic.Board;
import com.gamelogic.Coordinates;
import com.gamelogic.Player;

import java.util.function.Consumer;

public class PlayState implements GameState {

    private Player currentPlayer;
    private Board board;
    private int i=0;
    public PlayState(Player startingPlayer, Board board) {
        this.currentPlayer = startingPlayer;
        this.board = board;
    }

    @Override
    public void beginCurrentState(Consumer<String> output) {
        this.board.printState(output);
        output.accept("Player " + currentPlayer + ", make your move");
    }

    @Override
    public GameState moveToTheNextState(String userInput) {

        this.board.addMove(Coordinates.parse(userInput), currentPlayer);

        while(i++<3) {
            currentPlayer = currentPlayer.getOppositePlayer();
            return this;
        }
        return new EndState();
    }
}
