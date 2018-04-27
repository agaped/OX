package com.gamelogic;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class NewBoard {

    private Map<String, Character> boardState;
    private GameConfig gameConfig;


    public NewBoard(GameConfig gameConfig) {
        this.boardState = new HashMap<>();
        this.gameConfig = gameConfig;
    }

    public boolean addMove(String move, Player currentPlayer, Consumer<String> output) {
        //todo:check if user input is correct e.g. 11 12 ...
        if (!this.boardState.containsKey(move)) {
            this.boardState.put(move, currentPlayer.name().charAt(0));
            return true;
        }
        return false;
    }

    public void printState(Consumer<String> output) {
        for (int i)
    }

}
