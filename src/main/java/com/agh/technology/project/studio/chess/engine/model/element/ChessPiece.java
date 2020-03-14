package com.agh.technology.project.studio.chess.engine.model.element;

import com.agh.technology.project.studio.chess.engine.model.chessboard.Field;
import com.agh.technology.project.studio.chess.engine.model.enums.Color;

public abstract class ChessPiece {
    private Field field;
    private Color color;

    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
