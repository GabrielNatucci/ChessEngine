package com.natucciEngine.core.InputParser;

import java.util.Objects;

public class Move {
    public final int fromRow, fromCol;
    public final int toRow, toCol;

    public Move(int fromRow, int fromCol, int toRow, int toCol) {
        this.fromRow = fromRow;
        this.fromCol = fromCol;
        this.toRow = toRow;
        this.toCol = toCol;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Move other = (Move) obj;

        return fromRow == other.fromRow &&
                fromCol == other.fromCol &&
                toRow == other.toRow &&
                toCol == other.toCol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fromRow, fromCol, toRow, toCol);
    }

    @Override
    public String toString() {
        return String.format("Move[from=(%d,%d), to=(%d,%d)]",
                fromRow, fromCol, toRow, toCol);
    }

    public int getFromRow() {
        return fromRow;
    }

    public int getFromCol() {
        return fromCol;
    }

    public int getToRow() {
        return toRow;
    }

    public int getToCol() {
        return toCol;
    }

}
