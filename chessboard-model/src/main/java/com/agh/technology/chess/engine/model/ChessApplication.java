package com.agh.technology.chess.engine.model;

import com.agh.technology.chess.engine.model.model.ChessBoard;

public class ChessApplication {

    public static void main(String[] args) {

        ChessBoard chessBoard = ChessBoard.getInstance();
        ChessBoard.ChessBoardBuilder builder = chessBoard.new ChessBoardBuilder();

        builder.buildPositions(chessBoard.getInitialBoard());

        System.out.println(chessBoard.getWHITE_ROOK());
        System.out.println(chessBoard.displayAsBinary(chessBoard.getWHITE_ROOK()));
        System.out.println(chessBoard.displayAsFormattedBinary(chessBoard.getWHITE_ROOK()));
    }
}
