package com.gamelogic;

import java.util.function.Consumer;

public class Board {

    private GameConfig gameConfig;
    private char[][] boardState;

    public Board(GameConfig gameConfig) {
        this.gameConfig = gameConfig;
        this.boardState=new char[gameConfig.getBoardLength()][gameConfig.getBoardWidth()];
    }


    public void addMove(Coordinates coordinates, Player currentPlayer){
        this.boardState[coordinates.getX()-1][coordinates.getY()-1]=currentPlayer.name().charAt(0);
    }

    public void printState(Consumer<String> output){

        for (int i=0; i<this.boardState.length; i++){
            output.accept(String.valueOf(this.boardState[i]));
        }

    }
}
