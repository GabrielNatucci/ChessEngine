package com.natucciEngine.entities.pieces;

import java.util.ArrayList;

import com.natucciEngine.core.InputParser.Move;
import com.natucciEngine.entities.Piece;
import com.natucciEngine.entities.Table;
import com.natucciEngine.enuns.PieceColorEnum;
import com.natucciEngine.enuns.PiecesEnum;

public class Pawn extends Piece {
    public Pawn(PieceColorEnum color) {
        this.setPiece(PiecesEnum.PAWN);
        this.setColor(color);
    }

	@Override
	public Boolean isMoveValid(Table table, Move move) {
        if (move.getFromCol() == move.getFromCol()) {
            int diferenca = move.getFromRow() - move.getToRow();
            if (this.getColor() == PieceColorEnum.BLACK) diferenca *= -1;

            return diferenca > 0 && diferenca <= 2;
        }

        return false;
	}
}
