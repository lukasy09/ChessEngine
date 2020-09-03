import com.agh.technology.chess.engine.model.state.BoardState;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

public class BoardStateTest {

    public static String displayAsFormattedBinary(long value){
        String unreversedBoard = displayAsBinary(value).replaceAll("(.{8})", "$1\n");
        return Arrays.stream(unreversedBoard.split("\n")).map(line -> new StringBuilder(line).reverse().toString()).collect(Collectors.joining("\n"));
    }

    public static String displayAsBinary(long value){
        String binaryString = Long.toBinaryString(value);
        return String.format("%64s", binaryString).replace(' ', '0');
    }

    @Test
    public void initiallyOccupied() throws Exception
    {
        BoardState boardState = BoardState.getStartingState();
        String occupied = "11111111\n11111111\n00000000\n00000000\n00000000\n00000000\n11111111\n11111111";
        String actuallyOccupied = BoardStateTest.displayAsFormattedBinary(boardState.getOccupied());
        Assert.assertEquals(occupied, actuallyOccupied) ;
    }

    @Test
    public void initiallyOccupiedWhite() throws Exception
    {
        BoardState boardState = BoardState.getStartingState();
        String occupied = "00000000\n00000000\n00000000\n00000000\n00000000\n00000000\n11111111\n11111111";
        String actuallyOccupied = BoardStateTest.displayAsFormattedBinary(boardState.getWhiteState().getOccupied());
        Assert.assertEquals(occupied, actuallyOccupied) ;
    }
    @Test
    public void initiallyOccupiedBlack() throws Exception
    {
        BoardState boardState = BoardState.getStartingState();
        String occupied = "11111111\n11111111\n00000000\n00000000\n00000000\n00000000\n00000000\n00000000";
        String actuallyOccupied = BoardStateTest.displayAsFormattedBinary(boardState.getBlackState().getOccupied());
        Assert.assertEquals(occupied, actuallyOccupied) ;
    }
    @Test
    public void initialWhitePositions() throws Exception
    {
        BoardState boardState = BoardState.getStartingState();

        String wbishop = "00000000\n00000000\n00000000\n00000000\n00000000\n00000000\n00000000\n00100100";
        String wking = "00000000\n00000000\n00000000\n00000000\n00000000\n00000000\n00000000\n00001000";
        String wknight = "00000000\n00000000\n00000000\n00000000\n00000000\n00000000\n00000000\n01000010";
        String wpawn = "00000000\n00000000\n00000000\n00000000\n00000000\n00000000\n11111111\n00000000";
        String wqueen = "00000000\n00000000\n00000000\n00000000\n00000000\n00000000\n00000000\n00010000";
        String wrook = "00000000\n00000000\n00000000\n00000000\n00000000\n00000000\n00000000\n10000001";


        String actualwbishop = BoardStateTest.displayAsFormattedBinary(boardState.getWhiteState().getBishop());
        String actualwking = BoardStateTest.displayAsFormattedBinary(boardState.getWhiteState().getKing());
        String actualwknight = BoardStateTest.displayAsFormattedBinary(boardState.getWhiteState().getKnight());
        String actualwpawn = BoardStateTest.displayAsFormattedBinary(boardState.getWhiteState().getPawn());
        String actualwqueen = BoardStateTest.displayAsFormattedBinary(boardState.getWhiteState().getQueen());
        String actualwrook = BoardStateTest.displayAsFormattedBinary(boardState.getWhiteState().getRook());


        Assert.assertEquals(wbishop,actualwbishop);
        Assert.assertEquals(wking,actualwking);
        Assert.assertEquals(wknight,actualwknight);
        Assert.assertEquals(wpawn,actualwpawn);
        Assert.assertEquals(wqueen,actualwqueen);
        Assert.assertEquals(wrook,actualwrook);
    }
    @Test
    public void initialBlackPositions() throws Exception
    {
        BoardState boardState = BoardState.getStartingState();

        String bbishop = "00100100\n00000000\n00000000\n00000000\n00000000\n00000000\n00000000\n00000000";
        String bking = "00001000\n00000000\n00000000\n00000000\n00000000\n00000000\n00000000\n00000000";
        String bknight = "01000010\n00000000\n00000000\n00000000\n00000000\n00000000\n00000000\n00000000";
        String bpawn = "00000000\n11111111\n00000000\n00000000\n00000000\n00000000\n00000000\n00000000";
        String bqueen = "00010000\n00000000\n00000000\n00000000\n00000000\n00000000\n00000000\n00000000";
        String brook = "10000001\n00000000\n00000000\n00000000\n00000000\n00000000\n00000000\n00000000";



        String actualbbishop = BoardStateTest.displayAsFormattedBinary(boardState.getBlackState().getBishop());
        String actualbking = BoardStateTest.displayAsFormattedBinary(boardState.getBlackState().getKing());
        String actualbknight = BoardStateTest.displayAsFormattedBinary(boardState.getBlackState().getKnight());
        String actualbpawn = BoardStateTest.displayAsFormattedBinary(boardState.getBlackState().getPawn());
        String actualbqueen = BoardStateTest.displayAsFormattedBinary(boardState.getBlackState().getQueen());
        String actualbrook = BoardStateTest.displayAsFormattedBinary(boardState.getBlackState().getRook());


        Assert.assertEquals(bbishop,actualbbishop);
        Assert.assertEquals(bking,actualbking);
        Assert.assertEquals(bknight,actualbknight);
        Assert.assertEquals(bpawn,actualbpawn);
        Assert.assertEquals(bqueen,actualbqueen);
        Assert.assertEquals(brook,actualbrook);
    }

}