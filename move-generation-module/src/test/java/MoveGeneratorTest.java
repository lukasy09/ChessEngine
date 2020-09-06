import com.agh.technology.chess.engine.evaluation.position.PositionRatingEvaluation;
import com.agh.technology.chess.engine.model.element.Color;
import com.agh.technology.chess.engine.model.element.PieceType;
import com.agh.technology.chess.engine.model.state.BoardState;

import com.agh.technology.chess.engine.move.generation.AttackMasks;
import com.agh.technology.chess.engine.move.generation.MoveGenerator;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

public class MoveGeneratorTest {

    public static String displayAsFormattedBinary(long value){
        String unreversedBoard = displayAsBinary(value).replaceAll("(.{8})", "$1\n");
        return Arrays.stream(unreversedBoard.split("\n")).map(line -> new StringBuilder(line).reverse().toString()).collect(Collectors.joining("\n"));
    }

    public static String displayAsBinary(long value){
        String binaryString = Long.toBinaryString(value);
        return String.format("%64s", binaryString).replace(' ', '0');
    }
    private static long parseLong(String s, int base) {
        return new BigInteger(s.replaceAll(" ", ""), base).longValue();
    }

    @Test
    public void generateAllPossibleWhiteStates() {
        BoardState boardState = BoardState.getStartingState();
        MoveGenerator moveGenerator = MoveGenerator.getInstance();

        Set<String> nextPossibleStates = new HashSet<String>(
                                        Arrays.asList("11111111\n11111111\n00000000\n00000000\n00000000\n00100000\n11111111\n10111111"
                                                ,"11111111\n11111111\n00000000\n00000000\n00000000\n00000100\n11111111\n11111101"
                                                ,"11111111\n11111111\n00000000\n00000000\n00000000\n00000100\n11111011\n11111111"
                                                ,"11111111\n11111111\n00000000\n00000000\n00000000\n10000000\n11111111\n10111111"
                                                ,"11111111\n11111111\n00000000\n00000000\n00000000\n00001000\n11110111\n11111111"
                                                ,"11111111\n11111111\n00000000\n00000000\n00000000\n00010000\n11101111\n11111111"
                                                ,"11111111\n11111111\n00000000\n00000000\n00010000\n00000000\n11101111\n11111111"
                                                ,"11111111\n11111111\n00000000\n00000000\n00000000\n00000001\n11111111\n11111101"
                                                ,"11111111\n11111111\n00000000\n00000000\n10000000\n00000000\n01111111\n11111111"
                                                ,"11111111\n11111111\n00000000\n00000000\n01000000\n00000000\n10111111\n11111111"
                                                ,"11111111\n11111111\n00000000\n00000000\n00100000\n00000000\n11011111\n11111111"
                                                ,"11111111\n11111111\n00000000\n00000000\n00001000\n00000000\n11110111\n11111111"
                                                ,"11111111\n11111111\n00000000\n00000000\n00000100\n00000000\n11111011\n11111111"
                                                ,"11111111\n11111111\n00000000\n00000000\n00000000\n00000010\n11111101\n11111111"
                                                ,"11111111\n11111111\n00000000\n00000000\n00000000\n00000001\n11111110\n11111111"
                                                ,"11111111\n11111111\n00000000\n00000000\n00000010\n00000000\n11111101\n11111111"
                                                ,"11111111\n11111111\n00000000\n00000000\n00000001\n00000000\n11111110\n11111111"
                                                ,"11111111\n11111111\n00000000\n00000000\n00000000\n10000000\n01111111\n11111111"
                                                ,"11111111\n11111111\n00000000\n00000000\n00000000\n00100000\n11011111\n11111111"
                                                ,"11111111\n11111111\n00000000\n00000000\n00000000\n01000000\n10111111\n11111111"));


        Set<BoardState> actualNextPossibleStates =  moveGenerator.generateNextPossibleStates(boardState, Color.WHITE);
        Set<String> actualNextPossibleStatesF = new HashSet<String>();
        for (BoardState x:actualNextPossibleStates)
            actualNextPossibleStatesF.add(MoveGeneratorTest.displayAsFormattedBinary(x.getOccupied()));

        Assert.assertTrue(actualNextPossibleStatesF.equals(nextPossibleStates));

    }

    @Test
    public void generateAllPossibleBlackStates() {
        BoardState boardState = BoardState.getStartingState();
        MoveGenerator moveGenerator = MoveGenerator.getInstance();

        Set<String> nextPossibleStates = new HashSet<String>(
                Arrays.asList("11111101\n11111111\n00000100\n00000000\n00000000\n00000000\n11111111\n11111111"
                        ,"11111111\n01111111\n00000000\n10000000\n00000000\n00000000\n11111111\n11111111"
                        ,"11111111\n10111111\n00000000\n01000000\n00000000\n00000000\n11111111\n11111111"
                        ,"11111111\n11111011\n00000000\n00000100\n00000000\n00000000\n11111111\n11111111"
                        ,"11111111\n11110111\n00000000\n00001000\n00000000\n00000000\n11111111\n11111111"
                        ,"11111111\n11111110\n00000001\n00000000\n00000000\n00000000\n11111111\n11111111"
                        ,"11111111\n11011111\n00100000\n00000000\n00000000\n00000000\n11111111\n11111111"
                        ,"10111111\n11111111\n00100000\n00000000\n00000000\n00000000\n11111111\n11111111"
                        ,"11111111\n11111101\n00000010\n00000000\n00000000\n00000000\n11111111\n11111111"
                        ,"11111111\n11101111\n00000000\n00010000\n00000000\n00000000\n11111111\n11111111"
                        ,"11111111\n11111101\n00000000\n00000010\n00000000\n00000000\n11111111\n11111111"
                        ,"11111111\n11011111\n00000000\n00100000\n00000000\n00000000\n11111111\n11111111"
                        ,"11111111\n11110111\n00001000\n00000000\n00000000\n00000000\n11111111\n11111111"
                        ,"11111111\n01111111\n10000000\n00000000\n00000000\n00000000\n11111111\n11111111"
                        ,"11111111\n10111111\n01000000\n00000000\n00000000\n00000000\n11111111\n11111111"
                        ,"11111111\n11101111\n00010000\n00000000\n00000000\n00000000\n11111111\n11111111"
                        ,"11111111\n11111110\n00000000\n00000001\n00000000\n00000000\n11111111\n11111111"
                        ,"10111111\n11111111\n10000000\n00000000\n00000000\n00000000\n11111111\n11111111"
                        ,"11111111\n11111011\n00000100\n00000000\n00000000\n00000000\n11111111\n11111111"
                        ,"11111101\n11111111\n00000001\n00000000\n00000000\n00000000\n11111111\n11111111"));
        Set<BoardState> actualNextPossibleStates =  moveGenerator.generateNextPossibleStates(boardState, Color.BLACK);
        Set<String> actualNextPossibleStatesF = new HashSet<String>();
        for (BoardState x:actualNextPossibleStates)
            actualNextPossibleStatesF.add(MoveGeneratorTest.displayAsFormattedBinary(x.getOccupied()));

        Assert.assertTrue(actualNextPossibleStatesF.equals(nextPossibleStates));

    }
    @Test
    public void PositionRating() throws Exception
    {
        BoardState boardState = BoardState.getStartingState();
        Long bishop = parseLong("00100100 00000000 000000000000000000000000000000000000000000000000", 2);
        Long king = parseLong("00000000 10000000000000000000000000000000000000000000000000000000", 2);
        Long knight = parseLong("00000000 00100000000000000000000000000000000000000000000000000000", 2);
        Long pawn = parseLong("00000000 00000000 100000000000000000000000000000000000000000000000", 2);
        Long queen = parseLong("00000000 00000000 00000000 0010000000000000000000000000000000000000", 2);
        Long rook = parseLong("00000000 00000000 00000000 0010000000000000000000000000000000000000", 2);

        Long wking = parseLong("00000000 00010000000000000000000000000000000000000000000000000000", 2);
        Long wknight = parseLong("00000000 00000001000000000000000000000000000000000000000000000000", 2);
        Long wbishop = parseLong("00000000 00000000 000000000000000000000000000000000000000000000000", 2);
        Long wpawn = parseLong("00000000 00000000 000000000000000000000000000000000000000000000000", 2);
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

        PositionRatingEvaluation positionEvaluation = new PositionRatingEvaluation();
        int actualRating = positionEvaluation.evaluatePositionRating(boardState);
        Assert.assertEquals(-100, actualRating) ;
    }

}