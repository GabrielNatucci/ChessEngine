package com.natucciEngine.entities;

import java.util.ArrayList;

import com.natucciEngine.core.InputParser.Move;
import com.natucciEngine.enuns.PieceColorEnum;
import com.natucciEngine.enuns.PiecesEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Piece {
    private PieceColorEnum color;
    private PiecesEnum piece;
    private int col, row;
    private boolean moved = false;
    private ArrayList<Move> possibleMoves;

    public ArrayList<Move> generatePossibleMoves(Table table) {
        ArrayList<Move> moves = new ArrayList<Move>();
        return moves;
    }

    public Boolean isMoveValid(Table table, Move move) {
        return null;
    }
}
