package com.ox.core;

public class TurnNumber {

    int turn;

    public TurnNumber(int turn) {
        this.turn = turn;
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
