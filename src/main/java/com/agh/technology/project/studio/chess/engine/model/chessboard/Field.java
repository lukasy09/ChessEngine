package com.agh.technology.project.studio.chess.engine.model.chessboard;

import com.agh.technology.project.studio.chess.engine.model.enums.Color;

/**
 * The class represents a place where a single chess piece can be placed
 */
public class Field {

    private Position position;
    private Color color;

    public Field(Position position, Color color) {
        this.position = position;
        this.color = color;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Field{" +
                "position=" + position +
                ", color=" + color +
                '}';
    }
}
