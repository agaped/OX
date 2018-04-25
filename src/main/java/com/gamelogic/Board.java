package com.gamelogic;

public class Board {

    private int k;

    public Board(int x, int y, int k){
        int[][] board=new int[x][y];
        this.k=k;
    }

    public void addMove(){

    }

    public void print(){
        System.out.println(this);
    }
}
