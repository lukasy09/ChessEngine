import com.agh.technology.chess.engine.model.element.Color;
import com.agh.technology.chess.engine.model.state.BoardState;

import com.agh.technology.chess.engine.move.generation.MoveGenerator;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
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
}