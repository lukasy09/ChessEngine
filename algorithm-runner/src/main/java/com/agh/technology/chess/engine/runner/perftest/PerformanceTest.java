package com.agh.technology.chess.engine.runner.perftest;

import com.agh.technology.chess.engine.model.element.Color;
import com.agh.technology.chess.engine.model.state.BoardState;
import com.agh.technology.chess.engine.runner.Algorithm;

import static java.lang.Long.parseLong;

public class PerformanceTest {


    private static void simpleBoardTest() {
        System.out.println("Initial board performance test");
        BoardState boardState = BoardState.getStartingState();
        Algorithm algorithm = new Algorithm();
        for (int i = 1; i < 9; i++) {
            long startTime = System.nanoTime();
            algorithm.minmax(boardState, i, Color.WHITE);
            long endTime = System.nanoTime();
            System.out.println("That took " + (endTime - startTime) + " nanoseconds");
        }
    }

    private static void minMaxBothKingPerfTest() {
        System.out.println("Board performance test");
        BoardState boardState = BoardState.getStartingState();
        Algorithm algorithm = new Algorithm();
        long bishop = parseLong("0010010000000000000000000000000000000000000000000000000000000000", 2);
        long king = parseLong("0000000010000000000000000000000000000000000000000000000000000000", 2);
        long knight = parseLong("0000000000100000000000000000000000000000000000000000000000000000", 2);
        long pawn = parseLong("0000000000000000100000000000000000000000000000000000000000000000", 2);
        long queen = parseLong("0000000000000000000000000010000000000000000000000000000000000000", 2);
        long rook = parseLong("0000000000000000000000000010000000000000000000000000000000000000", 2);

        long whiteKing = parseLong("0000000000010000000000000000000000000000000000000000000000000000", 2);
        long whiteKnight = parseLong("0000000000000001000000000000000000000000000000000000000000000000", 2);
        long whiteBishop = parseLong("0000000000000000000000000000000000000000000000000000000000000000", 2);
        long whitePawn = parseLong("0000000000000000000000000000000000000000000000000000000011111111", 2);
        long whiteQueen = parseLong("0000000000000000000000000000000000000000000000000000000000000000", 2);
        long whiteRook = parseLong("0000000000000000000000000000000000000000000000000000000000000000", 2);

        boardState.getBlackState().setBishop(bishop);
        boardState.getBlackState().setKing(king);
        boardState.getBlackState().setKnight(knight);
        boardState.getBlackState().setPawn(pawn);
        boardState.getBlackState().setQueen(queen);
        boardState.getBlackState().setRook(rook);

        boardState.getWhiteState().setBishop(whiteBishop);
        boardState.getWhiteState().setKing(whiteKing);
        boardState.getWhiteState().setKnight(whiteKnight);
        boardState.getWhiteState().setPawn(whitePawn);
        boardState.getWhiteState().setQueen(whiteQueen);
        boardState.getWhiteState().setRook(whiteRook);
        for (int i = 1; i < 9; i++) {
            long startTime = System.nanoTime();
            algorithm.minmax(boardState, i, Color.WHITE);
            long endTime = System.nanoTime();
            System.out.println("That took " + (endTime - startTime) + " nanoseconds");
        }
    }

    public static void main(String[] args) {
        simpleBoardTest();
        minMaxBothKingPerfTest();
    }
}
