package com.natucciEngine.entities;

import java.util.ArrayList;
import java.util.stream.Collectors;

import com.natucciEngine.core.InputParser.Move;
import com.natucciEngine.entities.pieces.Bishop;
import com.natucciEngine.entities.pieces.King;
import com.natucciEngine.entities.pieces.Knight;
import com.natucciEngine.entities.pieces.Pawn;
import com.natucciEngine.entities.pieces.Queen;
import com.natucciEngine.entities.pieces.Rook;
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
    private boolean isCapurableAnPassant = false;
    private ArrayList<Move> possibleMoves = new ArrayList<Move>();

    public ArrayList<Move> generatePossibleMoves(Table table) {
        return null;
    }

    public Boolean isMoveValid(Table table, Move move) {
        return this.getPossibleMoves().stream().filter(movec -> {
            return move.equals(movec);
        }).collect(Collectors.toList())
                .size() == 1;
    }

    protected void copyAllValues(Piece t) {
        this.setCol(t.getCol());
        this.setRow(t.getRow());
        this.setColor(t.getColor());
        this.setMoved(t.isMoved());
        this.setPiece(t.getPiece());
        this.setPossibleMoves(new ArrayList<>(t.getPossibleMoves()));
        this.setCapurableAnPassant(t.isCapurableAnPassant());
    }

    public Piece copyPiece(Piece t) {
        if (t instanceof Rook) {
            Rook copy = new Rook();
            copy.copyAllValues(t);
            return copy;
        } else if (t instanceof Queen) {
            Queen copy = new Queen();
            copy.copyAllValues(t);
            return copy;
        } else if (t instanceof Bishop) {
            Bishop copy = new Bishop();
            copy.copyAllValues(t);
            return copy;
        } else if (t instanceof Knight) {
            Knight copy = new Knight();
            copy.copyAllValues(t);
            return copy;
        } else if (t instanceof Pawn) {
            Pawn copy = new Pawn();
            copy.copyAllValues(t);
            return copy;
        } else if (t instanceof King) {
            King copy = new King();
            copy.copyAllValues(t);
            return copy;
        } else {
            throw new IllegalArgumentException("Tipo de peça desconhecido: " + t.getClass());
        }
    }

}
