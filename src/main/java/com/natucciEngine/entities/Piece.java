package com.natucciEngine.entities;

import java.util.ArrayList;

import com.natucciEngine.core.InputParser.Move;
import com.natucciEngine.enuns.PieceColorEnum;
import com.natucciEngine.enuns.PiecesEnum;

public class Piece {
    private PieceColorEnum color;
    private PiecesEnum piece;
    private int col, row;

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public Piece(PieceColorEnum color) {
        this.color = color;
    }

    public Piece() {
    }

    public PieceColorEnum getColor() {
        return color;
    }

    public void setColor(PieceColorEnum color) {
        this.color = color;
    }

    public PiecesEnum getPiece() {
		return piece;
	}

	public void setPiece(PiecesEnum piece) {
		this.piece = piece;
	}


    public ArrayList<Move> getPossibleMoves(Table table) {
        return null;
    }

    public Boolean isMoveValid(Table table, Move move) {
        return null;
    }
}
