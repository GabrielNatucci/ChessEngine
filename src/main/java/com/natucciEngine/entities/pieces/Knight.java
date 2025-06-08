package com.natucciEngine.entities.pieces;

import com.natucciEngine.core.InputParser.Move;
import com.natucciEngine.entities.Piece;
import com.natucciEngine.entities.Table;
import com.natucciEngine.enuns.PieceColorEnum;
import com.natucciEngine.enuns.PiecesEnum;

public class Knight extends Piece {
    public Knight(PieceColorEnum color, int col, int row) {
        this.setPiece(PiecesEnum.KNIGHT);
        this.setColor(color);
        this.setCol(col);
        this.setRow(row);
    }

	@Override
	public Boolean isMoveValid(Table table, Move move) {
        int diferencaX = Math.abs(move.getFromRow() - move.getToRow());
        int diferencaY = Math.abs(move.getFromCol() - move.getToCol());

        return diferencaX - diferencaY == 1;
	}
}
