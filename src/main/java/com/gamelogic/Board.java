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

    //todo: probably move to Message/Error handling State
    public void addMove(Coordinates coordinates, Player currentPlayer, Consumer<String> output) {
        int x = coordinates.getX() - 1;
        int y = coordinates.getY() - 1;
        if (checkIfUserInputIsCorrect(x, y)) {
            if (checkIfMoveIsPossible(x, y)) {
                this.boardState[y][x] = currentPlayer.name().charAt(0);
            } else {
                output.accept("Move not possible! Loosing turn....\n");
            }
        } else {
            output.accept("Wrong input! Loosing turn....\n");
        }
    }

    private boolean checkIfUserInputIsCorrect(int x, int y) {
        if (y >= 0 && y < boardState.length && x >= 0 && x < boardState[0].length) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkIfMoveIsPossible(int x, int y) {
        if (this.boardState[y][x] == this.plusChar) {
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

    public boolean isBoardFull() {
        for (int i = 0; i < boardState.length; i++) {
            for (int j = 0; j < boardState[0].length; j++) {
                if (checkIfMoveIsPossible(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }
}
