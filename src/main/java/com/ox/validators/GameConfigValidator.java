package com.ox.validators;

import com.ox.coordinates.BoardDimensionCoordinates;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class GameConfigValidator {

    private BoardDimensionCoordinates boardDimensionCoordinates;

    public Optional<String> validateBoardSize(Consumer<String> output, Supplier<String> userInput) {
        String input;
        while (true) {
            output.accept("Provide board dimension - row and column: ");
            input = userInput.get();
            if (input.matches("[1-9][0-9]*[\" \"][1-9][0-9]*")) {
                boardDimensionCoordinates = BoardDimensionCoordinates.parse(input);
                if (boardDimensionCoordinates.getX() * boardDimensionCoordinates.getY() <= 10000) {
                    return Optional.of(input);
                } else {
                    output.accept("Total size of the board cannot exceed 10 000. Try again");
                }
            } else {
                output.accept("Wrong format! Try again");
            }
        }
    }

    public Optional<String> validateLengthOfCombinationToWin(Consumer<String> output, Supplier<String> userInput) {
        int possibleCombination = 0;
        while (true) {
            output.accept("Provide length of combination to win: ");
            String input = userInput.get();
            if (input.matches("[1-9][0-9]*")) {
                possibleCombination = Integer.parseInt(input);
                if (possibleCombination <= boardDimensionCoordinates.getX() || possibleCombination <= boardDimensionCoordinates.getY()) {
                    return Optional.of(input);
                }
            }
            output.accept("Impossible combination!!");
        }
    }
}