package com.agh.technology.project.studio.chess.engine.model.enums;

/**
 * Representing basic colors which exist on the chessboard
 */
public enum Color {
    WHITE("WHITE", "W"),
    BLACK("BLACK", "B");

    private String value;
    private String abbreviation;

    Color(String value, String abbreviation){
        this.value = value;
        this.abbreviation = abbreviation;
    }

    public String getValue() {
        return value;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    @Override
    public String toString() {
        return "Color{" +
                "value='" + value + '\'' +
                '}';
    }
}
