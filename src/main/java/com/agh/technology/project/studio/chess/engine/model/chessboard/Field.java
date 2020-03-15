package com.agh.technology.project.studio.chess.engine.model.chessboard;

import com.agh.technology.project.studio.chess.engine.model.enums.Color;

/**
 * The class represents a place where a single chess piece can be placed
 */
public class Field {

    private String file;

    public Field() {}

    public Field(String file, int rank, Color color) {
        this.file = file;
        this.rank = rank;
        this.color = color;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    private int rank;
    private Color color;


    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Field{" +
                "file='" + file + '\'' +
                ", rank=" + rank +
                ", color=" + color +
                '}';
    }
}
