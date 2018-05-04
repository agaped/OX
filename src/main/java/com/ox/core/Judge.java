package com.ox.core;

public enum Judge {

    DEFEAT(0),
    DRAW(1),
    WINNER(3);

    private int points;

    Judge(int points) {
        this.points = points;
    }

    public int getPoints() {
        return points;
    }
}
