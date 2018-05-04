package com.ox.core;

import com.ox.coordinates.BoardDimensionCoordinates;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class GameConfig {

    private int numberCombinationToWin;
    private BoardDimensionCoordinates boardDimensionCoordinates;
    private int boardRow;
    private int boardColumn;

    public void setBoardSize(Consumer<String> output, Supplier<String> userInput) {
        String input;

        while (true) {
            output.accept("Provide board dimension - row and column: ");
            input = userInput.get();
            if (input.matches("[1-9][0-9]*[\" \"][1-9][0-9]*")) {
                boardDimensionCoordinates = BoardDimensionCoordinates.parse(input);
                this.boardRow = boardDimensionCoordinates.getX();
                this.boardColumn = boardDimensionCoordinates.getY();
                if (this.getBoardRow() * this.getBoardColumn() <= 10000) {
                    break;
                } else {
                    output.accept("Total size of the board cannot exceed 10 000. Try again");
                }
            } else {
                output.accept("Wrong format! Try again");
            }
        }
    }

    //todo: validate user input
    public void setNumberCombinationToWin(Consumer<String> output, Supplier<String> userInput) {
        while (true) {
            output.accept("Provide length of combination to win: ");
            String input = userInput.get();
            int possibleCombination = 0;

            if (input.matches("[1-9][0-9]*")) {
                possibleCombination = Integer.parseInt(input);
                if (possibleCombination <= this.boardRow || possibleCombination <= this.boardColumn) {
                    this.numberCombinationToWin = possibleCombination;
                    break;
                }
            }
            output.accept("Impossible combination!!");
        }
    }

    public int getBoardRow() {
        return this.boardRow;
    }

    public int getBoardColumn() {
        return this.boardColumn;
    }

    public int getNumberCombinationToWin() {
        return numberCombinationToWin;
    }
}
