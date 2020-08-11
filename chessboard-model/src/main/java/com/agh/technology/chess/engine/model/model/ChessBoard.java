package com.agh.technology.chess.engine.model.model;

import static com.agh.technology.chess.engine.model.model.ChessPiece.*;

public class ChessBoard {
    private long BLACK_PAWN, BLACK_KNIGHT, BLACK_BISHOP, BLACK_ROOK, BLACK_QUEEN, BLACK_KING,
            WHITE_PAWN, WHITE_KNIGHT, WHITE_BISHOP, WHITE_ROOK, WHITE_QUEEN, WHITE_KING = 0L;

    private final int BOARD_DIM = 8;
    private final int BOARD_AREA = 64;

    private static ChessBoard instance;

    private ChessBoard(){}

    public static ChessBoard getInstance() {
        if(instance == null){
            instance = new ChessBoard();
        }
        return instance;
    }

    public ChessPiece[][] getInitialBoard() {
        return initialBoard;
    }

    private ChessPiece[][] initialBoard = {
            {BR, BN, BB, BQ, BK, BP, BN, BR},
            {BP, BP, BP, BP, BP, BP, BP, BP},

            {NO, NO, NO, NO, NO, NO, NO, NO},
            {NO, NO, NO, NO, NO, NO, NO, NO},
            {NO, NO, NO, NO, NO, NO, NO, NO},
            {NO, NO, NO, NO, NO, NO, NO, NO},

            {WP, WP, WP, WP, WP, WP, WP, WP},
            {WR, WN, WB, WQ, WK, WB, WN, WR}
    };


    public String displayAsBinary(long value){
        String binaryString = Long.toBinaryString(value);
        return String.format("%64s", binaryString).replace(' ', '0');
    }

    public String displayAsFormattedBinary(long value){
        return displayAsBinary(value).replaceAll("(.{8})", "$1\n");
    }


    public class ChessBoardBuilder{
        public void buildPositions(ChessPiece[][] board) {

            for (int i = 0; i < BOARD_AREA; i++) {
                switch (board[i / BOARD_DIM][i % BOARD_DIM]) {
                    case WP:
                        WHITE_PAWN |= (1L << i);
                        break;
                    case WN:
                        WHITE_KNIGHT |= (1L << i);
                        break;
                    case WB:
                        WHITE_BISHOP |= (1L << i);
                        break;
                    case WR:
                        WHITE_ROOK |= (1L << i);
                        break;
                    case WQ:
                        WHITE_QUEEN |= (1L << i);
                        break;
                    case WK:
                        WHITE_KING |= (1L << i);
                        break;
                    case BP:
                        BLACK_PAWN |= (1L << i);
                        break;
                    case BB:
                        BLACK_BISHOP |= (1L << i);
                        break;
                    case BN:
                        BLACK_KNIGHT |= (1L << i);
                        break;
                    case BR:
                        BLACK_ROOK |= (1L << i);
                        break;
                    case BQ:
                        BLACK_QUEEN |= (1L << i);
                        break;
                    case BK:
                        BLACK_KING |= (1L << i);
                        break;
                    default:
                        continue;
                }
            }
        }
    }

    public long getOccupied(){
        return BLACK_PAWN|BLACK_KNIGHT|BLACK_BISHOP|BLACK_ROOK|BLACK_QUEEN|BLACK_KING
                |WHITE_PAWN|WHITE_KNIGHT|WHITE_BISHOP|WHITE_ROOK|WHITE_QUEEN|WHITE_KING;
    }

    public long getVacant(){
        return ~getOccupied();
    }

    public long getBLACK_PAWN() {
        return BLACK_PAWN;
    }

    public void setBLACK_PAWN(long BLACK_PAWN) {
        this.BLACK_PAWN = BLACK_PAWN;
    }

    public long getBLACK_KNIGHT() {
        return BLACK_KNIGHT;
    }

    public void setBLACK_KNIGHT(long BLACK_KNIGHT) {
        this.BLACK_KNIGHT = BLACK_KNIGHT;
    }

    public long getBLACK_BISHOP() {
        return BLACK_BISHOP;
    }

    public void setBLACK_BISHOP(long BLACK_BISHOP) {
        this.BLACK_BISHOP = BLACK_BISHOP;
    }

    public long getBLACK_ROOK() {
        return BLACK_ROOK;
    }

    public void setBLACK_ROOK(long BLACK_ROOK) {
        this.BLACK_ROOK = BLACK_ROOK;
    }

    public long getBLACK_QUEEN() {
        return BLACK_QUEEN;
    }

    public void setBLACK_QUEEN(long BLACK_QUEEN) {
        this.BLACK_QUEEN = BLACK_QUEEN;
    }

    public long getBLACK_KING() {
        return BLACK_KING;
    }

    public void setBLACK_KING(long BLACK_KING) {
        this.BLACK_KING = BLACK_KING;
    }

    public long getWHITE_PAWN() {
        return WHITE_PAWN;
    }

    public void setWHITE_PAWN(long WHITE_PAWN) {
        this.WHITE_PAWN = WHITE_PAWN;
    }

    public long getWHITE_KNIGHT() {
        return WHITE_KNIGHT;
    }

    public void setWHITE_KNIGHT(long WHITE_KNIGHT) {
        this.WHITE_KNIGHT = WHITE_KNIGHT;
    }

    public long getWHITE_BISHOP() {
        return WHITE_BISHOP;
    }

    public void setWHITE_BISHOP(long WHITE_BISHOP) {
        this.WHITE_BISHOP = WHITE_BISHOP;
    }

    public long getWHITE_ROOK() {
        return WHITE_ROOK;
    }

    public void setWHITE_ROOK(long WHITE_ROOK) {
        this.WHITE_ROOK = WHITE_ROOK;
    }

    public long getWHITE_QUEEN() {
        return WHITE_QUEEN;
    }

    public void setWHITE_QUEEN(long WHITE_QUEEN) {
        this.WHITE_QUEEN = WHITE_QUEEN;
    }

    public long getWHITE_KING() {
        return WHITE_KING;
    }

    public void setWHITE_KING(long WHITE_KING) {
        this.WHITE_KING = WHITE_KING;
    }
}
