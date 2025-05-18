package com.natucciEngine.entities.pieces;

import com.natucciEngine.entities.Piece;
import com.natucciEngine.enuns.PieceColorEnum;
import com.natucciEngine.enuns.PiecesEnum;

public class Knight extends Piece {
    public Knight(PieceColorEnum color) {
        this.setPiece(PiecesEnum.KNIGHT);
        this.setColor(color);
    }
}
