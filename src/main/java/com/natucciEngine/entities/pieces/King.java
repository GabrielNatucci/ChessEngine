package com.natucciEngine.entities.pieces;

import com.natucciEngine.core.InputParser.Move;
import com.natucciEngine.entities.Piece;
import com.natucciEngine.entities.Table;
import com.natucciEngine.enuns.PieceColorEnum;
import com.natucciEngine.enuns.PiecesEnum;

public class King extends Piece {
    public King(PieceColorEnum color) {
        this.setPiece(PiecesEnum.KING);
        this.setColor(color);
    }

	@Override
	public Boolean isMoveValid(Table table, Move move) {
        int diferencaX = Math.abs(move.getFromRow() - move.getToRow());
        int diferencaY = Math.abs(move.getFromCol() - move.getToCol());

        return diferencaY == 1 || diferencaX == 1 || diferencaY == 0 || diferencaX == 0;
	}
}
