package com.ox.core;

import com.ox.coordinates.BoardDimensionCoordinates;
import com.ox.validators.GameConfigValidator;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class GameConfig {

    private BoardDimensionCoordinates boardDimensionCoordinates;
    private int lengthOfCombinationToWin;
    private int boardRow;
    private int boardColumn;

    public void setBoardSize(Consumer<String> output, Supplier<String> userInput, GameConfigValidator gameConfigValidator) {
        Optional<String> size = gameConfigValidator.validateBoardSize(output, userInput);

        if (size.isPresent()) {
            boardDimensionCoordinates = BoardDimensionCoordinates.parse(size.get());
            this.boardRow = boardDimensionCoordinates.getX();
            this.boardColumn = boardDimensionCoordinates.getY();
        }
    }

    public void setLengthOfCombinationToWin(Consumer<String> output, Supplier<String> userInput, GameConfigValidator gameConfigValidator) {
        Optional<String> combination = gameConfigValidator.validateLengthOfCombinationToWin(output, userInput);
        if (combination.isPresent()) {
            this.lengthOfCombinationToWin = Integer.parseInt(combination.get());
        }
    }

    public int getBoardRow() {
        return this.boardRow;
    }

    public int getBoardColumn() {
        return this.boardColumn;
    }

    public int getLengthOfCombinationToWin() {
        return lengthOfCombinationToWin;
    }
}
