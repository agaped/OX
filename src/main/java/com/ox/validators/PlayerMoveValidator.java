package com.ox.validators;

import com.ox.language.Language;
import com.ox.coordinates.BoardFieldCoordinate;
import com.ox.core.Board;

import java.util.function.Consumer;

public class PlayerMoveValidator {

    public static boolean validateInput(String input, Consumer<String> output) {
        if (input == null || !input.matches("[1-9][0-9]*")) {
            output.accept(Language.get("moveValidator"));
            return false;
        } else {
            return true;
        }
    }

    public static boolean validateMoveAccordingToBoardState(BoardFieldCoordinate boardFieldCoordinate, Board board, Consumer<String> output) {
        int field = boardFieldCoordinate.getX();

        if (checkIfUserMoveIsCorrect(field, board)) {
            if (!board.getBoardState().containsKey(field)) {
                return true;
            } else {
                output.accept(Language.get("moveValidatorFieldOccupied"));
                return false;
            }
        } else {
            output.accept(Language.get("moveValidatorOutOfBound")+" " + getMaxFieldsNumber(board));
            return false;
        }
    }

    private static boolean checkIfUserMoveIsCorrect(int field, Board board) {
        return (field > 0 && field <= getMaxFieldsNumber(board));
    }

    private static int getMaxFieldsNumber(Board board) {
        return board.getColumns() * board.getRows();
    }
}
