package com.natucciEngine.entities.pieces;

import com.natucciEngine.entities.Piece;
import com.natucciEngine.enuns.PieceColorEnum;
import com.natucciEngine.enuns.PiecesEnum;

public class King extends Piece {
    public King(PieceColorEnum color) {
        this.setPiece(PiecesEnum.KING);
        this.setColor(color);
    }
}
