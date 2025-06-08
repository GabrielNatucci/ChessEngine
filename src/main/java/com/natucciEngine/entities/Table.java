package com.natucciEngine.entities;

import com.natucciEngine.core.InputParser.Move;
import com.natucciEngine.entities.pieces.Bishop;
import com.natucciEngine.entities.pieces.King;
import com.natucciEngine.entities.pieces.Knight;
import com.natucciEngine.entities.pieces.Queen;
import com.natucciEngine.entities.pieces.Rook;
import com.natucciEngine.enuns.PieceColorEnum;

import lombok.Getter;
import lombok.Setter;

public class Table {
    // private static final Logger logger = LoggerFactory.getLogger(Table.class);

    @Getter
    @Setter
    Piece[][] localTable;
    public static final int height = 8;
    public static final int length = 8;

    public Table() {
        this.setLocalTable(new Piece[length][height]);
        // for (int i = 0; i < localTable.length; i++) {
        //     getLocalTable()[1][i] = new Pawn(PieceColorEnum.BLACK, i, 1);
        //     getLocalTable()[6][i] = new Pawn(PieceColorEnum.WHITE, i, 6);
        // }
        setHeavyPiecesList(PieceColorEnum.BLACK);
        setHeavyPiecesList(PieceColorEnum.WHITE);
    }

    private void setHeavyPiecesList(PieceColorEnum color) {
        int linha = 0;

        if (color.equals(PieceColorEnum.BLACK)) {
            linha = 0;
        } else {
            linha = 7;
        }

        getLocalTable()[linha][0] = new Rook(color, 0, linha);
        getLocalTable()[linha][7] = new Rook(color, 0, linha);

        getLocalTable()[linha][1] = new Knight(color, 1, linha);
        getLocalTable()[linha][6] = new Knight(color, 6, linha);

        getLocalTable()[linha][2] = new Bishop(color, 2, linha);
        getLocalTable()[linha][5] = new Bishop(color, 5, linha);

        getLocalTable()[linha][3] = new Queen(color, 3, linha);
        getLocalTable()[linha][4] = new King(color, 4, linha);
    }

    public Table(Piece[][] localTable) {
        this.localTable = localTable;
    }

    public Boolean handleMove(Move move) {
        Piece piece = getLocalTable()[move.getFromRow()][move.getFromCol()];
        Piece targetPiece = getLocalTable()[move.getToRow()][move.getToCol()];
        boolean isTargetPieceSameColor = targetPiece == null || targetPiece.getColor() != piece.getColor();

        if (piece.isMoveValid(this, move) && isTargetPieceSameColor) {
            piece.setCol(move.getToCol());
            piece.setRow(move.getToRow());
            
            getLocalTable()[move.getFromRow()][move.getFromCol()] = null;
            getLocalTable()[move.getToRow()][move.getToCol()] = piece;

            System.out.println("Movimento valido!");
            return true;
        }

        System.out.println("Movimento invalido!");
        return false;
    }
}
