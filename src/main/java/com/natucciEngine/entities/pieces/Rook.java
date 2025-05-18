package com.natucciEngine.entities.pieces;

import com.natucciEngine.entities.Piece;
import com.natucciEngine.enuns.PieceColorEnum;
import com.natucciEngine.enuns.PiecesEnum;

public class Rook extends Piece {
    public Rook(PieceColorEnum color) {
        this.setPiece(PiecesEnum.ROOK);
        this.setColor(color);
    }
}
