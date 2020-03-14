package com.agh.technology.project.studio.chess.engine.model.chessboard;


/**
 * The class represents a location at which a single chess piece can be placed(e.g E7, F1, A2, H4, etc.)
 */
public class Position {
    private File file; // Column, values of [A-Z]
    private Rank rank; // Rows, values of [1-8]

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "Position{" +
                "file=" + file +
                ", rank=" + rank +
                '}';
    }
}
