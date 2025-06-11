package com.natucciEngine;

import com.natucciEngine.core.InputParser.ChessInputParser;
import com.natucciEngine.core.InputParser.Move;
import com.natucciEngine.entities.Table;
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

        Move move;
        while (true) {
            while (true) {
                move = ChessInputParser.main();
                System.out.println(move);
                if (getTable().handleMove(move)){
                    break;
                }
            }
            PrintTable.main(getTable());
        }
    }
}
