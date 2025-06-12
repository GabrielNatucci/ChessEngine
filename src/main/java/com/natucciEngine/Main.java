package com.natucciEngine;

import com.natucciEngine.core.InputParser.ChessInputParser;
import com.natucciEngine.core.InputParser.Move;
import com.natucciEngine.entities.Table;
import com.natucciEngine.enuns.PieceColorEnum;
import com.natucciEngine.utils.PrintTable;

import lombok.Getter;
import lombok.Setter;

public class Main {
    @Getter
    @Setter
    private static Table table;

    public static void main(String[] args) {
        setTable(new Table());
        PrintTable.main(getTable());
        PieceColorEnum turn = PieceColorEnum.WHITE;

        Move move;
        while (true) {
            while (true) {
                PrintTable.printAttacked(table);
                move = ChessInputParser.main();
                if (getTable().handleMove(move, turn)) {
                    turn = turn.equals(PieceColorEnum.WHITE) ? PieceColorEnum.BLACK : PieceColorEnum.WHITE;
                    break;
                }
            }
            PrintTable.main(getTable());
        }
    }
}
