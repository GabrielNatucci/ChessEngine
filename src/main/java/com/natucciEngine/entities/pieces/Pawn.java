package com.natucciEngine.entities.pieces;

import java.util.ArrayList;
import java.util.stream.Collectors;

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
    public ArrayList<Move> generatePossibleMoves(Table table) {
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

                if (target == null) {
                    direction = -1;
                    if (this.getColor().equals(PieceColorEnum.WHITE) && this.getRow() == 3) {
                        System.out.println(this.getRow() + " " + this.getCol());
                    }

                    if (this.getColor().equals(PieceColorEnum.WHITE)) {
                        direction = +1;
                    }

                    Piece pawn = board[nextRow + direction][targetCol];
                    if (pawn != null && !pawn.getColor().equals(this.getColor()) && pawn.isCapurableAnPassant()) {
                        moves.add(new Move(row, col, nextRow, targetCol));
                    }
                }
            }
        }

        this.setPossibleMoves(moves);
        return moves;
    }

    @Override
    public Boolean isMoveValid(Table table, Move move) {

        /* isso aq é uma gambiarra TERRÍVEL!!!
         * não vou  me dar ao trabalho de explicar aqui detalhadamente oq isso faz, 
         * mas é basicamente para fazer a lógica da captura de passagem funcionar
         */
        Piece target = table.getLocalTable()[move.getToRow()][move.getToCol()];
        if (target == null) {
            int direction = -1;
            if (this.getColor().equals(PieceColorEnum.WHITE)) {
                direction = +1;
            }

            Piece pawn = table.getLocalTable()[move.getToRow() + direction][move.getToCol()];
            if (pawn != null && !pawn.getColor().equals(this.getColor()) && pawn.isCapurableAnPassant()) {
                table.getLocalTable()[move.getToRow() + direction][move.getToCol()] = null;
            }
        }

        boolean isMoveValid = this.getPossibleMoves().stream().filter(movec -> {
            return move.equals(movec);
        }).collect(Collectors.toList()).size() == 1;

        return isMoveValid;
    }

    private boolean isInsideBoard(int row, int col) {
        return row >= 0 && row < Table.HEIGHT && col >= 0 && col < Table.LENGTH;
    }

}
