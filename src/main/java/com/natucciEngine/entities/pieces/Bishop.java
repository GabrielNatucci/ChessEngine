package com.natucciEngine.entities.pieces;

import com.natucciEngine.core.InputParser.Move;
import com.natucciEngine.entities.Piece;
import com.natucciEngine.entities.Table;
import com.natucciEngine.enuns.PieceColorEnum;
import com.natucciEngine.enuns.PiecesEnum;

public class Bishop extends Piece {
    public Bishop(PieceColorEnum color) {
        this.setPiece(PiecesEnum.BISHOP);
        this.setColor(color);
    }

    @Override
    public Boolean isMoveValid(Table table, Move move) {
        int diferencaX = Math.abs(move.getFromRow() - move.getToRow());
        int diferencaY = Math.abs(move.getFromCol() - move.getToCol());

        if (diferencaX / diferencaY != 1) {
            return false;
        }

        int multiplierY = move.getFromCol() > move.getToCol() ? -1 : 1;
        int multiplierX = move.getFromRow() > move.getToRow() ? -1 : 1;
        int j = move.getFromCol() + multiplierY;
        int i = move.getFromRow() + multiplierX;

        for (; i != move.getToRow() || j != move.getToCol();) {
            Piece localPiece = table.getLocalTable()[i][j];

            if (localPiece != null) {
                return false;
            }

            i += (1*multiplierX);
            j += (1*multiplierY);
        }

        return true;
    }
}
