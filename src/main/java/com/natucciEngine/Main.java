package com.natucciEngine;

import com.natucciEngine.core.InputParser.ChessInputParser;
import com.natucciEngine.core.InputParser.Move;
import com.natucciEngine.entities.Table;
import com.natucciEngine.utils.PrintTable;

public class Main { 
    private static Table table;

	public static void main(String[] args) {
        setTable(new Table());
        PrintTable.main(getTable());
        Move move = ChessInputParser.main();
        System.out.println(move);

        if (getTable().handleMove(move)) {
            PrintTable.main(getTable());
        }
    }

	public static Table getTable() {
		return table;
	}

	public static void setTable(Table table) {
		Main.table = table;
	}
}
