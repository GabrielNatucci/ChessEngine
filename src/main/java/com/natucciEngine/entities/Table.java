package com.natucciEngine.entities;

import java.util.Objects;

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
public class Table {
    // private static final Logger logger = LoggerFactory.getLogger(Table.class);

    Piece[][] localTable;
    boolean[][][] attackedSquares;

    public static final int HEIGHT = 8;
    public static final int LENGTH = 8;

    public Table() {
        this.setLocalTable(new Piece[LENGTH][HEIGHT]);

        for (int i = 0; i < localTable.length; i++) {
            getLocalTable()[1][i] = new Pawn(PieceColorEnum.BLACK, i, 1);
            getLocalTable()[6][i] = new Pawn(PieceColorEnum.WHITE, i, 6);
        }

        setHeavyPiecesList(PieceColorEnum.BLACK);
        setHeavyPiecesList(PieceColorEnum.WHITE);
        this.updateAttackedSquares();
    }

    private void setHeavyPiecesList(PieceColorEnum color) {
        int linha = 0;

        if (color.equals(PieceColorEnum.BLACK)) {
            linha = 0;
        } else {
            linha = 7;
        }

        getLocalTable()[linha][7] = new Rook(color, linha, 7);
        getLocalTable()[linha][0] = new Rook(color, linha, 0);

        getLocalTable()[linha][1] = new Knight(color, linha, 1);
        getLocalTable()[linha][6] = new Knight(color, linha, 6);

        getLocalTable()[linha][2] = new Bishop(color, linha, 2);
        getLocalTable()[linha][5] = new Bishop(color, linha, 5);

        getLocalTable()[linha][3] = new Queen(color, linha, 3);

        getLocalTable()[linha][4] = new King(color, linha, 4);
    }

    public void updateAttackedSquares() {
        this.setAttackedSquares(new boolean[LENGTH][HEIGHT][2]);
        Piece[][] localTable = this.getLocalTable();
        boolean[][][] attackedSquares = this.getAttackedSquares();

        for (int i = 0; i < LENGTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                Piece piece = localTable[i][j];

                if (piece == null)
                    continue;

                piece.generatePossibleMoves(this)
                        .stream()
                        .filter(Objects::nonNull)
                        .forEach(move -> {
                            attackedSquares[move.getToRow()][move.getToCol()][piece.getColor().getColorCode()] = true;
                        });
            }
        }
    }

    public boolean isSquareAttacked(int row, int col, PieceColorEnum color) {
        return this.getAttackedSquares()[row][col][color.getColorCode()];
    }

    public Table(Piece[][] localTable) {
        this.localTable = localTable;
    }

    public Table(Piece[][] localTable, boolean[][][] attackedSquares) {
        this.localTable = localTable;
        this.attackedSquares = attackedSquares;
    }

    public Boolean handleMove(Move move, PieceColorEnum turn) {
        Piece piece = getLocalTable()[move.getFromRow()][move.getFromCol()];
        Piece targetPiece = getLocalTable()[move.getToRow()][move.getToCol()];
        boolean isTargetPieceSameColor = targetPiece == null || targetPiece.getColor() != piece.getColor();

        if (piece != null && turn.equals(piece.getColor()) && piece.isMoveValid(this, move) && isTargetPieceSameColor) {
            piece.setCol(move.getToCol());
            piece.setRow(move.getToRow());
            piece.setMoved(true);

            // esse if tem uma execçao pro peão, que caso ele tenha se movido duas casas,
            // vai ficar como se não tivesse se mexido ainda.
            // isso é pra facilitar o an passant
            if (piece.getPiece().equals(PiecesEnum.PAWN)) {
                piece.setCapurableAnPassant(Math.abs(move.getFromRow() - move.getToRow()) == 2);
            } else {
                piece.setCapurableAnPassant(false);
            }

            getLocalTable()[move.getFromRow()][move.getFromCol()] = null;
            getLocalTable()[move.getToRow()][move.getToCol()] = piece;

            this.updateAttackedSquares();

            System.out.println("Movimento valido!");
            return true;
        }

        System.out.println("Movimento invalido!");
        return false;
    }
}
