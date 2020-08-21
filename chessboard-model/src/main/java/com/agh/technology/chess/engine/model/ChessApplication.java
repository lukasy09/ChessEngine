package com.agh.technology.chess.engine.model;

import com.agh.technology.chess.engine.model.element.Board;

public class ChessApplication {

    public static void main(String[] args) {

        Board board = Board.getInstance();
        Board.ChessBoardBuilder builder = board.new ChessBoardBuilder();

        builder.buildPositions(board.getInitialBoard());

        System.out.println(board.getWHITE_ROOK());
        System.out.println(board.displayAsBinary(board.getWHITE_ROOK()));
        System.out.println(board.displayAsFormattedBinary(board.getWHITE_ROOK()));
    }
}
