package com.natucciEngine;

import com.natucciEngine.core.InputParser.ChessInputParser;
import com.natucciEngine.core.InputParser.Move;
import com.natucciEngine.entities.Table;

public class Main { 
    private static Table table;

	public static void main(String[] args) {
        setTable(new Table());
        getTable().printTable();
        Move move = ChessInputParser.main();
        System.out.println(move);
    }

	public static Table getTable() {
		return table;
	}

	public static void setTable(Table table) {
		Main.table = table;
	}
}
