package com.natucciEngine;

import com.natucciEngine.entities.Table;

public class Main { 
    private static Table table;

	public static void main(String[] args) {
        setTable(new Table());
        getTable().printTable();
    }

	public static Table getTable() {
		return table;
	}

	public static void setTable(Table table) {
		Main.table = table;
	}
}
