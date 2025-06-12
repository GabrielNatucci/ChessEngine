package com.natucciEngine.entities.pieces;

import java.util.ArrayList;

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
        int diferencaX = move.getFromRow() - move.getToRow();
        int diferencaY = move.getToCol() - move.getFromCol();

        if (diferencaY == 2 &&
                table.getLocalTable()[move.getFromRow()][7] != null &&
                table.getLocalTable()[move.getFromRow()][7].getPiece().equals(PiecesEnum.ROOK) &&
                !table.getLocalTable()[move.getFromRow()][7].isMoved() &&
                table.getLocalTable()[move.getFromRow()][6] == null &&
                table.getLocalTable()[move.getFromRow()][5] == null) {

            Piece rook = table.getLocalTable()[move.getFromRow()][7];
            table.getLocalTable()[move.getFromRow()][7] = null;
            table.getLocalTable()[move.getFromRow()][5] = rook;
            return true;
        }

        if (diferencaY == -2 &&
                table.getLocalTable()[move.getFromRow()][0] != null &&
                table.getLocalTable()[move.getFromRow()][0].getPiece().equals(PiecesEnum.ROOK) &&
                !table.getLocalTable()[move.getFromRow()][0].isMoved() &&
                table.getLocalTable()[move.getFromRow()][1] == null &&
                table.getLocalTable()[move.getFromRow()][2] == null) {

            Piece rook = table.getLocalTable()[move.getFromRow()][0];
            table.getLocalTable()[move.getFromRow()][0] = null;
            table.getLocalTable()[move.getFromRow()][3] = rook;
            return true;
        }

        diferencaX = Math.abs(diferencaX);
        diferencaY = Math.abs(diferencaY);
        return diferencaY == 1 || diferencaX == 1 || diferencaY == 0 || diferencaX == 0;
    }

    @Override
    public ArrayList<Move> generatePossibleMoves(Table table) {
        ArrayList<Move> moves = new ArrayList<Move>();
        // moves.addAll(generateKingMovesWithCastling(table));

		return moves;
    }

    private ArrayList<Move> generateKingMoves(Table table) {
        ArrayList<Move> moves = new ArrayList<>();
        int[][] directions = {
                { -1, -1 }, { -1, 0 }, { -1, +1 }, // diagonais e acima
                { 0, -1 }, { 0, +1 }, // lados
                { +1, -1 }, { +1, 0 }, { +1, +1 } // abaixo
        };

        int row = this.getRow();
        int col = this.getCol();

        for (int[] dir : directions) {
            int newRow = row + dir[0];
            int newCol = col + dir[1];

            if (isInsideBoard(newRow, newCol)) {
                Piece target = table.getLocalTable()[newRow][newCol];

                if (target == null || target.getColor() != this.getColor()) {
                    moves.add(new Move(row, col, newRow, newCol));
                }
            }
        }

        return moves;
    }

    private boolean isInsideBoard(int row, int col) {
        return row >= 0 && row < Table.HEIGHT && col >= 0 && col < Table.LENGTH;
    }

    private ArrayList<Move> generateKingMovesWithCastling(Table table) {
        ArrayList<Move> moves = generateKingMoves(table); // movimentos padrão do rei

        int row = this.getRow();
        int col = this.getCol();

        // O rei já se moveu? Não pode rocar.
        if (this.isMoved())
            return moves;

        // Está em xeque? Não pode rocar.
        if (table.isSquareAttacked(row, col, this.getColor()))
            return moves;

        // Roque curto (rei para direita)
        if (canCastle(table, row, 7, new int[] { 5, 6 })) {
            moves.add(new Move(row, col, row, col + 2)); // e -> g
        }

        // Roque longo (rei para esquerda)
        if (canCastle(table, row, 0, new int[] { 1, 2, 3 })) {
            moves.add(new Move(row, col, row, col - 2)); // e -> g
        }

        return moves;
    }

    private boolean canCastle(Table table, int row, int rookCol, int[] emptyCols) {
        Piece[][] board = table.getLocalTable();
        Piece rook = board[row][rookCol];

        // Verifica se tem torre, mesma cor, e se não se moveu
        if (rook == null || rook.getPiece() != PiecesEnum.ROOK || rook.isMoved()
                || rook.getColor() != this.getColor()) {
            return false;
        }

        // Verifica se as casas entre rei e torre estão vazias
        for (int col : emptyCols) {
            if (board[row][col] != null) {
                return false;
            }
        }

        // Verifica se as casas que o rei vai passar estão sob ataque
        for (int col : getKingPathCols(this.getCol(), rookCol)) {
            if (table.isSquareAttacked(row, col, this.getColor())) {
                return false;
            }
        }

        return true;
    }

    // Retorna colunas pelas quais o rei passará (inclusive destino) — sem incluir a
    // torre
    private int[] getKingPathCols(int kingCol, int rookCol) {
        if (rookCol == 7) {
            return new int[] { 5, 6 }; // roque curto
        } else {
            return new int[] { 3, 2 }; // roque longo
        }
    }

}
