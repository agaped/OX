package com.ox.core;

public enum Player{
    X {
        @Override
        public Player getOppositePlayer() {
            return O;
        }
    }, O {
        @Override
        public Player getOppositePlayer() {
            return X;
        }
    };

    public abstract Player getOppositePlayer();

    private String playerName;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
