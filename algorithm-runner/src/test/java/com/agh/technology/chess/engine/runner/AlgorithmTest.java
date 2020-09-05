package com.agh.technology.chess.engine.runner;

import com.agh.technology.chess.engine.evaluation.ScoreEvaluation;
import com.agh.technology.chess.engine.model.element.Color;
import com.agh.technology.chess.engine.model.state.BoardState;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class AlgorithmTest {
    public static String displayAsBinary(long value){
        String binaryString = Long.toBinaryString(value);
        return String.format("%64s", binaryString).replace(' ', '0');
    }
    private static long parseLong(String s, int base) {
        return new BigInteger(s.replaceAll(" ", ""), base).longValue();
    }
    public static String displayAsFormattedBinary(long value){
        String unreversedBoard = displayAsBinary(value).replaceAll("(.{8})", "$1\n");
        return Arrays.stream(unreversedBoard.split("\n")).map(line -> new StringBuilder(line).reverse().toString()).collect(Collectors.joining("\n"));
    }
    @Test
    public void minmaxTest() throws Exception {
        BoardState boardState = BoardState.getStartingState();

        Algorithm algorithm = new Algorithm();

        Long bishop = parseLong("00100100 00000000 000000000000000000000000000000000000000000000000", 2);
        Long king = parseLong("00000000 10000000000000000000000000000000000000000000000000000000", 2);
        Long knight = parseLong("00000000 00100000000000000000000000000000000000000000000000000000", 2);
        Long pawn = parseLong("00000000 00000000 100000000000000000000000000000000000000000000000", 2);
        Long queen = parseLong("00000000 00000000 00000000 0010000000000000000000000000000000000000", 2);
        Long rook = parseLong("00000000 00000000 00000000 0010000000000000000000000000000000000000", 2);

        Long wking = parseLong("00000000 00010000000000000000000000000000000000000000000000000000", 2);
        Long wknight = parseLong("00000000 00000001000000000000000000000000000000000000000000000000", 2);
        Long wbishop = parseLong("00000000 00000000 000000000000000000000000000000000000000000000000", 2);
        Long wpawn = parseLong("00000000 00000000 000000000000000000000000000000000000000011111111", 2);
        Long wqueen = parseLong("00000000 00000000 00000000 0000000000000000000000000000000000000000", 2);
        Long wrook = parseLong("00000000 00000000 00000000 0000000000000000000000000000000000000000", 2);

        boardState.getBlackState().setBishop(bishop);
        boardState.getBlackState().setKing(king);
        boardState.getBlackState().setKnight(knight);
        boardState.getBlackState().setPawn(pawn);
        boardState.getBlackState().setQueen(queen);
        boardState.getBlackState().setRook(rook);

        boardState.getWhiteState().setBishop(wbishop);
        boardState.getWhiteState().setKing(wking);
        boardState.getWhiteState().setKnight(wknight);
        boardState.getWhiteState().setPawn(wpawn);
        boardState.getWhiteState().setQueen(wqueen);
        boardState.getWhiteState().setRook(wrook);

        BoardState actualboardState = algorithm.minmax(boardState, 3, Color.WHITE);
        Long expectedOccupied = parseLong("0010010010100001100000000010000000000000000000000000000011111111",2);
        Long actualOccupied = actualboardState.getOccupied();
        Assert.assertEquals(expectedOccupied, actualOccupied) ;

    }



}