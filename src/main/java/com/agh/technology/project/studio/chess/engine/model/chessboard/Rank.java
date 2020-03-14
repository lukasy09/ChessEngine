package com.agh.technology.project.studio.chess.engine.model.chessboard;

/**
 * The class represent a single row on the chessboard(values from 1 to 8)
 */
public class Rank {
    private Integer value;

    public Rank(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
