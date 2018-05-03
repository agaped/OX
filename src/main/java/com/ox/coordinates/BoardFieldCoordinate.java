package com.ox.coordinates;

public class BoardFieldCoordinate {

    private int x;

    public BoardFieldCoordinate(int x) {
        this.x = x;
    }

    public int getX() {
        return x;
    }

    public static BoardFieldCoordinate parse(String coordinateAsString) {
        int x = Integer.parseInt(coordinateAsString);
        return new BoardFieldCoordinate(x);
    }
}
