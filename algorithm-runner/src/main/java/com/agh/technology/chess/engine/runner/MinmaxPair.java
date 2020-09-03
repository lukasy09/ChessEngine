package com.agh.technology.chess.engine.runner;

import com.agh.technology.chess.engine.model.state.BoardState;

public class MinmaxPair {
    private BoardState boardState;
    private int evaluation;

    public MinmaxPair(BoardState boardState, int evaluation) {
        this.boardState = boardState;
        this.evaluation = evaluation;
    }

    public BoardState getBoardState() {
        return boardState;
    }

    public int getEvaluation() {
        return evaluation;
    }
}
