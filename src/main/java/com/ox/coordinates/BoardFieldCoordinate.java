package com.ox.coordinates;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardFieldCoordinate that = (BoardFieldCoordinate) o;
        return x == that.x;
    }

    @Override
    public int hashCode() {

        return Objects.hash(x);
    }
}
