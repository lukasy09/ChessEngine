package com.agh.technology.chess.engine.runner;

import com.agh.technology.chess.engine.evaluation.ScoreEvaluation;
import com.agh.technology.chess.engine.model.element.Color;
import com.agh.technology.chess.engine.model.state.BoardState;
import com.agh.technology.chess.engine.move.generation.MoveGenerator;

import java.util.Set;

public class Algorithm {

    private ScoreEvaluation scoreEvaluation;
    private MoveGenerator moveGenerator;

    private int bestValue = 0;

    public Algorithm() {
        this.scoreEvaluation = ScoreEvaluation.getInstance();
        this.moveGenerator = MoveGenerator.getInstance();
    }

    public int minmax(BoardState boardState, int depth, Color color) {

        if (depth == 0) {
            return scoreEvaluation.evaluateTotalRating(boardState);
        }

        if (Color.WHITE.equals(color)) {
            Set<BoardState> children = moveGenerator.generateNextPossibleStates(boardState, Color.WHITE);
            for (BoardState child : children) {
                int value = minmax(child, depth - 1, Color.BLACK);
                bestValue = Math.max(bestValue, value);
            }
            return bestValue;
        } else {
            Set<BoardState> children = moveGenerator.generateNextPossibleStates(boardState, Color.BLACK);
            for (BoardState child : children) {
                int value = minmax(child, depth - 1, Color.WHITE);
                bestValue = Math.min(bestValue, value);
            }
            return bestValue;
        }
    }
}
