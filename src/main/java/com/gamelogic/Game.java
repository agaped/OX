package com.gamelogic;

import java.util.Scanner;

public class Game {

    private Scanner reader;
    private Player currentPlayer;
    private GameState currentState;

    public Game(GameState initialState) {
        this.currentState = initialState;
        reader = new Scanner(System.in);
    }

    public void start() {
        this.currentState.start(reader);
        this.currentState.nextState();
    }
}
