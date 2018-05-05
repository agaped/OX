package com.ox.core;

public enum TurnNumber {
    TURN_NUMBER(3);

    private int maxTurnNumberInOneGame;

    TurnNumber(int maxTurnNumberInOneGame) {
        this.maxTurnNumberInOneGame = maxTurnNumberInOneGame;
    }

    public int getMaxTurnNumberInOneGame() {
        return maxTurnNumberInOneGame;
    }
}

