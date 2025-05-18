package com.natucciEngine.entities;

import com.natucciEngine.enuns.PieceColorEnum;
import com.natucciEngine.enuns.PiecesEnum;

public class Piece {
    private PieceColorEnum color;
    private PiecesEnum piece;

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
}
