package com.natucciEngine.entities.pieces;

import com.natucciEngine.core.InputParser.Move;
import com.natucciEngine.entities.Piece;
import com.natucciEngine.entities.Table;
import com.natucciEngine.enuns.PieceColorEnum;
import com.natucciEngine.enuns.PiecesEnum;

public class Rook extends Piece {
    public Rook(PieceColorEnum color, int col, int row) {
        this.setPiece(PiecesEnum.ROOK);
        this.setColor(color);
        this.setCol(col);
        this.setRow(row);
    }

    @Override
    public Boolean isMoveValid(Table table, Move move) {
        boolean isMoveRow = move.getFromRow() == move.getToRow();
        boolean isMoveCol = move.getFromCol() == move.getToCol();

        if (!isMoveRow && !isMoveCol) {
            return false;
        }

        int fromSquare = isMoveRow ? move.getFromCol() : move.getFromRow();
        int toSquare = isMoveRow ? move.getToCol() : move.getToRow();

        int indexMultipier = 0;

        if (isMoveRow) {
            indexMultipier = move.getFromCol() - move.getToCol() > 0 ? -1 : 1;
        } else {
            indexMultipier = move.getFromRow() - move.getToRow() > 0 ? -1 : 1;
        }

        for (int i = fromSquare + indexMultipier; i != toSquare; i += indexMultipier) {
            Piece piece;
            if (isMoveRow) {
                piece = table.getLocalTable()[move.getFromRow()][i];
                if (piece != null) {
                    return false;
                }

                continue;
            }

            piece = table.getLocalTable()[i][move.getFromCol()];
            if (piece != null) {
                return false;
            }
        }

        return true;
    }
}
