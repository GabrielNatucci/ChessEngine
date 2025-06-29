package com.natucciEngine.entities.pieces;

import java.util.ArrayList;

import com.natucciEngine.core.InputParser.Move;
import com.natucciEngine.entities.Piece;
import com.natucciEngine.entities.Table;
import com.natucciEngine.enuns.PieceColorEnum;
import com.natucciEngine.enuns.PiecesEnum;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Knight extends Piece {
    public Knight(PieceColorEnum color, int row, int col) {
        this.setPiece(PiecesEnum.KNIGHT);
        this.setColor(color);
        this.setRow(row);
        this.setCol(col);
    }

    @Override
	public ArrayList<Move> generatePossibleMoves(Table table) {
        ArrayList<Move> moves = new ArrayList<>();
        int[][] directions = {
                { -2, -1 }, { -2, +1 }, // cima esquerda / cima direita
                { -1, -2 }, { -1, +2 }, // esquerda cima / direita cima
                { +1, -2 }, { +1, +2 }, // esquerda baixo / direita baixo
                { +2, -1 }, { +2, +1 } // baixo esquerda / baixo direita
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

        this.setPossibleMoves(moves);

        return moves;
	}

    private boolean isInsideBoard(int row, int col) {
        return row >= 0 && row < Table.HEIGHT && col >= 0 && col < Table.LENGTH;
    }
}
