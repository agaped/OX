package com.gamelogic;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class GameConfig {

    private int maxNumberOfMatches = 3;
    private int numberCombinationToWin;
    private Coordinates boardCoordinates;
    private int boardLength;
    private int boardWidth;

    //todo: probably move to Message/Error handling State
    public void setBoardSize(Consumer<String> output, Supplier<String> userInput) {
        String input;
        output.accept("Welcome to XO game!!!\nSome setup at the beggining...\n");
        do {
            output.accept("Provide board dimension y-vertical, x-horizontal: ");
            input = userInput.get();

        } while (!input.matches("[1-9][0-9]*[\" \"][1-9][0-9]*"));

        boardCoordinates = Coordinates.parse(input);
        this.boardWidth = boardCoordinates.getX();
        this.boardLength = boardCoordinates.getY();
    }

    //todo: validate user input
    public void setNumberCombinationToWin(Consumer<String> output, Supplier<String> userInput) {
        while (true) {
            output.accept("Provide length of combination to win: ");
            String input = userInput.get();
            int possibleCombination = 0;

            if (input.matches("[1-9][0-9]*")) {
                possibleCombination = Integer.parseInt(input);
                if (possibleCombination <= this.boardLength || possibleCombination <= this.boardWidth) {
                    this.numberCombinationToWin = possibleCombination;
                    break;
                }
            }
            output.accept("Impossible combination!!");
        }
    }

    public int getBoardLength() {
        return this.boardLength;
    }

    public int getBoardWidth() {
        return this.boardWidth;
    }
}
