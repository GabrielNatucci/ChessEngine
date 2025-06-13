package com.natucciEngine.core.engine;

import java.util.ArrayList;

import com.natucciEngine.core.InputParser.Move;
import com.natucciEngine.entities.Table;
import com.natucciEngine.enuns.PieceColorEnum;

public class EngineCore {
    public long countMoves(Table table, PieceColorEnum turn, int depth) {
        if (depth <= 0) {
            return 1;
        }

        ArrayList<Move> moves = table.generatePossibleMovesAllPieces(turn);
        long moveCount = 0;

        for (Move move : moves) {
            Table localTable = table.clone();
            localTable.handleMove(move, turn);
            moveCount += countMoves(localTable,
                    turn.equals(PieceColorEnum.WHITE) ? PieceColorEnum.BLACK : PieceColorEnum.WHITE, depth - 1);
        }

        return moveCount;
    }
}
