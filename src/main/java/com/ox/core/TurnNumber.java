package com.ox.core;

public class TurnNumber {

    int turn;

    public TurnNumber() {
        this.turn = 1;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public void increaseTurn(){
        this.turn++;
    }
}
