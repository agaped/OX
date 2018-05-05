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
            output.accept("Provide board dimension - row and column, minimal board size is: 3 3");
            input = userInput.get();
            if (input.matches("[1-9][0-9]*[\" \"][1-9][0-9]*")) {
                boardDimensionCoordinates = BoardDimensionCoordinates.parse(input);
                if(boardDimensionCoordinates.getX()>=3 && boardDimensionCoordinates.getY()>=3) {
                    if (boardDimensionCoordinates.getX() * boardDimensionCoordinates.getY() <= 10000) {
                        return Optional.of(input);
                    } else {
                        output.accept("Total size of the board cannot exceed 10 000. Try again");
                    }
                }else{
                    output.accept("Minimal board size is: 3 3");
                }
            } else {
                output.accept("Wrong format! Try again");
            }
        }
    }

    public Optional<String> validateLengthOfCombinationToWin(Consumer<String> output, Supplier<String> userInput) {
        int possibleCombination = 0;
        while (true) {
            output.accept("Provide length of combination to win, minimal length is 3");
            String input = userInput.get();
            if (input.matches("[1-9][0-9]*")) {
                possibleCombination = Integer.parseInt(input);
                if(possibleCombination>=3) {
                    if (possibleCombination <= boardDimensionCoordinates.getX() || possibleCombination <= boardDimensionCoordinates.getY()) {
                        return Optional.of(input);
                    }
                }else{
                    output.accept("Minimal length is 3");
                }
            }
            output.accept("Impossible combination!!");
        }
    }
}