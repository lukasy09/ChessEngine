package com.agh.technology.chess.engine.evaluation;

import com.agh.technology.chess.engine.evaluation.material.MaterialRatingEvaluation;
import com.agh.technology.chess.engine.evaluation.position.PositionRatingEvaluation;
import com.agh.technology.chess.engine.model.state.BoardState;

public class ScoreEvaluation {

    private final MaterialRatingEvaluation materialRatingEvaluation;
    private final PositionRatingEvaluation positionRatingEvaluation;

    public ScoreEvaluation(PositionRatingEvaluation positionRatingEvaluation) {
        this.positionRatingEvaluation = positionRatingEvaluation;
        this.materialRatingEvaluation = new MaterialRatingEvaluation();
    }

    public int evaluateTotalRating(BoardState boardState) {
        int rating = 0;
        rating += materialRatingEvaluation.evaluateMaterialRating(boardState);
        rating += positionRatingEvaluation.evaluatePositionRating(boardState);
        return rating;
    }
}
