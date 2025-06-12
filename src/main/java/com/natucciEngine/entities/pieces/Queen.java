package com.natucciEngine.entities.pieces;

import java.util.ArrayList;

import com.natucciEngine.core.InputParser.Move;
import com.natucciEngine.entities.Piece;
import com.natucciEngine.entities.Table;
import com.natucciEngine.enuns.PieceColorEnum;
import com.natucciEngine.enuns.PiecesEnum;

public class Queen extends Piece {
    public Queen(PieceColorEnum color, int row, int col) {
        this.setPiece(PiecesEnum.QUEEN);
        this.setColor(color);
        this.setCol(col);
        this.setRow(row);
    }

    @Override
    public Boolean isMoveValid(Table table, Move move) {

        Piece rook = new Rook(this.getColor(), this.getCol(), this.getRow());
        Piece bishop = new Bishop(this.getColor(), this.getCol(), this.getRow());

        /*
         * aqui vou fazer o caminho mais fácil
         * Se os movimento do bispo ou da torre forem válidos, o movimento deve ser
         * válido para rainha também
         * já que ela é basicamente uma junção dos dois
         */
        return bishop.isMoveValid(table, move) || rook.isMoveValid(table, move);
    }

    @Override
    public ArrayList<Move> getPossibleMoves(Table table) {
        ArrayList<Move> moves = new ArrayList<Move>();
        Piece rook = new Rook(this.getColor(), this.getCol(), this.getRow());
        Piece bishop = new Bishop(this.getColor(), this.getCol(), this.getRow());

        // moves.addAll(rook.getPossibleMoves(table));
        // moves.addAll(bishop.getPossibleMoves(table));

        return moves;
    }
}
