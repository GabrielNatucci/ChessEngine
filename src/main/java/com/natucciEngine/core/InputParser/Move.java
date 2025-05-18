package com.natucciEngine.core.InputParser;

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
    public String toString() {
        return String.format("Move[from=(%d,%d), to=(%d,%d)]",
                fromRow, fromCol, toRow, toCol);
    }
}
