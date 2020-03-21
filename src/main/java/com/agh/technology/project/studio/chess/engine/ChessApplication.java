package com.agh.technology.project.studio.chess.engine;

import com.agh.technology.project.studio.chess.engine.model.ChessBoard;

public class ChessApplication {

    public static void main(String[] args) {

        ChessBoard chessBoard = ChessBoard.getInstance();
        chessBoard.generateInitialPositions(chessBoard.getInitialBoard());

        System.out.println(chessBoard.BLACK_ROOK);
    }
}
