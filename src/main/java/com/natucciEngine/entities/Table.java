package com.natucciEngine.entities;

import java.lang.reflect.Array;
import java.util.ArrayList;
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
    private Piece[][] localTable;
    private boolean[][][] attackedSquares;
    private ArrayList<Move> possible;
    private ArrayList<Piece> whitePeople;
    private ArrayList<Piece> blackPeople;
    private ArrayList<Piece> whitePieces;
    private ArrayList<Piece> blackPieces;

    private King blackKing, whiteKing;
    public static final int HEIGHT = 8;
    public static final int LENGTH = 8;

    public Table() {
        this.setLocalTable(new Piece[LENGTH][HEIGHT]);
        this.setAttackedSquares(new boolean[LENGTH][HEIGHT][2]);
        this.setWhitePeople(new ArrayList<Piece>());
        this.setBlackPeople(new ArrayList<Piece>());
    }

    public void initTable() {
        for (int i = 0; i < localTable.length; i++) {
            getLocalTable()[1][i] = new Pawn(PieceColorEnum.BLACK, i, 1);
            this.getBlackPeople().add(getLocalTable()[1][i]);

            getLocalTable()[6][i] = new Pawn(PieceColorEnum.WHITE, i, 6);
            this.getWhitePeople().add(getLocalTable()[6][i]);
        }

        this.getWhitePeople().addAll(this.getWhitePeople());
        this.getWhitePieces().addAll(setHeavyPiecesList(PieceColorEnum.WHITE));

        this.getBlackPieces().addAll(this.getBlackPeople());
        this.getBlackPieces().addAll(setHeavyPiecesList(PieceColorEnum.BLACK));

        this.updateAttackedSquares();
    }

    private ArrayList<Piece> setHeavyPiecesList(PieceColorEnum color) {
        ArrayList<Piece> pieces = new ArrayList<Piece>();
        int linha = 0;

        if (color.equals(PieceColorEnum.BLACK)) {
            linha = 0;
        } else {
            linha = 7;
        }

        King king = new King(color, linha, 4);

        getLocalTable()[linha][0] = new Rook(color, linha, 0);
        getLocalTable()[linha][1] = new Knight(color, linha, 1);
        getLocalTable()[linha][2] = new Bishop(color, linha, 2);
        getLocalTable()[linha][3] = new Queen(color, linha, 3);
        getLocalTable()[linha][4] = king;
        getLocalTable()[linha][5] = new Bishop(color, linha, 5);
        getLocalTable()[linha][6] = new Knight(color, linha, 6);
        getLocalTable()[linha][7] = new Rook(color, linha, 7);

        if (color.equals(PieceColorEnum.WHITE)) {
            this.setWhiteKing(king);
        } else {
            this.setBlackKing(king);
        }

        for (int i = 0; i < Table.LENGTH; i++) {
            pieces.add(getLocalTable()[linha][i]);
        }

        return pieces;
    }

    public void updateAttackedSquares() {
        this.setAttackedSquares(new boolean[LENGTH][HEIGHT][2]);
        Piece[][] localTable = this.getLocalTable();
        boolean[][][] attackedSquares = this.getAttackedSquares();

        for (int i = 0; i < LENGTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                Piece piece = localTable[i][j];

                if (piece != null) {
                    piece.generatePossibleMoves(this)
                            .stream()
                            .forEach(move -> {
                                attackedSquares[move.getToRow()][move.getToCol()][piece.getColor()
                                        .getColorCode()] = true;
                            });
                }
            }
        }
    }

    public ArrayList<Move> generatePossibleMovesAllPieces(PieceColorEnum color) {
        Piece[][] localTable = this.getLocalTable();
        ArrayList<Move> moves = new ArrayList<Move>();

        for (int i = 0; i < LENGTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                Piece piece = localTable[i][j];

                if (piece != null) {
                    if (piece.getColor().equals(color)) {
                        moves.addAll(piece.getPossibleMoves());
                    }
                }
            }
        }

        ArrayList<Move> movesFr = new ArrayList<Move>();
        for (Move move : moves) {
            Table table = this.clone();
            table.handleMove(move, color);

            King king = new King();

            PieceColorEnum tmpColor = PieceColorEnum.WHITE;
            if (color == PieceColorEnum.WHITE) {
                tmpColor = PieceColorEnum.BLACK;
                king = table.getWhiteKing();
            } else {
                king = table.getBlackKing();
            }

            if (!table.getAttackedSquares()[king.getRow()][king.getCol()][tmpColor.getColorCode()]) {
                movesFr.add(move);
            }

        }

        return movesFr;
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

        if (piece == null) {
            return false;
        }

        if (turn.equals(piece.getColor()) && piece.isMoveValid(this, move)) {
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
            this.cleanAnPassant(turn);

            return true;
        }

        return false;
    }

    private void cleanAnPassant(PieceColorEnum color) {
        ArrayList<Piece> pieces = new ArrayList<Piece>();

        if (color.equals(PieceColorEnum.WHITE)) {
            pieces = getBlackPeople();
        } else {
            pieces = getWhitePeople();
        }

        for (Piece piece : pieces) {
            piece.setCapurableAnPassant(false);
        }
    }

    @Override
    public Table clone() {
        Table table = new Table();

        for (int i = 0; i < HEIGHT; i++) {
            for (int j = 0; j < LENGTH; j++) {
                if (this.getLocalTable()[i][j] != null) {
                    Piece piece = this.getLocalTable()[i][j];

                    table.getLocalTable()[i][j] = piece.copyPiece(piece);
                    table.getLocalTable()[i][j] = piece.copyPiece(piece);

                    if (piece.getPiece().equals(PiecesEnum.KING)) {
                        if (piece.getColor().equals(PieceColorEnum.WHITE)) {
                            table.setWhiteKing((King) table.getLocalTable()[i][j]);
                        } else {
                            table.setBlackKing((King) table.getLocalTable()[i][j]);
                        }
                    }

                }
            }
        }

        return table;
    }
}
