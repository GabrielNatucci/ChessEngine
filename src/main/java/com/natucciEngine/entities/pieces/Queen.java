package com.natucciEngine.entities.pieces;

import com.natucciEngine.core.InputParser.Move;
import com.natucciEngine.entities.Piece;
import com.natucciEngine.entities.Table;
import com.natucciEngine.enuns.PieceColorEnum;
import com.natucciEngine.enuns.PiecesEnum;

public class Queen extends Piece {
    public Queen(PieceColorEnum color, int col, int row) {
        this.setPiece(PiecesEnum.QUEEN);
        this.setColor(color);
        this.setCol(col);
        this.setRow(row);
    }

    @Override
    public Boolean isMoveValid(Table table, Move move) {
        Piece queen = table.getLocalTable()[move.getFromRow()][move.getFromCol()];

        Piece rook = new Rook(queen.getColor(), queen.getCol(), queen.getRow());
        Piece bishop = new Bishop(queen.getColor(), queen.getCol(), queen.getRow());

        /*
         * aqui vou fazer o caminho mais fácil
         * Se os movimento do bispo ou da torre forem válidos, o movimento deve ser válido para rainha também
         * já que ela é basicamente uma junção dos dois
         */
        if (bishop.isMoveValid(table, move) || rook.isMoveValid(table, move)) {
            return true;
        }

        return false;
    }
}
