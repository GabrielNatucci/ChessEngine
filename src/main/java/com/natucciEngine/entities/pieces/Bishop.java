package com.natucciEngine.entities.pieces;

import java.util.ArrayList;

import com.natucciEngine.core.InputParser.Move;
import com.natucciEngine.entities.Piece;
import com.natucciEngine.entities.Table;
import com.natucciEngine.enuns.PieceColorEnum;
import com.natucciEngine.enuns.PiecesEnum;

public class Bishop extends Piece {
    public Bishop(PieceColorEnum color, int row, int col) {
        this.setPiece(PiecesEnum.BISHOP);
        this.setColor(color);
        this.setRow(row);
        this.setCol(col);
    }

    @Override
    public ArrayList<Move> generatePossibleMoves(Table table) {
        ArrayList<Move> moves = new ArrayList<Move>();
        moves.addAll(generateMovesToTheColumns(table, 1, 1));
        moves.addAll(generateMovesToTheColumns(table, 1, -1));
        moves.addAll(generateMovesToTheColumns(table, -1, 1));
        moves.addAll(generateMovesToTheColumns(table, -1, -1));

        this.setPossibleMoves(moves);
        return moves;
    }

    private ArrayList<Move> generateMovesToTheColumns(Table table, int directionX, int directionY) {
        ArrayList<Move> moves = new ArrayList<>();
        int row = this.getRow();
        int col = this.getCol();

        int j = col + directionY;

        for (int i = row + directionX; i >= 0 && i < Table.HEIGHT && j >= 0 && j < Table.LENGTH; i += directionX) {
            Piece currentPiece = table.getLocalTable()[i][j];

            if (currentPiece == null) {
                moves.add(new Move(row, col, i, j));
            } else {
                if (currentPiece.getColor() != this.getColor()) {
                    moves.add(new Move(row, col, i, j));
                }
                break;
            }

            j += directionY;
        }

        return moves;
    }

}
