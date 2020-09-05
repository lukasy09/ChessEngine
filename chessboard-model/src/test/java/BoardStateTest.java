import com.agh.technology.chess.engine.model.element.PieceType;
import com.agh.technology.chess.engine.model.state.BlackState;
import com.agh.technology.chess.engine.model.state.BoardState;
import com.agh.technology.chess.engine.model.state.WhiteState;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;
import java.io.*;

public class BoardStateTest {

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
    @Test
    public void setStatePerFigure() throws Exception
    {
        BoardState boardState = BoardState.getStartingState();

        Long bishop = parseLong("00100100 00000000 000000000000000000000000000000000000000000000000",2);
        Long king = parseLong("00000000 10000000000000000000000000000000000000000000000000000000", 2);
        Long knight = parseLong("00000000 00100000000000000000000000000000000000000000000000000000", 2);
        Long pawn = parseLong("00000000 00000000 100000000000000000000000000000000000000000000000", 2);
        Long queen = parseLong("00000000 00000000 00000000 0010000000000000000000000000000000000000", 2);
        Long rook = parseLong("00000000 00000000 00000000 0010000000000000000000000000000000000000", 2);


        boardState.getBlackState().setBishop(bishop);
        boardState.getBlackState().setKing(king);
        boardState.getBlackState().setKnight(knight);
        boardState.getBlackState().setPawn(pawn);
        boardState.getBlackState().setQueen(queen);
        boardState.getBlackState().setRook(rook);

        Long actualbishop = boardState.getBlackState().getBishop();
        Long actualking = boardState.getBlackState().getKing();
        Long actualknight = boardState.getBlackState().getKnight();
        Long actualpawn = boardState.getBlackState().getPawn();
        Long actualqueen = boardState.getBlackState().getQueen();
        Long actualrook = boardState.getBlackState().getRook();

        Assert.assertEquals(bishop,actualbishop);
        Assert.assertEquals(king,actualking);
        Assert.assertEquals(knight,actualknight);
        Assert.assertEquals(pawn,actualpawn);
        Assert.assertEquals(queen,actualqueen);
        Assert.assertEquals(rook,actualrook);
    }

    @Test
    public void setState() throws Exception
    {
        BoardState boardState = BoardState.getStartingState();

        Long bishop = parseLong("00100100 00000000 000000000000000000000000000000000000000000000000",2);
        Long king = parseLong("00000000 10000000000000000000000000000000000000000000000000000000", 2);
        Long knight = parseLong("00000000 00100000000000000000000000000000000000000000000000000000", 2);
        Long pawn = parseLong("00000000 00000000 100000000000000000000000000000000000000000000000", 2);
        Long queen = parseLong("00000000 00000000 00000000 0010000000000000000000000000000000000000", 2);
        Long rook = parseLong("00000000 00000000 00000000 0010000000000000000000000000000000000000", 2);

        boardState.getBlackState().setBishop(bishop);
        boardState.getBlackState().setKing(king);
        boardState.getBlackState().setKnight(knight);
        boardState.getBlackState().setPawn(pawn);
        boardState.getBlackState().setQueen(queen);
        boardState.getBlackState().setRook(rook);

        Long occupied = king | queen| rook| bishop| knight| pawn;
        Long actuallyoccupied = boardState.getBlackState().getOccupied();


        Assert.assertEquals(occupied,actuallyoccupied);

    }

    @Test
    public void stateToCopy() throws Exception {
        BoardState boardState = BoardState.getStartingState();

        Long bishop = parseLong("00100100 00000000 000000000000000000000000000000000000000000000000",2);
        Long king = parseLong("00000000 10000000000000000000000000000000000000000000000000000000", 2);
        Long knight = parseLong("00000000 00100000000000000000000000000000000000000000000000000000", 2);
        Long pawn = parseLong("00000000 00000000 100000000000000000000000000000000000000000000000", 2);
        Long queen = parseLong("00000000 00000000 00000000 0010000000000000000000000000000000000000", 2);
        Long rook = parseLong("00000000 00000000 00000000 0010000000000000000000000000000000000000", 2);

        boardState.getBlackState().setBishop(bishop);
        boardState.getBlackState().setKing(king);
        boardState.getBlackState().setKnight(knight);
        boardState.getBlackState().setPawn(pawn);
        boardState.getBlackState().setQueen(queen);
        boardState.getBlackState().setRook(rook);

        BoardState actualBoardState = new BoardState(boardState);

        Assert.assertEquals( boardState.getBlackState().getOccupied(),actualBoardState.getBlackState().getOccupied());
    }

    @Test
    public void getOccupied() throws Exception {
        BoardState boardState = BoardState.getStartingState();

        Long bishop = parseLong("00100100 00000000 000000000000000000000000000000000000000000000000",2);
        Long king = parseLong("00000000 10000000000000000000000000000000000000000000000000000000", 2);
        Long knight = parseLong("00000000 00100000000000000000000000000000000000000000000000000000", 2);
        Long pawn = parseLong("00000000 00000000 100000000000000000000000000000000000000000000000", 2);
        Long queen = parseLong("00000000 00000000 00000000 0010000000000000000000000000000000000000", 2);
        Long rook = parseLong("00000000 00000000 00000000 0010000000000000000000000000000000000000", 2);

        boardState.getBlackState().setBishop(bishop);
        boardState.getBlackState().setKing(king);
        boardState.getBlackState().setKnight(knight);
        boardState.getBlackState().setPawn(pawn);
        boardState.getBlackState().setQueen(queen);
        boardState.getBlackState().setRook(rook);



        Assert.assertEquals( 2639250256566484991l,boardState.getOccupied());
    }

    @Test
    public void setBlackStateTest() throws Exception {
        BoardState boardState = BoardState.getStartingState();

        Long bishop = parseLong("00100100 00000000 000000000000000000000000000000000000000000000000",2);
        Long king = parseLong("00000000 10000000000000000000000000000000000000000000000000000000", 2);
        Long knight = parseLong("00000000 00100000000000000000000000000000000000000000000000000000", 2);
        Long pawn = parseLong("00000000 00000000 100000000000000000000000000000000000000000000000", 2);
        Long queen = parseLong("00000000 00000000 00000000 0010000000000000000000000000000000000000", 2);
        Long rook = parseLong("00000000 00000000 00000000 0010000000000000000000000000000000000000", 2);

        boardState.getBlackState().setBishop(bishop);
        boardState.getBlackState().setKing(king);
        boardState.getBlackState().setKnight(knight);
        boardState.getBlackState().setPawn(pawn);
        boardState.getBlackState().setQueen(queen);
        boardState.getBlackState().setRook(rook);

        BoardState actualBoardState = BoardState.getStartingState();

        actualBoardState.setBlackState(boardState.getBlackState());

        Assert.assertEquals( boardState.getBlackState(),actualBoardState.getBlackState());
    }

    @Test
    public void setWhiteStateTest() throws Exception {
        BoardState boardState = BoardState.getStartingState();

        Long bishop = parseLong("00100100 00000000 000000000000000000000000000000000000000000000000",2);
        Long king = parseLong("00000000 10000000000000000000000000000000000000000000000000000000", 2);
        Long knight = parseLong("00000000 00100000000000000000000000000000000000000000000000000000", 2);
        Long pawn = parseLong("00000000 00000000 100000000000000000000000000000000000000000000000", 2);
        Long queen = parseLong("00000000 00000000 00000000 0010000000000000000000000000000000000000", 2);
        Long rook = parseLong("00000000 00000000 00000000 0010000000000000000000000000000000000000", 2);

        boardState.getWhiteState().setBishop(bishop);
        boardState.getWhiteState().setKing(king);
        boardState.getWhiteState().setKnight(knight);
        boardState.getWhiteState().setPawn(pawn);
        boardState.getWhiteState().setQueen(queen);
        boardState.getWhiteState().setRook(rook);

        BoardState actualBoardState = BoardState.getStartingState();

        actualBoardState.setWhiteState(boardState.getWhiteState());

        Assert.assertEquals( boardState.getWhiteState(),actualBoardState.getWhiteState());
    }

    @Test
    public void getPawnPromotionTest() throws Exception {
        Character pawnPromotion = 'c';
        BoardState actualBoardState = BoardState.getStartingState();
        actualBoardState.setPawnPromotion('c');


        Assert.assertEquals(pawnPromotion,actualBoardState.getPawnPromotion());
    }
    @Test
    public void getMoveTest() throws Exception {
        int[] move = new int[3];
        BoardState actualBoardState = BoardState.getStartingState();
        actualBoardState.setMove(move);


        Assert.assertEquals(move,actualBoardState.getMove());
    }

    @Test
    public void hashCodeTest() throws Exception {

        BoardState actualBoardState = BoardState.getStartingState();

        Assert.assertEquals(-22879270l,actualBoardState.hashCode());
    }
    @Test
    public void setPieceBitboardWhiteTest() throws Exception {

        BoardState actualBoardState = BoardState.getStartingState();
        Long queen = parseLong("00000000 00000000 00000000 0010000000000000000000000000000000000000", 2);

        actualBoardState.getWhiteState().setPieceBitboard(PieceType.QUEEN,queen);
        Long actualqueen = actualBoardState.getWhiteState().getPieceBitboard(PieceType.QUEEN);
        Assert.assertEquals(queen,actualqueen);
    }

    @Test
    public void setPieceBitboardBlackTest() throws Exception {

        BoardState actualBoardState = BoardState.getStartingState();
        Long queen = parseLong("00000000 00000000 00000000 0010000000000000000000000000000000000000", 2);

        actualBoardState.getBlackState().setPieceBitboard(PieceType.QUEEN,queen);
        Long actualqueen = actualBoardState.getBlackState().getPieceBitboard(PieceType.QUEEN);
        Assert.assertEquals(queen,actualqueen);
    }

}