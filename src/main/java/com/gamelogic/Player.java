package com.gamelogic;

public enum Player {
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
}
