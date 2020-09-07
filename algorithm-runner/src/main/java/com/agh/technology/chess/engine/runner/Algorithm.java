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

    public BoardState minmax(BoardState boardState, int depth, Color color){
        return minmax(boardState, depth, color, Integer.MIN_VALUE, Integer.MAX_VALUE).getBoardState();
    }

    private MinmaxPair minmax(BoardState boardState, int depth, Color color, int alpha, int beta) {

        if (depth == 0) {
            return new MinmaxPair(boardState, scoreEvaluation.evaluateTotalRating(boardState));
        }

        if (Color.WHITE.equals(color)) {
            int maxEvaluation = Integer.MIN_VALUE;
            BoardState maxEvaluationState = null;
            if(boardState.getWhiteState().getKing() == 0){
                return new MinmaxPair(boardState, Integer.MIN_VALUE);
            }
            Set<BoardState> children = moveGenerator.generateNextPossibleStates(boardState, Color.WHITE);
            for (BoardState child : children) {
                int evaluation = minmax(child, depth - 1, Color.BLACK, alpha, beta).getEvaluation();
                if(maxEvaluation < evaluation){
                    maxEvaluation = evaluation;
                    maxEvaluationState = child;
                }
                alpha = Math.max(alpha,maxEvaluation);
                if(beta <= alpha){
                    break;
                }
            }
            return new MinmaxPair(maxEvaluationState, maxEvaluation);
        } else {
            int minEvaluation = Integer.MAX_VALUE;
            BoardState minEvaluationState = null;
            if(boardState.getBlackState().getKing() == 0){
                return new MinmaxPair(boardState, Integer.MAX_VALUE);
            }
            Set<BoardState> children = moveGenerator.generateNextPossibleStates(boardState, Color.BLACK);
            for (BoardState child : children) {
                int evaluation = minmax(child, depth - 1, Color.WHITE, alpha, beta).getEvaluation();
                if(minEvaluation > evaluation){
                    minEvaluation = evaluation;
                    minEvaluationState = child;
                }
                minEvaluation = Math.min(minEvaluation, evaluation);
                beta = Math.min(beta,minEvaluation);
                if(beta <= alpha){
                    break;
                }
            }
            return new MinmaxPair(minEvaluationState, minEvaluation);
        }
    }

    public static void main(String[] args) {
        Algorithm algorithm = new Algorithm();
        Set<BoardState> boardStateSet = MoveGenerator.getInstance().generateNextPossibleStates(BoardState.getStartingState(), Color.WHITE);
        for (BoardState state : boardStateSet){
            System.out.println(algorithm.minmax(state,7, Color.BLACK));
        }

    }
};



