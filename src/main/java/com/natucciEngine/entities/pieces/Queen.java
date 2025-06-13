package com.natucciEngine.entities.pieces;

import java.util.ArrayList;

import com.natucciEngine.core.InputParser.Move;
import com.natucciEngine.entities.Piece;
import com.natucciEngine.entities.Table;
import com.natucciEngine.enuns.PieceColorEnum;
import com.natucciEngine.enuns.PiecesEnum;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Queen extends Piece {
    public Queen(PieceColorEnum color, int row, int col) {
        this.setPiece(PiecesEnum.QUEEN);
        this.setColor(color);
        this.setCol(col);
        this.setRow(row);
    }

    @Override
    public ArrayList<Move> generatePossibleMoves(Table table) {
        ArrayList<Move> moves = new ArrayList<Move>();
        Piece rook = new Rook(this.getColor(), this.getRow(),this.getCol());
        Piece bishop = new Bishop(this.getColor(), this.getRow(),this.getCol());

        moves.addAll(rook.generatePossibleMoves(table));
        moves.addAll(bishop.generatePossibleMoves(table));
        this.setPossibleMoves(moves);

        return moves;
    }
}
