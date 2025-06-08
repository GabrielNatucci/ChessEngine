package com.natucciEngine.utils;

import com.natucciEngine.entities.Piece;
import com.natucciEngine.entities.Table;
import com.natucciEngine.enuns.PieceColorEnum;

public class PrintTable {
    public static void main(Table table) {
        Piece[][] localTable = table.getLocalTable();

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
}
