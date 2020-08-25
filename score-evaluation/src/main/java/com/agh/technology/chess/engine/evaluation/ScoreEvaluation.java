package com.agh.technology.chess.engine.evaluation;

import com.agh.technology.chess.engine.evaluation.material.MaterialRatingEvaluation;
import com.agh.technology.chess.engine.evaluation.position.PositionRatingEvaluation;
import com.agh.technology.chess.engine.model.state.BoardState;

public class ScoreEvaluation {

    private final MaterialRatingEvaluation materialRatingEvaluation;
    private final PositionRatingEvaluation positionRatingEvaluation;

    private static ScoreEvaluation instance;

    private ScoreEvaluation() {
        this.positionRatingEvaluation = new PositionRatingEvaluation();
        this.materialRatingEvaluation = new MaterialRatingEvaluation();
    }

    public static ScoreEvaluation getInstance(){
        if(instance == null){
            instance = new ScoreEvaluation();
        }
        return instance;
    }

    public int evaluateTotalRating(BoardState boardState) {
        int rating = 0;
        rating += materialRatingEvaluation.evaluateMaterialRating(boardState);
        rating += positionRatingEvaluation.evaluatePositionRating(boardState);
        return rating;
    }
}
