package com.natucciEngine.entities.pieces;

import com.natucciEngine.entities.Piece;
import com.natucciEngine.enuns.PieceColorEnum;
import com.natucciEngine.enuns.PiecesEnum;

public class Queen extends Piece {
    public Queen(PieceColorEnum color) {
        this.setPiece(PiecesEnum.QUEEN);
        this.setColor(color);
    }
}
