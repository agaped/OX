package com.gamelogic;

import java.util.function.Consumer;

public class Board {

    private char emptyChar='\u0000';
    private GameConfig gameConfig;
    private char[][] boardState;


    public Board(GameConfig gameConfig) {
        this.gameConfig = gameConfig;
        this.boardState=new char[gameConfig.getBoardLength()][gameConfig.getBoardWidth()];
    }

    //todo: refactor to use Consumer<String> output or Message/Error Handling

    public void addMove(Coordinates coordinates, Player currentPlayer){
        int x=coordinates.getX()-1;
        int y=coordinates.getY()-1;
        if(getCheckIfMoveIsPossible(x, y)) {
            this.boardState[x][y] = currentPlayer.name().charAt(0);
        }else{
            System.out.println("Move not possible");
        }
    }

    private boolean getCheckIfMoveIsPossible(int x, int y) {
        if(this.boardState[x][y]==this.emptyChar){
            return true;
        }else{
            return false;
        }
    }

    public void printState(Consumer<String> output){
        for (int i=0; i<this.boardState.length; i++){
            output.accept(String.valueOf(this.boardState[i]));
        }

    }
}
