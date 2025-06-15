package com.natucciEngine;

import java.util.ArrayList;

import com.natucciEngine.core.InputParser.ChessInputParser;
import com.natucciEngine.core.InputParser.Move;
import com.natucciEngine.core.engine.EngineCore;
import com.natucciEngine.entities.Table;
import com.natucciEngine.enuns.PieceColorEnum;
import com.natucciEngine.utils.PrintTable;

import lombok.Getter;
import lombok.Setter;

public class Main {
    @Getter
    @Setter
    private static Table table;

    private static EngineCore engine = new EngineCore();

    public static void main(String[] args) {
        setTable(new Table());
        getTable().initTable();

        PrintTable.main(getTable());
        PieceColorEnum turn = PieceColorEnum.WHITE;

        Move move;
        // engine.countMoves(table, PieceColorEnum.WHITE, 5);

        while (true) {
            while (true) {
                // PrintTable.printAttacked(table);
                ArrayList<Move> moves = new ArrayList<Move>(getTable().generatePossibleMovesAllPieces(turn));
                System.out.println(moves.size());
                System.out.println(moves);
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
