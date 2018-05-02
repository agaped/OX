package com.gamelogic.core;

import com.gamelogic.coordinates.BoardFieldCoordinate;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class NewBoard {

    private Map<Integer, Character> boardState;
    private GameConfig gameConfig;


    public NewBoard(GameConfig gameConfig) {
        this.boardState = new HashMap<>();
        this.gameConfig = gameConfig;
    }

    public boolean addMove(BoardFieldCoordinate boardFieldCoordinate, Player currentPlayer, Consumer<String> output) {
        //todo:check if user input is correct e.g. 11 12 ...
        int field = boardFieldCoordinate.getX();

        if (checkIfUserInputIsCorrect(field)) {
            if (!this.boardState.containsKey(field)) {
                this.boardState.put(field, currentPlayer.name().charAt(0));
                return true;
            } else {
                output.accept("Given field is occupied! Try again");
                return false;
            }
        } else {
            output.accept("Input is incorrect! Should not be greater than "+ getMaxFieldsNumber());
            return false;
        }
    }

    private boolean checkIfUserInputIsCorrect(int field) {
        if (field > 0 && field <= getMaxFieldsNumber()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isBoardFull() {
        int maxFieldsNumber = getMaxFieldsNumber();
        int currentFieldsNumber = boardState.keySet().size();

        return (currentFieldsNumber < maxFieldsNumber) ? false : true;
    }

    public void printState(Consumer<String> output) {
        StringBuilder result=new StringBuilder();
        for (int i = 1; i<= getMaxFieldsNumber(); i++){

            result.append(" ");

            if(i<10) {
                result.append("   ");
            }
            if(i>=10 && i<100) {
                result.append("  ");
            }
            if(i>=100 && i<1000) {
                result.append(" ");
            }
            if(this.boardState.containsKey(i)){
                if(i>=10){
                    result.append(" ");
                }
                if(i>=100){
                    result.append(" ");
                }
                if(i>=1000){
                    result.append(" ");
                }
                result.append(this.boardState.get(i));
            }else{
                result.append(i);
            }
            if(i%gameConfig.getBoardColumn()==0) {
                result.append("\n");
            }
        }
        output.accept(result.toString());
    }

    private int getMaxFieldsNumber(){
        return gameConfig.getBoardRow()*gameConfig.getBoardColumn();
    }

}
