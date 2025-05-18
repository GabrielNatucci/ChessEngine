package com.natucciEngine.entities.pieces;

import com.natucciEngine.entities.Piece;
import com.natucciEngine.enuns.PieceColorEnum;
import com.natucciEngine.enuns.PiecesEnum;

public class Pawn extends Piece {
    public Pawn(PieceColorEnum color) {
        this.setPiece(PiecesEnum.PAWN);
        this.setColor(color);
    }
}
