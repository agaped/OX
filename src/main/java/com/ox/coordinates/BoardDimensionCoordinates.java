package com.ox.coordinates;

import java.util.Objects;

public class BoardDimensionCoordinates {
    private int x;
    private int y;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardDimensionCoordinates that = (BoardDimensionCoordinates) o;
        return x == that.x &&
                y == that.y;
    }

    @Override
    public int hashCode() {

        return Objects.hash(x, y);
    }
}
