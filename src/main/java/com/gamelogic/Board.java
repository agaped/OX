package com.gamelogic;

import java.util.function.Consumer;

public class Board {


    public void addMove(Coordinates coordinates, Player currentPlayer){

    }

    public void printState(Consumer<String> output){
        output.accept("Board state");
    }
}
