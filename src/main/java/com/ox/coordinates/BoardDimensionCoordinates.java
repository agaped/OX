package com.ox.coordinates;

public class BoardDimensionCoordinates {
    private  int x;
    private  int y;

    public BoardDimensionCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static BoardDimensionCoordinates parse(String coordinatesAsString) {
        String[] parts = coordinatesAsString.split(" ");
        int x = Integer.parseInt(parts[0]);
        int y = Integer.parseInt(parts[1]);
        return new BoardDimensionCoordinates(x, y);
    }
}
