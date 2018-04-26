package com.gamelogic;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class GameConfig {

    private int maxNumberOfMatches=3;
    private int numberToWin;
    private Coordinates boardCoordinates;
    private int boardLength;
    private int boardWidth;
    private Board board;


    public void setBoardSize(Consumer<String> output, Supplier<String> userInput) {
        output.accept("Provide board dimension y-vertical, x-horizontal: ");
        boardCoordinates=Coordinates.parse(userInput.get());
        this.boardLength=boardCoordinates.getX();
        this.boardWidth=boardCoordinates.getY();
    }

    public int getBoardLength() {
        return this.boardLength;
    }
    public int getBoardWidth(){
        return this.boardWidth;
    }
}
