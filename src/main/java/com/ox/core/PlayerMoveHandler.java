package com.ox.core;

import com.ox.coordinates.BoardFieldCoordinate;

import java.util.function.Consumer;

public class PlayerMoveHandler {

    public static boolean validateInput(String input, Consumer<String> output) {
        if (!input.matches("[1-9][0-9]*")) {
            output.accept("Wrong input! Provide a move as a single number");
            return false;
        }else{
            return true;
        }
    }

    public static boolean validateMoveAccordingToBoardState(BoardFieldCoordinate boardFieldCoordinate, Board board, Consumer<String> output){
        int field = boardFieldCoordinate.getX();

        if (checkIfUserMoveIsCorrect(field, board)) {
            if (!board.getBoardState().containsKey(field)) {
                return true;
            } else {
                output.accept("Given field is occupied! Try again");
                return false;
            }
        } else {
            output.accept("Input is incorrect! Should not be greater than " + getMaxFieldsNumber(board));
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
