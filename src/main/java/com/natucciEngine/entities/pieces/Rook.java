package com.natucciEngine.entities.pieces;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.natucciEngine.core.InputParser.Move;
import com.natucciEngine.entities.Piece;
import com.natucciEngine.entities.Table;
import com.natucciEngine.enuns.PieceColorEnum;
import com.natucciEngine.enuns.PiecesEnum;

public class Rook extends Piece {
    public Rook(PieceColorEnum color, int row, int col) {
        this.setPiece(PiecesEnum.ROOK);
        this.setColor(color);
        this.setCol(col);
        this.setRow(row);
    }

    @Override
    public ArrayList<Move> generatePossibleMoves(Table table) {
        ArrayList<Move> moves = new ArrayList<Move>();
        moves.addAll(this.generateMovesToTheColumns(table, +1));
        moves.addAll(this.generateMovesToTheColumns(table, -1));
        moves.addAll(this.generateMovesToTheColumnsSideWays(table, +1));
        moves.addAll(this.generateMovesToTheColumnsSideWays(table, -1));

        this.setPossibleMoves(moves);
        return moves;
    }

    private ArrayList<Move> generateMovesToTheColumns(Table table, int direction) {
        ArrayList<Move> moves = new ArrayList<>();
        int row = this.getRow();
        int col = this.getCol();

        for (int i = row + direction; i >= 0 && i < Table.HEIGHT; i += direction) {
            Piece currentPiece = table.getLocalTable()[i][col];

            if (currentPiece == null) {
                moves.add(new Move(row, col, i, col));
            } else {
                if (currentPiece.getColor() != this.getColor()) {
                    moves.add(new Move(row, col, i, col));
                }
                break;
            }
        }

        return moves;
    }

    private ArrayList<Move> generateMovesToTheColumnsSideWays(Table table, int direction) {
        ArrayList<Move> moves = new ArrayList<>();
        int row = this.getRow();
        int col = this.getCol();

        for (int i = col + direction; i >= 0 && i < Table.LENGTH; i += direction) {
            Piece currentPiece = table.getLocalTable()[row][i];

            if (currentPiece == null) {
                moves.add(new Move(row, col, row, i));
            } else {
                if (currentPiece.getColor() != this.getColor()) {
                    moves.add(new Move(row, col, row, i));
                }
                break;
            }
        }

        return moves;
    }

}
