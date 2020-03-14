package com.agh.technology.project.studio.chess.engine.model.enums;

/**
 * Representing basic colors which exist on the chessboard
 */
public enum Color {
    WHITE("WHITE"),
    BLACK("BLACK");

    private String value;

    Color(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Color{" +
                "value='" + value + '\'' +
                '}';
    }
}
