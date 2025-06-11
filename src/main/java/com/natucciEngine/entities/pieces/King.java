package com.natucciEngine.entities.pieces;

import com.natucciEngine.core.InputParser.Move;
import com.natucciEngine.entities.Piece;
import com.natucciEngine.entities.Table;
import com.natucciEngine.enuns.PieceColorEnum;
import com.natucciEngine.enuns.PiecesEnum;

public class King extends Piece {
    public King(PieceColorEnum color, int row, int col) {
        this.setPiece(PiecesEnum.KING);
        this.setColor(color);
        this.setCol(col);
        this.setRow(row);
    }

    @Override
    public Boolean isMoveValid(Table table, Move move) {
        int diferencaX = Math.abs(move.getFromRow() - move.getToRow());
        int diferencaY = Math.abs(move.getFromCol() - move.getToCol());

        if (diferencaY == 2 && 
                table.getLocalTable()[move.getFromRow()][7] != null  &&
                table.getLocalTable()[move.getFromRow()][7].getPiece().equals(PiecesEnum.ROOK) &&
                !table.getLocalTable()[move.getFromRow()][7].isMoved() &&
                table.getLocalTable()[move.getFromRow()][6] == null &&
                table.getLocalTable()[move.getFromRow()][5] == null ) {

            Piece rook = table.getLocalTable()[move.getFromRow()][7];
            table.getLocalTable()[move.getFromRow()][7] = null;
            table.getLocalTable()[move.getFromRow()][5] = rook;
            return true;
        }

        if (diferencaY == -2 && 
                table.getLocalTable()[move.getFromRow()][0] != null  &&
                table.getLocalTable()[move.getFromRow()][0].getPiece().equals(PiecesEnum.ROOK) &&
                !table.getLocalTable()[move.getFromRow()][0].isMoved() &&
                table.getLocalTable()[move.getFromRow()][1] == null &&
                table.getLocalTable()[move.getFromRow()][2] == null ) {
            Piece rook = table.getLocalTable()[move.getFromRow()][0];
            table.getLocalTable()[move.getFromRow()][0] = null;
            table.getLocalTable()[move.getFromRow()][3] = rook;
            return true;
        }

        return diferencaY == 1 || diferencaX == 1 || diferencaY == 0 || diferencaX == 0;
    }
}
