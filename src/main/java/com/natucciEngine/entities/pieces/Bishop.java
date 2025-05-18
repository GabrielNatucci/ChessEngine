package com.natucciEngine.entities.pieces;

import com.natucciEngine.entities.Piece;
import com.natucciEngine.enuns.PieceColorEnum;
import com.natucciEngine.enuns.PiecesEnum;

public class Bishop extends Piece {
    public Bishop(PieceColorEnum color) {
        this.setPiece(PiecesEnum.BISHOP);
        this.setColor(color);
    }
}
