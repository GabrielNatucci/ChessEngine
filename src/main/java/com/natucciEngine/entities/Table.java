package com.natucciEngine.entities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.natucciEngine.core.InputParser.Move;
import com.natucciEngine.entities.pieces.Bishop;
import com.natucciEngine.entities.pieces.King;
import com.natucciEngine.entities.pieces.Knight;
import com.natucciEngine.entities.pieces.Pawn;
import com.natucciEngine.entities.pieces.Queen;
import com.natucciEngine.entities.pieces.Rook;
import com.natucciEngine.enuns.PieceColorEnum;
import com.natucciEngine.utils.StringUtils;

public class Table {
    private static final Logger logger = LoggerFactory.getLogger(Table.class);
    Piece[][] localTable;
    public static final int height = 8;
    public static final int length = 8;

    public Table() {
        this.setLocalTable(new Piece[length][height]);
        for (int i = 0; i < localTable.length; i++) {
            getLocalTable()[1][i] = new Pawn(PieceColorEnum.BLACK);
            getLocalTable()[6][i] = new Pawn(PieceColorEnum.WHITE);
        }
        setHeavyPiecesList(PieceColorEnum.BLACK);
        setHeavyPiecesList(PieceColorEnum.WHITE);
    }

    private void setHeavyPiecesList(PieceColorEnum color) {
        int linha = 0;

        if (color.equals(PieceColorEnum.BLACK)) {
            linha = 0;
        } else {
            linha = 7;
        }

        getLocalTable()[linha][0] = new Rook(color);
        getLocalTable()[linha][7] = new Rook(color);

        getLocalTable()[linha][1] = new Knight(color);
        getLocalTable()[linha][6] = new Knight(color);

        getLocalTable()[linha][2] = new Bishop(color);
        getLocalTable()[linha][5] = new Bishop(color);

        getLocalTable()[linha][3] = new Queen(color);
        getLocalTable()[linha][4] = new King(color);
    }

    public Table(Piece[][] localTable) {
        this.localTable = localTable;
    }

    public Piece[][] getLocalTable() {
        return localTable;
    }

    public void setLocalTable(Piece[][] localTable) {
        this.localTable = localTable;
    }

    public void printTable() {
        System.out.println("  -------------------");
        for (int i = 0; i < localTable.length; i++) {
            System.out.print((i - 8) * -1 + " | ");
            for (int j = 0; j < localTable.length; j++) {
                if (localTable[i][j] == null) {
                    System.out.print("-");
                } else if (localTable[i][j].getColor().equals(PieceColorEnum.BLACK)) {
                    System.out.print(localTable[i][j].getPiece().getDescription().toLowerCase());
                } else {
                    System.out.print(localTable[i][j].getPiece().getDescription());
                }
                System.out.print(" ");
            }
            System.out.println("|");
        }

        System.out.println("  -------------------");
        System.out.print("    ");
        for (int i = 0; i < localTable.length; i++) {
            char letra = StringUtils.converteNumeroPaLetra(i + 1);
            System.out.print(letra + " ");
        }
        System.out.println();
    }

    public Boolean handleMove(Move move) {
        Piece piece = getLocalTable()[move.getFromRow()][move.getFromCol()];
        Piece targetPiece = getLocalTable()[move.getToRow()][move.getToCol()];
        boolean isTargetPieceSameColor = targetPiece == null || targetPiece.getColor() != piece.getColor();

        if (piece.isMoveValid(this, move) && isTargetPieceSameColor) {
            getLocalTable()[move.getFromRow()][move.getFromCol()] = null;
            getLocalTable()[move.getToRow()][move.getToCol()] = piece;

            System.out.println("Movimento valido!");
            return true;
        }

        System.out.println("Movimento invalido!");
        return false;
    }
}
