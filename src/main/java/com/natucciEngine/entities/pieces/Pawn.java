package com.natucciEngine.entities.pieces;

import java.util.ArrayList;

import com.natucciEngine.core.InputParser.Move;
import com.natucciEngine.entities.Piece;
import com.natucciEngine.entities.Table;
import com.natucciEngine.enuns.PieceColorEnum;
import com.natucciEngine.enuns.PiecesEnum;

public class Pawn extends Piece {
    public Pawn(PieceColorEnum color, int col, int row) {
        this.setPiece(PiecesEnum.PAWN);
        this.setColor(color);
        this.setCol(col);
        this.setRow(row);
    }

    @Override
    public Boolean isMoveValid(Table table, Move move) {
        if (move.getFromCol() == move.getFromCol()) {
            int diferenca = move.getFromRow() - move.getToRow();
            if (this.getColor() == PieceColorEnum.BLACK)
                diferenca *= -1;

            return diferenca > 0 && diferenca <= 2;
        }

        return false;
    }

    @Override
    public ArrayList<Move> getPossibleMoves(Table table) {
        ArrayList<Move> moves = new ArrayList<>();
        Piece[][] board = table.getLocalTable();

        int row = this.getRow();
        int col = this.getCol();

        int direction = (this.getColor() == PieceColorEnum.WHITE) ? -1 : 1;
        int startRow = (this.getColor() == PieceColorEnum.WHITE) ? 6 : 1;

        // Movimento simples para frente
        int nextRow = row + direction;
        if (isInsideBoard(nextRow, col) && board[nextRow][col] == null) {
            moves.add(new Move(row, col, nextRow, col));

            // Movimento duplo (somente da posição inicial)
            int doubleRow = row + 2 * direction;
            if (row == startRow && board[doubleRow][col] == null) {
                moves.add(new Move(row, col, doubleRow, col));
            }
        }

        // Capturas diagonais
        for (int dCol : new int[] { -1, +1 }) {
            int targetCol = col + dCol;

            if (isInsideBoard(nextRow, targetCol)) {
                Piece target = board[nextRow][targetCol];

                if (target != null && target.getColor() != this.getColor()) {
                    moves.add(new Move(row, col, nextRow, targetCol));
                }
            }
        }

        return moves;

    }

    private boolean isInsideBoard(int row, int col) {
        return row >= 0 && row < Table.HEIGHT && col >= 0 && col < Table.LENGTH;
    }

}
