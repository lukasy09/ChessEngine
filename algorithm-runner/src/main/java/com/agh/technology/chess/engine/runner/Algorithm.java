package com.agh.technology.chess.engine.runner;

import com.agh.technology.chess.engine.evaluation.ScoreEvaluation;
import com.agh.technology.chess.engine.model.element.Color;
import com.agh.technology.chess.engine.model.state.BoardState;
import com.agh.technology.chess.engine.move.generation.MoveGenerator;

import java.util.Set;

public class Algorithm {

    private final ScoreEvaluation scoreEvaluation;
    private final MoveGenerator moveGenerator;

    public Algorithm() {
        this.scoreEvaluation = ScoreEvaluation.getInstance();
        this.moveGenerator = MoveGenerator.getInstance();
    }

    public int minmax(BoardState boardState, int depth, Color color){
        return minmax(boardState, depth, color, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private int minmax(BoardState boardState, int depth, Color color, int alpha, int beta) {

        if (depth == 0) {
            return scoreEvaluation.evaluateTotalRating(boardState);
        }

        if (Color.WHITE.equals(color)) {
            int maxEvaluation = Integer.MIN_VALUE;
            Set<BoardState> children = moveGenerator.generateNextPossibleStates(boardState, Color.WHITE);
            for (BoardState child : children) {
                int evaluation = minmax(child, depth - 1, Color.BLACK, alpha, beta);
                maxEvaluation = Math.max(maxEvaluation, evaluation);
                alpha = Math.max(alpha,evaluation);
                if(beta <= alpha){
                    break;
                }
            }
            return maxEvaluation;
        } else {
            int minEvaluation = Integer.MAX_VALUE;
            Set<BoardState> children = moveGenerator.generateNextPossibleStates(boardState, Color.BLACK);
            for (BoardState child : children) {
                int evaluation = minmax(child, depth - 1, Color.WHITE, alpha, beta);
                minEvaluation = Math.min(minEvaluation, evaluation);
                beta = Math.min(beta,evaluation);
                if(beta <= alpha){
                    break;
                }
            }
            return minEvaluation;
        }
    }
}
