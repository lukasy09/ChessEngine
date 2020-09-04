import com.agh.technology.chess.engine.evaluation.ScoreEvaluation;
import com.agh.technology.chess.engine.evaluation.material.MaterialRatingEvaluation;
import com.agh.technology.chess.engine.evaluation.position.PositionRatingEvaluation;
import com.agh.technology.chess.engine.model.state.BoardState;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class PositionRatingEvaluationTest {

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

}