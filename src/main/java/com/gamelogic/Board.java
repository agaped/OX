package com.gamelogic;

import java.util.Arrays;
import java.util.function.Consumer;

public class Board {

    private char emptyChar = '\u0000';
    private char plusChar = '\u002B';
    private GameConfig gameConfig;
    private char[][] boardState;


    public Board(GameConfig gameConfig) {
        this.gameConfig = gameConfig;
        this.boardState = new char[gameConfig.getBoardLength()][gameConfig.getBoardWidth()];

        for (char[] row : this.boardState) {
            Arrays.fill(row, plusChar);
        }
    }

    public void addMove(Coordinates coordinates, Player currentPlayer, Consumer<String> output) {
        int x = coordinates.getX() - 1;
        int y = coordinates.getY() - 1;
        if (checkIfUserInputIsCorrect(x, y)) {
            if (checkIfMoveIsPossible(x, y)) {
                this.boardState[x][y] = currentPlayer.name().charAt(0);
            } else {
                output.accept("Move not possible! Loosing turn....\n");
            }
        } else {
            output.accept("Wrong input! Loosing turn....\n");
        }
    }

    private boolean checkIfUserInputIsCorrect(int x, int y) {
        if (x >= 0 && x < boardState.length && y >= 0 && y < boardState[0].length) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkIfMoveIsPossible(int x, int y) {
        if (this.boardState[x][y] == this.plusChar) {
            return true;
        } else {
            return false;
        }
    }

    public void printState(Consumer<String> output) {
        for (int i = 0; i < this.boardState.length; i++) {
            output.accept(String.valueOf(this.boardState[i]));
        }
        output.accept(" ");

    }
}
