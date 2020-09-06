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
    @Test
    public void getWhitePawnMoves() throws Exception
    {
        AttackMasks attackMasks = AttackMasks.getInstance();
        Map<Integer, Long> ExpectePawnMoves = new HashMap<Integer, Long>(){
            {
                put(0,256l);
                put(1,512l);
                put(2,1024l);
                put(3,2048l);
                put(4,4096l);
                put(5,8192l);
                put(6,16384l);
                put(7,32768l);
                put(8,16842752l);
                put(9,33685504l);
                put(10,67371008l);
                put(11,134742016l);
                put(12,269484032l);
                put(13,538968064l);
                put(14,1077936128l);
                put(15,2155872256l);
                put(16,16777216l);
                put(17,33554432l);
                put(18,67108864l);
                put(19,134217728l);
                put(20,268435456l);
                put(21,536870912l);
                put(22,1073741824l);
                put(23,2147483648l);
                put(24,4294967296l);
                put(25,8589934592l);
                put(26,17179869184l);
                put(27,34359738368l);
                put(28,68719476736l);
                put(29,137438953472l);
                put(30,274877906944l);
                put(31,549755813888l);
                put(32,1099511627776l);
                put(33,2199023255552l);
                put(34,4398046511104l);
                put(35,8796093022208l);
                put(36,17592186044416l);
                put(37,35184372088832l);
                put(38,70368744177664l);
                put(39,140737488355328l);
                put(40,281474976710656l);
                put(41,562949953421312l);
                put(42,1125899906842624l);
                put(43,2251799813685248l);
                put(44,4503599627370496l);
                put(45,9007199254740992l);
                put(46,18014398509481984l);
                put(47,36028797018963968l);
                put(48,72057594037927936l);
                put(49,144115188075855872l);
                put(50,288230376151711744l);
                put(51,576460752303423488l);
                put(52,1152921504606846976l);
                put(53,2305843009213693952l);
                put(54,4611686018427387904l);
                put(55,-9223372036854775808l);
                put(56,0l);
                put(57,0l);
                put(58,0l);
                put(59,0l);
                put(60,0l);
                put(61,0l);
                put(62,0l);
                put(63,0l);}

        };


        Assert.assertEquals(ExpectePawnMoves, attackMasks.getPawnMoves().get(Color.WHITE)) ;
    }
    @Test
    public void getBLACKPawnMoves() throws Exception
    {
        AttackMasks attackMasks = AttackMasks.getInstance();
        Map<Integer, Long> ExpectePawnMoves = new HashMap<Integer, Long>(){
            {
                put(0,0l);
                put(1,0l);
                put(2,0l);
                put(3,0l);
                put(4,0l);
                put(5,0l);
                put(6,0l);
                put(7,0l);
                put(8,1l);
                put(9,2l);
                put(10,4l);
                put(11,8l);
                put(12,16l);
                put(13,32l);
                put(14,64l);
                put(15,128l);
                put(16,256l);
                put(17,512l);
                put(18,1024l);
                put(19,2048l);
                put(20,4096l);
                put(21,8192l);
                put(22,16384l);
                put(23,32768l);
                put(24,65536l);
                put(25,131072l);
                put(26,262144l);
                put(27,524288l);
                put(28,1048576l);
                put(29,2097152l);
                put(30,4194304l);
                put(31,8388608l);
                put(32,16777216l);
                put(33,33554432l);
                put(34,67108864l);
                put(35,134217728l);
                put(36,268435456l);
                put(37,536870912l);
                put(38,1073741824l);
                put(39,2147483648l);
                put(40,4294967296l);
                put(41,8589934592l);
                put(42,17179869184l);
                put(43,34359738368l);
                put(44,68719476736l);
                put(45,137438953472l);
                put(46,274877906944l);
                put(47,549755813888l);
                put(48,1103806595072l);
                put(49,2207613190144l);
                put(50,4415226380288l);
                put(51,8830452760576l);
                put(52,17660905521152l);
                put(53,35321811042304l);
                put(54,70643622084608l);
                put(55,141287244169216l);
                put(56,281474976710656l);
                put(57,562949953421312l);
                put(58,1125899906842624l);
                put(59,2251799813685248l);
                put(60,4503599627370496l);
                put(61,9007199254740992l);
                put(62,18014398509481984l);
                put(63,36028797018963968l);
            }
        };


        Assert.assertEquals(ExpectePawnMoves, attackMasks.getPawnMoves().get(Color.BLACK)) ;
    }
    @Test
    public void getPawnCaptureMovesBlackTest() throws Exception
    {
        AttackMasks attackMasks = AttackMasks.getInstance();
        Map<Integer, Long> ExpectedPawnCaptureMoves = new HashMap<Integer, Long>(){
            {
                put(0,0l);
                put(1,0l);
                put(2,0l);
                put(3,0l);
                put(4,0l);
                put(5,0l);
                put(6,0l);
                put(7,0l);
                put(8,2l);
                put(9,5l);
                put(10,10l);
                put(11,20l);
                put(12,40l);
                put(13,80l);
                put(14,160l);
                put(15,64l);
                put(16,512l);
                put(17,1280l);
                put(18,2560l);
                put(19,5120l);
                put(20,10240l);
                put(21,20480l);
                put(22,40960l);
                put(23,16384l);
                put(24,131072l);
                put(25,327680l);
                put(26,655360l);
                put(27,1310720l);
                put(28,2621440l);
                put(29,5242880l);
                put(30,10485760l);
                put(31,4194304l);
                put(32,33554432l);
                put(33,83886080l);
                put(34,167772160l);
                put(35,335544320l);
                put(36,671088640l);
                put(37,1342177280l);
                put(38,2684354560l);
                put(39,1073741824l);
                put(40,8589934592l);
                put(41,21474836480l);
                put(42,42949672960l);
                put(43,85899345920l);
                put(44,171798691840l);
                put(45,343597383680l);
                put(46,687194767360l);
                put(47,274877906944l);
                put(48,2199023255552l);
                put(49,5497558138880l);
                put(50,10995116277760l);
                put(51,21990232555520l);
                put(52,43980465111040l);
                put(53,87960930222080l);
                put(54,175921860444160l);
                put(55,70368744177664l);
                put(56,562949953421312l);
                put(57,1407374883553280l);
                put(58,2814749767106560l);
                put(59,5629499534213120l);
                put(60,11258999068426240l);
                put(61,22517998136852480l);
                put(62,45035996273704960l);
                put(63,18014398509481984l);
            }
        };


        Assert.assertEquals(ExpectedPawnCaptureMoves, attackMasks.getPawnCaptureMoves().get(Color.BLACK)) ;
    }
    @Test
    public void getPawnCaptureMovesWhiteTest() throws Exception
    {
        AttackMasks attackMasks = AttackMasks.getInstance();
        Map<Integer, Long> ExpectedPawnCaptureMoves = new HashMap<Integer, Long>(){
            {
                put(0,512l);
                put(1,1280l);
                put(2,2560l);
                put(3,5120l);
                put(4,10240l);
                put(5,20480l);
                put(6,40960l);
                put(7,16384l);
                put(8,131072l);
                put(9,327680l);
                put(10,655360l);
                put(11,1310720l);
                put(12,2621440l);
                put(13,5242880l);
                put(14,10485760l);
                put(15,4194304l);
                put(16,33554432l);
                put(17,83886080l);
                put(18,167772160l);
                put(19,335544320l);
                put(20,671088640l);
                put(21,1342177280l);
                put(22,2684354560l);
                put(23,1073741824l);
                put(24,8589934592l);
                put(25,21474836480l);
                put(26,42949672960l);
                put(27,85899345920l);
                put(28,171798691840l);
                put(29,343597383680l);
                put(30,687194767360l);
                put(31,274877906944l);
                put(32,2199023255552l);
                put(33,5497558138880l);
                put(34,10995116277760l);
                put(35,21990232555520l);
                put(36,43980465111040l);
                put(37,87960930222080l);
                put(38,175921860444160l);
                put(39,70368744177664l);
                put(40,562949953421312l);
                put(41,1407374883553280l);
                put(42,2814749767106560l);
                put(43,5629499534213120l);
                put(44,11258999068426240l);
                put(45,22517998136852480l);
                put(46,45035996273704960l);
                put(47,18014398509481984l);
                put(48,144115188075855872l);
                put(49,360287970189639680l);
                put(50,720575940379279360l);
                put(51,1441151880758558720l);
                put(52,2882303761517117440l);
                put(53,5764607523034234880l);
                put(54,-6917529027641081856l);
                put(55,4611686018427387904l);
                put(56,0l);
                put(57,0l);
                put(58,0l);
                put(59,0l);
                put(60,0l);
                put(61,0l);
                put(62,0l);
                put(63,0l);}

        };

        Assert.assertEquals(ExpectedPawnCaptureMoves, attackMasks.getPawnCaptureMoves().get(Color.WHITE)) ;
    }
    @Test
    public void getBlockersAndBeyondBlackTest() throws Exception
    {
        AttackMasks attackMasks = AttackMasks.getInstance();


        Assert.assertEquals(null, attackMasks.getBlockersAndBeyond().get(Color.BLACK)) ;


    }
    @Test
    public void getBlockersAndBeyondWhiteTest() throws Exception
    {
        AttackMasks attackMasks = AttackMasks.getInstance();


        Assert.assertEquals(null, attackMasks.getBlockersAndBeyond().get(Color.WHITE)) ;


    }
    @Test
    public void getAttackMovesKingTest() throws Exception
    {
        AttackMasks attackMasks = AttackMasks.getInstance();
        Map<Integer, Long> ExpectedAttackMovesKing = new HashMap<Integer, Long>(){
            {
                put(0,770l);
                put(1,1797l);
                put(2,3594l);
                put(3,7188l);
                put(4,14376l);
                put(5,28752l);
                put(6,57504l);
                put(7,49216l);
                put(8,197123l);
                put(9,460039l);
                put(10,920078l);
                put(11,1840156l);
                put(12,3680312l);
                put(13,7360624l);
                put(14,14721248l);
                put(15,12599488l);
                put(16,50463488l);
                put(17,117769984l);
                put(18,235539968l);
                put(19,471079936l);
                put(20,942159872l);
                put(21,1884319744l);
                put(22,3768639488l);
                put(23,3225468928l);
                put(24,12918652928l);
                put(25,30149115904l);
                put(26,60298231808l);
                put(27,120596463616l);
                put(28,241192927232l);
                put(29,482385854464l);
                put(30,964771708928l);
                put(31,825720045568l);
                put(32,3307175149568l);
                put(33,7718173671424l);
                put(34,15436347342848l);
                put(35,30872694685696l);
                put(36,61745389371392l);
                put(37,123490778742784l);
                put(38,246981557485568l);
                put(39,211384331665408l);
                put(40,846636838289408l);
                put(41,1975852459884544l);
                put(42,3951704919769088l);
                put(43,7903409839538176l);
                put(44,15806819679076352l);
                put(45,31613639358152704l);
                put(46,63227278716305408l);
                put(47,54114388906344448l);
                put(48,216739030602088448l);
                put(49,505818229730443264l);
                put(50,1011636459460886528l);
                put(51,2023272918921773056l);
                put(52,4046545837843546112l);
                put(53,8093091675687092224l);
                put(54,-2260560722335367168l);
                put(55,-4593460513685372928l);
                put(56,144959613005987840l);
                put(57,362258295026614272l);
                put(58,724516590053228544l);
                put(59,1449033180106457088l);
                put(60,2898066360212914176l);
                put(61,5796132720425828352l);
                put(62,-6854478632857894912l);
                put(63,4665729213955833856l);}

        };

        Assert.assertEquals( ExpectedAttackMovesKing, attackMasks.getAttackMoves().get(PieceType.KING)) ;
    }
    @Test
    public void getAttackMovesQueenTest() throws Exception
    {
        AttackMasks attackMasks = AttackMasks.getInstance();
        Map<Integer, Long> ExpectedAttackMoves = new HashMap<Integer, Long>(){
            {

                put(0,-9132982212281170946l);
                put(1,180779649147209725l);
                put(2,289501704256556795l);
                put(3,578721933553179895l);
                put(4,1157442771889699055l);
                put(5,2314886638996058335l);
                put(6,4630054752952049855l);
                put(7,-9114576973767589761l);
                put(8,4693051017133293059l);
                put(9,-9060642039442965241l);
                put(10,325459994840333070l);
                put(11,578862399937640220l);
                put(12,1157444424410132280l);
                put(13,2315169224285282160l);
                put(14,4702396038313459680l);
                put(15,-9041951997099475008l);
                put(16,2382695595002168069l);
                put(17,4765391190004401930l);
                put(18,-8915961689422492139l);
                put(19,614821794359483434l);
                put(20,1157867469641037908l);
                put(21,2387511058326581416l);
                put(22,4775021017124823120l);
                put(23,-8896702043771649888l);
                put(24,1227517888139822345l);
                put(25,2455035776296487442l);
                put(26,4910072647826412836l);
                put(27,-8626317307358205367l);
                put(28,1266167048752878738l);
                put(29,2460276499189639204l);
                put(30,4920271519124312136l);
                put(31,-8606202139267522416l);
                put(32,649930110732142865l);
                put(33,1299860225776030242l);
                put(34,2600000831312176196l);
                put(35,5272058161445620104l);
                put(36,-7902628846034972143l);
                put(37,2641485286422881314l);
                put(38,5210911883574396996l);
                put(39,-8025202881049096056l);
                put(40,361411684042608929l);
                put(41,722824471891812930l);
                put(42,1517426162373248132l);
                put(43,3034571949281478664l);
                put(44,6068863523097809168l);
                put(45,-6309297402995793375l);
                put(46,5827868887957914690l);
                put(47,-6863345366808360828l);
                put(48,287670746360127809l);
                put(49,575624067208594050l);
                put(50,1079472019650937860l);
                put(51,2087167920257370120l);
                put(52,4102559721436811280l);
                put(53,8133343319517438240l);
                put(54,-2251834653247520191l);
                put(55,-4575726900532968318l);
                put(56,-143265226645487231l);
                put(57,-214191384276336126l);
                put(58,-356324075003182076l);
                put(59,-640590551690246136l);
                put(60,-1209123513620754416l);
                put(61,-2346190532715143136l);
                put(62,-4620604946369068736l);
                put(63,9205534180971414145l);}

        };

        Assert.assertEquals( ExpectedAttackMoves, attackMasks.getAttackMoves().get(PieceType.QUEEN)) ;
    }
    @Test
    public void getAttackMovesRookTest() throws Exception
    {
        AttackMasks attackMasks = AttackMasks.getInstance();
        Map<Integer, Long> ExpectedAttackMoves = new HashMap<Integer, Long>(){
            {

                put(0,72340172838076926l);
                put(1,144680345676153597l);
                put(2,289360691352306939l);
                put(3,578721382704613623l);
                put(4,1157442765409226991l);
                put(5,2314885530818453727l);
                put(6,4629771061636907199l);
                put(7,-9187201950435737473l);
                put(8,72340172838141441l);
                put(9,144680345676217602l);
                put(10,289360691352369924l);
                put(11,578721382704674568l);
                put(12,1157442765409283856l);
                put(13,2314885530818502432l);
                put(14,4629771061636939584l);
                put(15,-9187201950435737728l);
                put(16,72340172854657281l);
                put(17,144680345692602882l);
                put(18,289360691368494084l);
                put(19,578721382720276488l);
                put(20,1157442765423841296l);
                put(21,2314885530830970912l);
                put(22,4629771061645230144l);
                put(23,-9187201950435803008l);
                put(24,72340177082712321l);
                put(25,144680349887234562l);
                put(26,289360695496279044l);
                put(27,578721386714368008l);
                put(28,1157442769150545936l);
                put(29,2314885534022901792l);
                put(30,4629771063767613504l);
                put(31,-9187201950452514688l);
                put(32,72341259464802561l);
                put(33,144681423712944642l);
                put(34,289361752209228804l);
                put(35,578722409201797128l);
                put(36,1157443723186933776l);
                put(37,2314886351157207072l);
                put(38,4629771607097753664l);
                put(39,-9187201954730704768l);
                put(40,72618349279904001l);
                put(41,144956323094725122l);
                put(42,289632270724367364l);
                put(43,578984165983651848l);
                put(44,1157687956502220816l);
                put(45,2315095537539358752l);
                put(46,4629910699613634624l);
                put(47,-9187203049947365248l);
                put(48,143553341945872641l);
                put(49,215330564830528002l);
                put(50,358885010599838724l);
                put(51,645993902138460168l);
                put(52,1220211685215703056l);
                put(53,2368647251370188832l);
                put(54,4665518383679160384l);
                put(55,-9187483425412448128l);
                put(56,-143832609275707135l);
                put(57,-215607624513486334l);
                put(58,-359157654989044732l);
                put(59,-646257715940161528l);
                put(60,-1220457837842395120l);
                put(61,-2368858081646862304l);
                put(62,-4665658569255796672l);
                put(63,9187484529235886208l);}

        };

        Assert.assertEquals( ExpectedAttackMoves, attackMasks.getAttackMoves().get(PieceType.ROOK)) ;
    }
    @Test
    public void getAttackMovesBishopTest() throws Exception
    {
        AttackMasks attackMasks = AttackMasks.getInstance();
        Map<Integer, Long> ExpectedAttackMoves = new HashMap<Integer, Long>(){
            {

                put(0,-9205322385119247872l);
                put(1,36099303471056128l);
                put(2,141012904249856l);
                put(3,550848566272l);
                put(4,6480472064l);
                put(5,1108177604608l);
                put(6,283691315142656l);
                put(7,72624976668147712l);
                put(8,4620710844295151618l);
                put(9,-9205322385119182843l);
                put(10,36099303487963146l);
                put(11,141017232965652l);
                put(12,1659000848424l);
                put(13,283693466779728l);
                put(14,72624976676520096l);
                put(15,145249953336262720l);
                put(16,2310355422147510788l);
                put(17,4620710844311799048l);
                put(18,-9205322380790986223l);
                put(19,36100411639206946l);
                put(20,424704217196612l);
                put(21,72625527495610504l);
                put(22,145249955479592976l);
                put(23,290499906664153120l);
                put(24,1155177711057110024l);
                put(25,2310355426409252880l);
                put(26,4620711952330133792l);
                put(27,-9205038694072573375l);
                put(28,108724279602332802l);
                put(29,145390965166737412l);
                put(30,290500455356698632l);
                put(31,580999811184992272l);
                put(32,577588851267340304l);
                put(33,1155178802063085600l);
                put(34,2310639079102947392l);
                put(35,4693335752243822976l);
                put(36,-9060072569221905919l);
                put(37,326598935265674242l);
                put(38,581140276476643332l);
                put(39,1161999073681608712l);
                put(40,288793334762704928l);
                put(41,577868148797087808l);
                put(42,1227793891648880768l);
                put(43,2455587783297826816l);
                put(44,4911175566595588352l);
                put(45,-8624392940535152127l);
                put(46,1197958188344280066l);
                put(47,2323857683139004420l);
                put(48,144117404414255168l);
                put(49,360293502378066048l);
                put(50,720587009051099136l);
                put(51,1441174018118909952l);
                put(52,2882348036221108224l);
                put(53,5764696068147249408l);
                put(54,-6917353036926680575l);
                put(55,4611756524879479810l);
                put(56,567382630219904l);
                put(57,1416240237150208l);
                put(58,2833579985862656l);
                put(59,5667164249915392l);
                put(60,11334324221640704l);
                put(61,22667548931719168l);
                put(62,45053622886727936l);
                put(63,18049651735527937l);}

        };

        Assert.assertEquals( ExpectedAttackMoves, attackMasks.getAttackMoves().get(PieceType.BISHOP)) ;
    }
    @Test
    public void getAttackMovesKnightTest() throws Exception
    {
        AttackMasks attackMasks = AttackMasks.getInstance();
        Map<Integer, Long> ExpectedAttackMoves = new HashMap<Integer, Long>(){
            {

                put(0,132096l);
                put(1,329728l);
                put(2,659712l);
                put(3,1319424l);
                put(4,2638848l);
                put(5,5277696l);
                put(6,10489856l);
                put(7,4202496l);
                put(8,33816580l);
                put(9,84410376l);
                put(10,168886289l);
                put(11,337772578l);
                put(12,675545156l);
                put(13,1351090312l);
                put(14,2685403152l);
                put(15,1075839008l);
                put(16,8657044482l);
                put(17,21609056261l);
                put(18,43234889994l);
                put(19,86469779988l);
                put(20,172939559976l);
                put(21,345879119952l);
                put(22,687463207072l);
                put(23,275414786112l);
                put(24,2216203387392l);
                put(25,5531918402816l);
                put(26,11068131838464l);
                put(27,22136263676928l);
                put(28,44272527353856l);
                put(29,88545054707712l);
                put(30,175990581010432l);
                put(31,70506185244672l);
                put(32,567348067172352l);
                put(33,1416171111120896l);
                put(34,2833441750646784l);
                put(35,5666883501293568l);
                put(36,11333767002587136l);
                put(37,22667534005174272l);
                put(38,45053588738670592l);
                put(39,18049583422636032l);
                put(40,145241105196122112l);
                put(41,362539804446949376l);
                put(42,725361088165576704l);
                put(43,1450722176331153408l);
                put(44,2901444352662306816l);
                put(45,5802888705324613632l);
                put(46,-6913025356609880064l);
                put(47,4620693356194824192l);
                put(48,288234782788157440l);
                put(49,576469569871282176l);
                put(50,1224997833292120064l);
                put(51,2449995666584240128l);
                put(52,4899991333168480256l);
                put(53,-8646761407372591104l);
                put(54,1152939783987658752l);
                put(55,2305878468463689728l);
                put(56,1128098930098176l);
                put(57,2257297371824128l);
                put(58,4796069720358912l);
                put(59,9592139440717824l);
                put(60,19184278881435648l);
                put(61,38368557762871296l);
                put(62,4679521487814656l);
                put(63,9077567998918656l);}

        };

        Assert.assertEquals( ExpectedAttackMoves, attackMasks.getAttackMoves().get(PieceType.KNIGHT)) ;
    }
    @Test
    public void getAttackMovesPawnTest() throws Exception
    {
        AttackMasks attackMasks = AttackMasks.getInstance();
        Map<Integer, Long> ExpectedAttackMoves = null;

        Assert.assertEquals( ExpectedAttackMoves, attackMasks.getAttackMoves().get(PieceType.PAWN)) ;
    }
    @Test
    public void getBehindTest() throws Exception
    {
        AttackMasks attackMasks = AttackMasks.getInstance();
        Long[][] expectedAttackMasks = {
        {0l, 252l, 248l, 240l, 224l, 192l, 128l, 0l, 72340172838076416l, -9205322385119248384l, 0l, 0l, 0l, 0l, 0l, 0l, 72340172838010880l, 0l, -9205322385119510528l, 0l, 0l, 0l, 0l, 0l, 72340172821233664l, 0l, 0l, -9205322385253728256l, 0l, 0l, 0l, 0l, 72340168526266368l, 0l, 0l, 0l, -9205322453973204992l, 0l, 0l, 0l, 72339069014638592l, 0l, 0l, 0l, 0l, -9205357638345293824l, 0l, 0l, 72057594037927936l, 0l, 0l, 0l, 0l, 0l, -9223372036854775808l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l},
        {0l, 0l, 248l, 240l, 224l, 192l, 128l, 0l, 0l, 144680345676152832l, 36099303471054848l, 0l, 0l, 0l, 0l, 0l, 0l, 144680345676021760l, 0l, 36099303470530560l, 0l, 0l, 0l, 0l, 0l, 144680345642467328l, 0l, 0l, 36099303202095104l, 0l, 0l, 0l, 0l, 144680337052532736l, 0l, 0l, 0l, 36099165763141632l, 0l, 0l, 0l, 144678138029277184l, 0l, 0l, 0l, 0l, 36028797018963968l, 0l, 0l, 144115188075855872l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l},
        {0l, 1l, 0l, 240l, 224l, 192l, 128l, 0l, 0l, 65536l, 289360691352305664l, 141012904181760l, 0l, 0l, 0l, 0l, 0l, 0l, 289360691352043520l, 0l, 141012903133184l, 0l, 0l, 0l, 0l, 0l, 289360691284934656l, 0l, 0l, 141012366262272l, 0l, 0l, 0l, 0l, 289360674105065472l, 0l, 0l, 0l, 140737488355328l, 0l, 0l, 0l, 289356276058554368l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 288230376151711744l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l},
        {0l, 1l, 3l, 0l, 224l, 192l, 128l, 0l, 0l, 0l, 16908288l, 578721382704611328l, 550831652864l, 0l, 0l, 0l, 0l, 16777216l, 0l, 578721382704087040l, 0l, 550829555712l, 0l, 0l, 0l, 0l, 0l, 578721382569869312l, 0l, 0l, 549755813888l, 0l, 0l, 0l, 0l, 578721348210130944l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 578712552117108736l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 576460752303423488l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l},
        {0l, 1l, 3l, 7l, 0l, 192l, 128l, 0l, 0l, 0l, 0l, 4328783872l, 1157442765409222656l, 2151677952l, 0l, 0l, 0l, 0l, 4328521728l, 0l, 1157442765408174080l, 0l, 2147483648l, 0l, 0l, 4294967296l, 0l, 0l, 1157442765139738624l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 1157442696420261888l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 1157425104234217472l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 1152921504606846976l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l},
        {0l, 1l, 3l, 7l, 15l, 0l, 128l, 0l, 0l, 0l, 0l, 0l, 1108169195520l, 2314885530818445312l, 8388608l, 0l, 0l, 0l, 0l, 1108168671232l, 0l, 2314885530816348160l, 0l, 0l, 0l, 0l, 1108101562368l, 0l, 0l, 2314885530279477248l, 0l, 0l, 0l, 1099511627776l, 0l, 0l, 0l, 2314885392840523776l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 2314850208468434944l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 2305843009213693952l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l},
        {0l, 1l, 3l, 7l, 15l, 31l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 283691315101696l, 4629771061636890624l, 0l, 0l, 0l, 0l, 0l, 283691314053120l, 0l, 4629771061632696320l, 0l, 0l, 0l, 0l, 283691179835392l, 0l, 0l, 4629771060558954496l, 0l, 0l, 0l, 283673999966208l, 0l, 0l, 0l, 4629770785681047552l, 0l, 0l, 281474976710656l, 0l, 0l, 0l, 0l, 4629700416936869888l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 4611686018427387904l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l},
        {0l, 1l, 3l, 7l, 15l, 31l, 63l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 72624976668131328l, -9187201950435770368l, 0l, 0l, 0l, 0l, 0l, 72624976666034176l, 0l, -9187201950444158976l, 0l, 0l, 0l, 0l, 72624976397598720l, 0l, 0l, -9187201952591642624l, 0l, 0l, 0l, 72624942037860352l, 0l, 0l, 0l, -9187202502347456512l, 0l, 0l, 72620543991349248l, 0l, 0l, 0l, 0l, -9187343239835811840l, 0l, 72057594037927936l, 0l, 0l, 0l, 0l, 0l, -9223372036854775808l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l},
        {0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 64512l, 63488l, 61440l, 57344l, 49152l, 32768l, 0l, 72340172838010880l, 4620710844295020544l, 0l, 0l, 0l, 0l, 0l, 0l, 72340172821233664l, 0l, 4620710844227911680l, 0l, 0l, 0l, 0l, 0l, 72340168526266368l, 0l, 0l, 4620710809868173312l, 0l, 0l, 0l, 0l, 72339069014638592l, 0l, 0l, 0l, 4620693217682128896l, 0l, 0l, 0l, 72057594037927936l, 0l, 0l, 0l, 0l, 4611686018427387904l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l},
        {0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 63488l, 61440l, 57344l, 49152l, 32768l, 0l, 0l, 144680345676021760l, -9205322385119510528l, 0l, 0l, 0l, 0l, 0l, 0l, 144680345642467328l, 0l, -9205322385253728256l, 0l, 0l, 0l, 0l, 0l, 144680337052532736l, 0l, 0l, -9205322453973204992l, 0l, 0l, 0l, 0l, 144678138029277184l, 0l, 0l, 0l, -9205357638345293824l, 0l, 0l, 0l, 144115188075855872l, 0l, 0l, 0l, 0l, -9223372036854775808l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l},
        {0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 256l, 0l, 61440l, 57344l, 49152l, 32768l, 0l, 0l, 16777216l, 289360691352043520l, 36099303470530560l, 0l, 0l, 0l, 0l, 0l, 0l, 289360691284934656l, 0l, 36099303202095104l, 0l, 0l, 0l, 0l, 0l, 289360674105065472l, 0l, 0l, 36099165763141632l, 0l, 0l, 0l, 0l, 289356276058554368l, 0l, 0l, 0l, 36028797018963968l, 0l, 0l, 0l, 288230376151711744l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l},
        {0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 256l, 768l, 0l, 57344l, 49152l, 32768l, 0l, 0l, 0l, 4328521728l, 578721382704087040l, 141012903133184l, 0l, 0l, 0l, 0l, 4294967296l, 0l, 578721382569869312l, 0l, 141012366262272l, 0l, 0l, 0l, 0l, 0l, 578721348210130944l, 0l, 0l, 140737488355328l, 0l, 0l, 0l, 0l, 578712552117108736l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 576460752303423488l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l},
        {0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 256l, 768l, 1792l, 0l, 49152l, 32768l, 0l, 0l, 0l, 0l, 1108168671232l, 1157442765408174080l, 550829555712l, 0l, 0l, 0l, 0l, 1108101562368l, 0l, 1157442765139738624l, 0l, 549755813888l, 0l, 0l, 1099511627776l, 0l, 0l, 1157442696420261888l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 1157425104234217472l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 1152921504606846976l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l},
        {0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 256l, 768l, 1792l, 3840l, 0l, 32768l, 0l, 0l, 0l, 0l, 0l, 283691314053120l, 2314885530816348160l, 2147483648l, 0l, 0l, 0l, 0l, 283691179835392l, 0l, 2314885530279477248l, 0l, 0l, 0l, 0l, 283673999966208l, 0l, 0l, 2314885392840523776l, 0l, 0l, 0l, 281474976710656l, 0l, 0l, 0l, 2314850208468434944l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 2305843009213693952l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l},
        {0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 256l, 768l, 1792l, 3840l, 7936l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 72624976666034176l, 4629771061632696320l, 0l, 0l, 0l, 0l, 0l, 72624976397598720l, 0l, 4629771060558954496l, 0l, 0l, 0l, 0l, 72624942037860352l, 0l, 0l, 4629770785681047552l, 0l, 0l, 0l, 72620543991349248l, 0l, 0l, 0l, 4629700416936869888l, 0l, 0l, 72057594037927936l, 0l, 0l, 0l, 0l, 4611686018427387904l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l},
        {0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 256l, 768l, 1792l, 3840l, 7936l, 16128l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 145249953332068352l, -9187201950444158976l, 0l, 0l, 0l, 0l, 0l, 145249952795197440l, 0l, -9187201952591642624l, 0l, 0l, 0l, 0l, 145249884075720704l, 0l, 0l, -9187202502347456512l, 0l, 0l, 0l, 145241087982698496l, 0l, 0l, 0l, -9187343239835811840l, 0l, 0l, 144115188075855872l, 0l, 0l, 0l, 0l, -9223372036854775808l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l},
        {0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 1l, 4l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 16515072l, 16252928l, 15728640l, 14680064l, 12582912l, 8388608l, 0l, 72340172821233664l, 2310355422113955840l, 0l, 0l, 0l, 0l, 0l, 0l, 72340168526266368l, 0l, 2310355404934086656l, 0l, 0l, 0l, 0l, 0l, 72339069014638592l, 0l, 0l, 2310346608841064448l, 0l, 0l, 0l, 0l, 72057594037927936l, 0l, 0l, 0l, 2305843009213693952l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l},
        {0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 2l, 8l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 16252928l, 15728640l, 14680064l, 12582912l, 8388608l, 0l, 0l, 144680345642467328l, 4620710844227911680l, 0l, 0l, 0l, 0l, 0l, 0l, 144680337052532736l, 0l, 4620710809868173312l, 0l, 0l, 0l, 0l, 0l, 144678138029277184l, 0l, 0l, 4620693217682128896l, 0l, 0l, 0l, 0l, 144115188075855872l, 0l, 0l, 0l, 4611686018427387904l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l},
        {0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 1l, 4l, 16l, 0l, 0l, 0l, 0l, 0l, 65536l, 0l, 15728640l, 14680064l, 12582912l, 8388608l, 0l, 0l, 4294967296l, 289360691284934656l, -9205322385253728256l, 0l, 0l, 0l, 0l, 0l, 0l, 289360674105065472l, 0l, -9205322453973204992l, 0l, 0l, 0l, 0l, 0l, 289356276058554368l, 0l, 0l, -9205357638345293824l, 0l, 0l, 0l, 0l, 288230376151711744l, 0l, 0l, 0l, -9223372036854775808l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l},
        {0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 2l, 8l, 32l, 0l, 0l, 0l, 0l, 65536l, 196608l, 0l, 14680064l, 12582912l, 8388608l, 0l, 0l, 0l, 1108101562368l, 578721382569869312l, 36099303202095104l, 0l, 0l, 0l, 0l, 1099511627776l, 0l, 578721348210130944l, 0l, 36099165763141632l, 0l, 0l, 0l, 0l, 0l, 578712552117108736l, 0l, 0l, 36028797018963968l, 0l, 0l, 0l, 0l, 576460752303423488l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l},
        {0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 4l, 16l, 64l, 0l, 0l, 0l, 65536l, 196608l, 458752l, 0l, 12582912l, 8388608l, 0l, 0l, 0l, 0l, 283691179835392l, 1157442765139738624l, 141012366262272l, 0l, 0l, 0l, 0l, 283673999966208l, 0l, 1157442696420261888l, 0l, 140737488355328l, 0l, 0l, 281474976710656l, 0l, 0l, 1157425104234217472l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 1152921504606846976l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l},
        {0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 8l, 32l, 128l, 0l, 0l, 65536l, 196608l, 458752l, 983040l, 0l, 8388608l, 0l, 0l, 0l, 0l, 0l, 72624976397598720l, 2314885530279477248l, 549755813888l, 0l, 0l, 0l, 0l, 72624942037860352l, 0l, 2314885392840523776l, 0l, 0l, 0l, 0l, 72620543991349248l, 0l, 0l, 2314850208468434944l, 0l, 0l, 0l, 72057594037927936l, 0l, 0l, 0l, 2305843009213693952l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l},
        {0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 16l, 64l, 0l, 0l, 65536l, 196608l, 458752l, 983040l, 2031616l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 145249952795197440l, 4629771060558954496l, 0l, 0l, 0l, 0l, 0l, 145249884075720704l, 0l, 4629770785681047552l, 0l, 0l, 0l, 0l, 145241087982698496l, 0l, 0l, 4629700416936869888l, 0l, 0l, 0l, 144115188075855872l, 0l, 0l, 0l, 4611686018427387904l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l},
        {0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 32l, 128l, 0l, 65536l, 196608l, 458752l, 983040l, 2031616l, 4128768l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 290499905590394880l, -9187201952591642624l, 0l, 0l, 0l, 0l, 0l, 290499768151441408l, 0l, -9187202502347456512l, 0l, 0l, 0l, 0l, 290482175965396992l, 0l, 0l, -9187343239835811840l, 0l, 0l, 0l, 288230376151711744l, 0l, 0l, 0l, -9223372036854775808l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l},
        {0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 1l, 0l, 8l, 0l, 0l, 0l, 0l, 0l, 257l, 1032l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 4227858432l, 4160749568l, 4026531840l, 3758096384l, 3221225472l, 2147483648l, 0l, 72340168526266368l, 1155177702467043328l, 0l, 0l, 0l, 0l, 0l, 0l, 72339069014638592l, 0l, 1155173304420532224l, 0l, 0l, 0l, 0l, 0l, 72057594037927936l, 0l, 0l, 1152921504606846976l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l},
        {0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 2l, 0l, 16l, 0l, 0l, 0l, 0l, 0l, 514l, 2064l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 4160749568l, 4026531840l, 3758096384l, 3221225472l, 2147483648l, 0l, 0l, 144680337052532736l, 2310355404934086656l, 0l, 0l, 0l, 0l, 0l, 0l, 144678138029277184l, 0l, 2310346608841064448l, 0l, 0l, 0l, 0l, 0l, 144115188075855872l, 0l, 0l, 2305843009213693952l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l},
        {0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 4l, 0l, 32l, 0l, 0l, 0l, 0l, 256l, 1028l, 4128l, 0l, 0l, 0l, 0l, 0l, 16777216l, 0l, 4026531840l, 3758096384l, 3221225472l, 2147483648l, 0l, 0l, 1099511627776l, 289360674105065472l, 4620710809868173312l, 0l, 0l, 0l, 0l, 0l, 0l, 289356276058554368l, 0l, 4620693217682128896l, 0l, 0l, 0l, 0l, 0l, 288230376151711744l, 0l, 0l, 4611686018427387904l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l},
        {0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 1l, 0l, 8l, 0l, 64l, 0l, 0l, 0l, 0l, 513l, 2056l, 8256l, 0l, 0l, 0l, 0l, 16777216l, 50331648l, 0l, 3758096384l, 3221225472l, 2147483648l, 0l, 0l, 0l, 283673999966208l, 578721348210130944l, -9205322453973204992l, 0l, 0l, 0l, 0l, 281474976710656l, 0l, 578712552117108736l, 0l, -9205357638345293824l, 0l, 0l, 0l, 0l, 0l, 576460752303423488l, 0l, 0l, -9223372036854775808l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l},
        {0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 2l, 0l, 16l, 0l, 128l, 0l, 0l, 0l, 0l, 1026l, 4112l, 16512l, 0l, 0l, 0l, 16777216l, 50331648l, 117440512l, 0l, 3221225472l, 2147483648l, 0l, 0l, 0l, 0l, 72624942037860352l, 1157442696420261888l, 36099165763141632l, 0l, 0l, 0l, 0l, 72620543991349248l, 0l, 1157425104234217472l, 0l, 36028797018963968l, 0l, 0l, 72057594037927936l, 0l, 0l, 1152921504606846976l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l},
        {0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 4l, 0l, 32l, 0l, 0l, 0l, 0l, 0l, 0l, 2052l, 8224l, 32768l, 0l, 0l, 16777216l, 50331648l, 117440512l, 251658240l, 0l, 2147483648l, 0l, 0l, 0l, 0l, 0l, 145249884075720704l, 2314885392840523776l, 140737488355328l, 0l, 0l, 0l, 0l, 145241087982698496l, 0l, 2314850208468434944l, 0l, 0l, 0l, 0l, 144115188075855872l, 0l, 0l, 2305843009213693952l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l},
        {0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 8l, 0l, 64l, 0l, 0l, 0l, 0l, 0l, 0l, 4104l, 16448l, 0l, 0l, 16777216l, 50331648l, 117440512l, 251658240l, 520093696l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 290499768151441408l, 4629770785681047552l, 0l, 0l, 0l, 0l, 0l, 290482175965396992l, 0l, 4629700416936869888l, 0l, 0l, 0l, 0l, 288230376151711744l, 0l, 0l, 4611686018427387904l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l},
            {0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 16l, 0l, 128l, 0l, 0l, 0l, 0l, 0l, 0l, 8208l, 32896l, 0l, 16777216l, 50331648l, 117440512l, 251658240l, 520093696l, 1056964608l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 580999536302882816l, -9187202502347456512l, 0l, 0l, 0l, 0l, 0l, 580964351930793984l, 0l, -9187343239835811840l, 0l, 0l, 0l, 0l, 576460752303423488l, 0l, 0l, -9223372036854775808l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l},
            {0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 1l, 0l, 0l, 16l, 0l, 0l, 0l, 0l, 257l, 0l, 2064l, 0l, 0l, 0l, 0l, 0l, 65793l, 264208l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 1082331758592l, 1065151889408l, 1030792151040l, 962072674304l, 824633720832l, 549755813888l, 0l, 72339069014638592l, 577586652210266112l, 0l, 0l, 0l, 0l, 0l, 0l, 72057594037927936l, 0l, 576460752303423488l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l},
            {0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 2l, 0l, 0l, 32l, 0l, 0l, 0l, 0l, 514l, 0l, 4128l, 0l, 0l, 0l, 0l, 0l, 131586l, 528416l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 1065151889408l, 1030792151040l, 962072674304l, 824633720832l, 549755813888l, 0l, 0l, 144678138029277184l, 1155173304420532224l, 0l, 0l, 0l, 0l, 0l, 0l, 144115188075855872l, 0l, 1152921504606846976l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l},
            {0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 4l, 0l, 0l, 64l, 0l, 0l, 0l, 0l, 1028l, 0l, 8256l, 0l, 0l, 0l, 0l, 65536l, 263172l, 1056832l, 0l, 0l, 0l, 0l, 0l, 4294967296l, 0l, 1030792151040l, 962072674304l, 824633720832l, 549755813888l, 0l, 0l, 281474976710656l, 289356276058554368l, 2310346608841064448l, 0l, 0l, 0l, 0l, 0l, 0l, 288230376151711744l, 0l, 2305843009213693952l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l},
            {0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 8l, 0l, 0l, 128l, 0l, 0l, 256l, 0l, 2056l, 0l, 16512l, 0l, 0l, 0l, 0l, 131328l, 526344l, 2113664l, 0l, 0l, 0l, 0l, 4294967296l, 12884901888l, 0l, 962072674304l, 824633720832l, 549755813888l, 0l, 0l, 0l, 72620543991349248l, 578712552117108736l, 4620693217682128896l, 0l, 0l, 0l, 0l, 72057594037927936l, 0l, 576460752303423488l, 0l, 4611686018427387904l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l},
            {0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 1l, 0l, 0l, 16l, 0l, 0l, 0l, 0l, 0l, 513l, 0l, 4112l, 0l, 32768l, 0l, 0l, 0l, 0l, 262657l, 1052688l, 4227072l, 0l, 0l, 0l, 4294967296l, 12884901888l, 30064771072l, 0l, 824633720832l, 549755813888l, 0l, 0l, 0l, 0l, 145241087982698496l, 1157425104234217472l, -9205357638345293824l, 0l, 0l, 0l, 0l, 144115188075855872l, 0l, 1152921504606846976l, 0l, -9223372036854775808l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l},
            {0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 2l, 0l, 0l, 32l, 0l, 0l, 0l, 0l, 0l, 1026l, 0l, 8224l, 0l, 0l, 0l, 0l, 0l, 0l, 525314l, 2105376l, 8388608l, 0l, 0l, 4294967296l, 12884901888l, 30064771072l, 64424509440l, 0l, 549755813888l, 0l, 0l, 0l, 0l, 0l, 290482175965396992l, 2314850208468434944l, 36028797018963968l, 0l, 0l, 0l, 0l, 288230376151711744l, 0l, 2305843009213693952l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l},
            {0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 4l, 0l, 0l, 64l, 0l, 0l, 0l, 0l, 0l, 2052l, 0l, 16448l, 0l, 0l, 0l, 0l, 0l, 0l, 1050628l, 4210752l, 0l, 0l, 4294967296l, 12884901888l, 30064771072l, 64424509440l, 133143986176l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 580964351930793984l, 4629700416936869888l, 0l, 0l, 0l, 0l, 0l, 576460752303423488l, 0l, 4611686018427387904l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l},
            {0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 8l, 0l, 0l, 128l, 0l, 0l, 0l, 0l, 0l, 4104l, 0l, 32896l, 0l, 0l, 0l, 0l, 0l, 0l, 2101256l, 8421504l, 0l, 4294967296l, 12884901888l, 30064771072l, 64424509440l, 133143986176l, 270582939648l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 1161928703861587968l, -9187343239835811840l, 0l, 0l, 0l, 0l, 0l, 1152921504606846976l, 0l, -9223372036854775808l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l},
            {0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 1l, 0l, 0l, 0l, 32l, 0l, 0l, 0l, 257l, 0l, 0l, 4128l, 0l, 0l, 0l, 0l, 65793l, 0l, 528416l, 0l, 0l, 0l, 0l, 0l, 16843009l, 67637280l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 277076930199552l, 272678883688448l, 263882790666240l, 246290604621824l, 211106232532992l, 140737488355328l, 0l, 72057594037927936l, 288230376151711744l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l},
            {0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 2l, 0l, 0l, 0l, 64l, 0l, 0l, 0l, 514l, 0l, 0l, 8256l, 0l, 0l, 0l, 0l, 131586l, 0l, 1056832l, 0l, 0l, 0l, 0l, 0l, 33686018l, 135274560l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 272678883688448l, 263882790666240l, 246290604621824l, 211106232532992l, 140737488355328l, 0l, 0l, 144115188075855872l, 576460752303423488l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l},
            {0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 4l, 0l, 0l, 0l, 128l, 0l, 0l, 0l, 1028l, 0l, 0l, 16512l, 0l, 0l, 0l, 0l, 263172l, 0l, 2113664l, 0l, 0l, 0l, 0l, 16777216l, 67372036l, 270549120l, 0l, 0l, 0l, 0l, 0l, 1099511627776l, 0l, 263882790666240l, 246290604621824l, 211106232532992l, 140737488355328l, 0l, 0l, 72057594037927936l, 288230376151711744l, 1152921504606846976l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l},
            {0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 8l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 2056l, 0l, 0l, 32768l, 0l, 0l, 65536l, 0l, 526344l, 0l, 4227072l, 0l, 0l, 0l, 0l, 33619968l, 134744072l, 541097984l, 0l, 0l, 0l, 0l, 1099511627776l, 3298534883328l, 0l, 246290604621824l, 211106232532992l, 140737488355328l, 0l, 0l, 0l, 144115188075855872l, 576460752303423488l, 2305843009213693952l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l},
            {0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 16l, 0l, 0l, 0l, 0l, 256l, 0l, 0l, 4112l, 0l, 0l, 0l, 0l, 0l, 131328l, 0l, 1052688l, 0l, 8388608l, 0l, 0l, 0l, 0l, 67240192l, 269488144l, 1082130432l, 0l, 0l, 0l, 1099511627776l, 3298534883328l, 7696581394432l, 0l, 211106232532992l, 140737488355328l, 0l, 0l, 0l, 0l, 288230376151711744l, 1152921504606846976l, 4611686018427387904l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l},
            {0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 1l, 0l, 0l, 0l, 32l, 0l, 0l, 0l, 0l, 513l, 0l, 0l, 8224l, 0l, 0l, 0l, 0l, 0l, 262657l, 0l, 2105376l, 0l, 0l, 0l, 0l, 0l, 0l, 134480385l, 538976288l, 2147483648l, 0l, 0l, 1099511627776l, 3298534883328l, 7696581394432l, 16492674416640l, 0l, 140737488355328l, 0l, 0l, 0l, 0l, 0l, 576460752303423488l, 2305843009213693952l, -9223372036854775808l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l},
            {0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 2l, 0l, 0l, 0l, 64l, 0l, 0l, 0l, 0l, 1026l, 0l, 0l, 16448l, 0l, 0l, 0l, 0l, 0l, 525314l, 0l, 4210752l, 0l, 0l, 0l, 0l, 0l, 0l, 268960770l, 1077952576l, 0l, 0l, 1099511627776l, 3298534883328l, 7696581394432l, 16492674416640l, 34084860461056l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 1152921504606846976l, 4611686018427387904l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l},
            {0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 4l, 0l, 0l, 0l, 128l, 0l, 0l, 0l, 0l, 2052l, 0l, 0l, 32896l, 0l, 0l, 0l, 0l, 0l, 1050628l, 0l, 8421504l, 0l, 0l, 0l, 0l, 0l, 0l, 537921540l, 2155905152l, 0l, 1099511627776l, 3298534883328l, 7696581394432l, 16492674416640l, 34084860461056l, 69269232549888l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 2305843009213693952l, -9223372036854775808l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l},
            {0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 1l, 0l, 0l, 0l, 0l, 64l, 0l, 0l, 257l, 0l, 0l, 0l, 8256l, 0l, 0l, 0l, 65793l, 0l, 0l, 1056832l, 0l, 0l, 0l, 0l, 16843009l, 0l, 135274560l, 0l, 0l, 0l, 0l, 0l, 4311810305l, 17315143744l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 70931694131085312l, 69805794224242688l, 67553994410557440l, 63050394783186944l, 54043195528445952l, 36028797018963968l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l},
            {0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 2l, 0l, 0l, 0l, 0l, 128l, 0l, 0l, 514l, 0l, 0l, 0l, 16512l, 0l, 0l, 0l, 131586l, 0l, 0l, 2113664l, 0l, 0l, 0l, 0l, 33686018l, 0l, 270549120l, 0l, 0l, 0l, 0l, 0l, 8623620610l, 34630287488l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 69805794224242688l, 67553994410557440l, 63050394783186944l, 54043195528445952l, 36028797018963968l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l},
            {0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 4l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 1028l, 0l, 0l, 0l, 32768l, 0l, 0l, 0l, 263172l, 0l, 0l, 4227072l, 0l, 0l, 0l, 0l, 67372036l, 0l, 541097984l, 0l, 0l, 0l, 0l, 4294967296l, 17247241220l, 69260574720l, 0l, 0l, 0l, 0l, 0l, 281474976710656l, 0l, 67553994410557440l, 63050394783186944l, 54043195528445952l, 36028797018963968l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l},
            {0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 8l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 2056l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 526344l, 0l, 0l, 8388608l, 0l, 0l, 16777216l, 0l, 134744072l, 0l, 1082130432l, 0l, 0l, 0l, 0l, 8606711808l, 34494482440l, 138521083904l, 0l, 0l, 0l, 0l, 281474976710656l, 844424930131968l, 0l, 63050394783186944l, 54043195528445952l, 36028797018963968l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l},
            {0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 16l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 4112l, 0l, 0l, 0l, 0l, 65536l, 0l, 0l, 1052688l, 0l, 0l, 0l, 0l, 0l, 33619968l, 0l, 269488144l, 0l, 2147483648l, 0l, 0l, 0l, 0l, 17213489152l, 68988964880l, 277025390592l, 0l, 0l, 0l, 281474976710656l, 844424930131968l, 1970324836974592l, 0l, 54043195528445952l, 36028797018963968l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l},
            {0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 32l, 0l, 0l, 0l, 256l, 0l, 0l, 0l, 8224l, 0l, 0l, 0l, 0l, 131328l, 0l, 0l, 2105376l, 0l, 0l, 0l, 0l, 0l, 67240192l, 0l, 538976288l, 0l, 0l, 0l, 0l, 0l, 0l, 34426978560l, 137977929760l, 549755813888l, 0l, 0l, 281474976710656l, 844424930131968l, 1970324836974592l, 4222124650659840l, 0l, 36028797018963968l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l},
            {0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 1l, 0l, 0l, 0l, 0l, 64l, 0l, 0l, 0l, 513l, 0l, 0l, 0l, 16448l, 0l, 0l, 0l, 0l, 262657l, 0l, 0l, 4210752l, 0l, 0l, 0l, 0l, 0l, 134480385l, 0l, 1077952576l, 0l, 0l, 0l, 0l, 0l, 0l, 68853957121l, 275955859520l, 0l, 0l, 281474976710656l, 844424930131968l, 1970324836974592l, 4222124650659840l, 8725724278030336l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l},
            {0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 2l, 0l, 0l, 0l, 0l, 128l, 0l, 0l, 0l, 1026l, 0l, 0l, 0l, 32896l, 0l, 0l, 0l, 0l, 525314l, 0l, 0l, 8421504l, 0l, 0l, 0l, 0l, 0l, 268960770l, 0l, 2155905152l, 0l, 0l, 0l, 0l, 0l, 0l, 137707914242l, 551911719040l, 0l, 281474976710656l, 844424930131968l, 1970324836974592l, 4222124650659840l, 8725724278030336l, 17732923532771328l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l},
            {0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 1l, 0l, 0l, 0l, 0l, 0l, 128l, 0l, 257l, 0l, 0l, 0l, 0l, 16512l, 0l, 0l, 65793l, 0l, 0l, 0l, 2113664l, 0l, 0l, 0l, 16843009l, 0l, 0l, 270549120l, 0l, 0l, 0l, 0l, 4311810305l, 0l, 34630287488l, 0l, 0l, 0l, 0l, 0l, 1103823438081l, 4432676798592l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, -288230376151711744l, -576460752303423488l, -1152921504606846976l, -2305843009213693952l, -4611686018427387904l, -9223372036854775808l, 0l},
            {0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 2l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 514l, 0l, 0l, 0l, 0l, 32768l, 0l, 0l, 131586l, 0l, 0l, 0l, 4227072l, 0l, 0l, 0l, 33686018l, 0l, 0l, 541097984l, 0l, 0l, 0l, 0l, 8623620610l, 0l, 69260574720l, 0l, 0l, 0l, 0l, 0l, 2207646876162l, 8865353596928l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, -576460752303423488l, -1152921504606846976l, -2305843009213693952l, -4611686018427387904l, -9223372036854775808l, 0l},
            {0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 4l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 1028l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 263172l, 0l, 0l, 0l, 8388608l, 0l, 0l, 0l, 67372036l, 0l, 0l, 1082130432l, 0l, 0l, 0l, 0l, 17247241220l, 0l, 138521083904l, 0l, 0l, 0l, 0l, 1099511627776l, 4415293752324l, 17730707128320l, 0l, 0l, 0l, 0l, 0l, 72057594037927936l, 0l, -1152921504606846976l, -2305843009213693952l, -4611686018427387904l, -9223372036854775808l, 0l},
            {0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 8l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 2056l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 526344l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 134744072l, 0l, 0l, 2147483648l, 0l, 0l, 4294967296l, 0l, 34494482440l, 0l, 277025390592l, 0l, 0l, 0l, 0l, 2203318222848l, 8830587504648l, 35461397479424l, 0l, 0l, 0l, 0l, 72057594037927936l, 216172782113783808l, 0l, -2305843009213693952l, -4611686018427387904l, -9223372036854775808l, 0l},
            {0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 16l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 4112l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 1052688l, 0l, 0l, 0l, 0l, 16777216l, 0l, 0l, 269488144l, 0l, 0l, 0l, 0l, 0l, 8606711808l, 0l, 68988964880l, 0l, 549755813888l, 0l, 0l, 0l, 0l, 4406653222912l, 17661175009296l, 70918499991552l, 0l, 0l, 0l, 72057594037927936l, 216172782113783808l, 504403158265495552l, 0l, -4611686018427387904l, -9223372036854775808l, 0l},
            {0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 32l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 8224l, 0l, 0l, 0l, 65536l, 0l, 0l, 0l, 2105376l, 0l, 0l, 0l, 0l, 33619968l, 0l, 0l, 538976288l, 0l, 0l, 0l, 0l, 0l, 17213489152l, 0l, 137977929760l, 0l, 0l, 0l, 0l, 0l, 0l, 8813306511360l, 35322350018592l, 140737488355328l, 0l, 0l, 72057594037927936l, 216172782113783808l, 504403158265495552l, 1080863910568919040l, 0l, -9223372036854775808l, 0l},
            {0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 64l, 0l, 0l, 256l, 0l, 0l, 0l, 0l, 16448l, 0l, 0l, 0l, 131328l, 0l, 0l, 0l, 4210752l, 0l, 0l, 0l, 0l, 67240192l, 0l, 0l, 1077952576l, 0l, 0l, 0l, 0l, 0l, 34426978560l, 0l, 275955859520l, 0l, 0l, 0l, 0l, 0l, 0l, 17626613022976l, 70644700037184l, 0l, 0l, 72057594037927936l, 216172782113783808l, 504403158265495552l, 1080863910568919040l, 2233785415175766016l, 0l, 0l},
            {0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 0l, 1l, 0l, 0l, 0l, 0l, 0l, 128l, 0l, 0l, 513l, 0l, 0l, 0l, 0l, 32896l, 0l, 0l, 0l, 262657l, 0l, 0l, 0l, 8421504l, 0l, 0l, 0l, 0l, 134480385l, 0l, 0l, 2155905152l, 0l, 0l, 0l, 0l, 0l, 68853957121l, 0l, 551911719040l, 0l, 0l, 0l, 0l, 0l, 0l, 35253226045953l, 141289400074368l, 0l, 72057594037927936l, 216172782113783808l, 504403158265495552l, 1080863910568919040l, 2233785415175766016l, 4539628424389459968l, 0l}};


        Long[][] actualGetBehind = attackMasks.getBehind();
        Assert.assertEquals( expectedAttackMasks, actualGetBehind) ;
    }

}