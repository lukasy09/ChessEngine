package com.agh.technology.project.studio.chess.engine.model;

import static com.agh.technology.project.studio.chess.engine.model.ChessPiece.*;

public class ChessBoard {
    public long BLACK_PAWN, BLACK_KNIGHT, BLACK_BISHOP, BLACK_ROOK, BLACK_QUEEN, BLACK_KING,
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

    public void setInitialBoard(ChessPiece[][] initialBoard) {
        this.initialBoard = initialBoard;
    }

    private ChessPiece[][] initialBoard = {
            {BR, BN, BB, BQ, BK, BP, BN, BR},
            {BP, BP, BP, BP, BP, BP, BP, BP},

            {NO, NO, NO, NO, NO, NO, NO, NO},
            {NO, NO, NO, NO, NO, NO, NO, NO},
            {NO, NO, NO, NO, NO, NO, NO, NO},
            {NO, NO, NO, NO, NO, NO, NO, NO},
            {NO, NO, NO, NO, NO, NO, NO, NO},

            {WP, WP, WP, WP, WP, WP, WP, WP},
            {WR, WN, WB, WQ, WK, WB, WN, WR}
    };

    public void generateInitialPositions(ChessPiece[][] board) {

        for (int i = 0; i < 64; i++) {
            switch (board[i / 8][i % 8]) {
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
                case BQ:
                    BLACK_QUEEN |= (1L << i);
                    break;
                case BK:
                    BLACK_KING |= (1L << i);
                    break;
            }
        }
    }
}
