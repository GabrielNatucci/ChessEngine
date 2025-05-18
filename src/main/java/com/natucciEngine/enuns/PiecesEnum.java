package com.natucciEngine.enuns;

public enum PiecesEnum {
    PAWN(1, "P"),
    KNIGHT(1, "H"),
    BISHOP(1, "B"),
    ROOK(1, "R"),
    KING(1, "K"),
    QUEEN(1, "Q");

    private int pieceCode;
    private String description;

    PiecesEnum(int colorCode, String color) {
        this.description = color;
        this.pieceCode = colorCode;
    }

    public String getDescription() {
        return description;
    }

    public int getPieceCode() {
        return pieceCode;
    }

}
