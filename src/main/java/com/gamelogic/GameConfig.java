package com.gamelogic;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class GameConfig {

    private int maxNumberOfMatches=3;
    private int numberCombinationToWin;
    private Coordinates boardCoordinates;
    private int boardLength;
    private int boardWidth;


    public void setBoardSize(Consumer<String> output, Supplier<String> userInput) {
        output.accept("Provide board dimension y-vertical, x-horizontal: ");
        boardCoordinates=Coordinates.parse(userInput.get());
        this.boardLength=boardCoordinates.getX();
        this.boardWidth=boardCoordinates.getY();
    }

    public void setNumberCombinationToWin(Consumer<String> output, Supplier<String> userInput){
        output.accept("Provide length of combination to win: ");
        this.numberCombinationToWin=Integer.parseInt(userInput.get());
    }

    public int getBoardLength() {
        return this.boardLength;
    }
    public int getBoardWidth(){
        return this.boardWidth;
    }
}
