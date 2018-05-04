package com.ox.core;

import com.ox.coordinates.BoardFieldCoordinate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class Board {

    private Map<Integer, Character> boardState;
    private GameConfig gameConfig;
    private int rows;
    private int columns;


    public Board(GameConfig gameConfig) {
        this.boardState = new HashMap<>();
        this.gameConfig = gameConfig;
        this.columns=gameConfig.getBoardColumn();
        this.rows=gameConfig.getBoardRow();
    }

    public void addMove(BoardFieldCoordinate boardFieldCoordinate, Player currentPlayer) {
        int field = boardFieldCoordinate.getX();
        this.boardState.put(field, currentPlayer.name().charAt(0));
    }

    public boolean isBoardFull() {
        int maxFieldsNumber = getMaxFieldsNumber();
        int currentFieldsNumber = boardState.keySet().size();

        return !(currentFieldsNumber < maxFieldsNumber);
    }

    public void printBoardState(Consumer<String> output) {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= getMaxFieldsNumber(); i++) {

            result.append(" ");

            if (i < 10) {
                result.append("   ");
            }
            if (i >= 10 && i < 100) {
                result.append("  ");
            }
            if (i >= 100 && i < 1000) {
                result.append(" ");
            }
            if (this.boardState.containsKey(i)) {
                if (i >= 10) {
                    result.append(" ");
                }
                if (i >= 100) {
                    result.append(" ");
                }
                if (i >= 1000) {
                    result.append(" ");
                }
                result.append(this.boardState.get(i));
            } else {
                result.append(i);
            }
            if (i % this.columns == 0) {
                result.append("\n");
            }
        }
        output.accept(result.toString());
    }

    private int getMaxFieldsNumber() {
        return this.columns * this.rows;
    }

    public Map<Integer, Character> getBoardState() {
        return Collections.unmodifiableMap(this.boardState);
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }
}
