package com.agh.technology.chess.engine.model.element;

public enum ColorPiece {
    WR("WP"), // white rook
    WN("WN"), // white knight
    WB("WB"), // white bishop
    WQ("WQ"), // white queen
    WK("WK"), // white king
    WP("WP"), // white pawn

    BR("BR"), // black rook
    BN("BN"), // black knight
    BB("BB"), // black bishop
    BQ("BQ"), // black queen
    BK("BK"), // black king
    BP("BP"), // black pawn
    NO(" "); // empty field

    private String id;

    ColorPiece(String id){
      this.id = id;

    }

    public String getId() {
        return id;
    }

}
