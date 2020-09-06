import com.agh.technology.chess.engine.evaluation.ScoreEvaluation;
import com.agh.technology.chess.engine.evaluation.material.MaterialRatingEvaluation;
import com.agh.technology.chess.engine.evaluation.position.PositionRatingEvaluation;
import com.agh.technology.chess.engine.model.state.BoardState;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class PositionRatingEvaluationTest {

    private static long parseLong(String s, int base) {
        return new BigInteger(s.replaceAll(" ", ""), base).longValue();
    }


    @Test
    public void initialPositionRating() throws Exception
    {
        BoardState boardState = BoardState.getStartingState();
        PositionRatingEvaluation positionEvaluation = new PositionRatingEvaluation();
        int actualRating = positionEvaluation.evaluatePositionRating(boardState);
        Assert.assertEquals(0, actualRating) ;
    }

    @Test
    public void initialMaterialRating() throws Exception
    {
        BoardState boardState = BoardState.getStartingState();
        MaterialRatingEvaluation materialRatingEvaluation = new MaterialRatingEvaluation();
        int actualRating = materialRatingEvaluation.evaluateMaterialRating(boardState);
        Assert.assertEquals(0, actualRating) ;
    }

    @Test
    public void initialTotalRating() throws Exception
    {
        BoardState boardState = BoardState.getStartingState();
        ScoreEvaluation scoreEvaluation = ScoreEvaluation.getInstance();
        int actualRating = scoreEvaluation.evaluateTotalRating(boardState);
        Assert.assertEquals(0, actualRating) ;
    }
    @Test
    public void scoreEvaluation() throws Exception {
        BoardState boardState = BoardState.getStartingState();
        ScoreEvaluation scoreEvaluation = ScoreEvaluation.getInstance();

        Long bishop = parseLong("00100100 00000000 000000000000000000000000000000000000000000000000", 2);
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

        int actualRating = scoreEvaluation.evaluateTotalRating(boardState);
        Assert.assertEquals(1424, actualRating) ;

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