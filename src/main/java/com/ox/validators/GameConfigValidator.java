package com.ox.validators;

import com.ox.coordinates.BoardDimensionCoordinates;
import com.ox.language.Language;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class GameConfigValidator {

    private BoardDimensionCoordinates boardDimensionCoordinates;

    public Optional<String> validateBoardSize(Consumer<String> output, Supplier<String> userInput) {
        String input;
        while (true) {
            output.accept(Language.get("boardSize"));
            input = userInput.get();
            if (input.matches("[1-9][0-9]*[\" \"][1-9][0-9]*")) {
                boardDimensionCoordinates = BoardDimensionCoordinates.parse(input);
                if (boardDimensionCoordinates.getX() >= 3 && boardDimensionCoordinates.getY() >= 3) {
                    if (boardDimensionCoordinates.getX() * boardDimensionCoordinates.getY() <= 10000) {
                        return Optional.of(input);
                    } else {
                        output.accept(Language.get("boardSizeExceeded"));
                    }
                } else {
                    output.accept(Language.get("boardSizeMin"));
                }
            } else {
                output.accept(Language.get("boardWrongFormat"));
            }
        }
    }

    public Optional<String> validateLengthOfCombinationToWin(Consumer<String> output, Supplier<String> userInput) {
        int possibleCombination = 0;
        while (true) {
            output.accept(Language.get("winCom"));
            String input = userInput.get();
            if (input.matches("[1-9][0-9]*")) {
                possibleCombination = Integer.parseInt(input);
                if (possibleCombination >= 3) {
                    if (possibleCombination <= boardDimensionCoordinates.getX() || possibleCombination <= boardDimensionCoordinates.getY()) {
                        return Optional.of(input);
                    }
                } else {
                    output.accept(Language.get("winComMin"));
                }
            }
            output.accept(Language.get("winComWrong"));
        }
    }
}