package com.agh.technology.chess.engine.model.state;

public class WhiteState {
    private long king;
    private long queen;
    private long rook;
    private long bishop;
    private long knight;
    private long pawn;

    public long getKing() {
        return king;
    }

    public void setKing(long king) {
        this.king = king;
    }

    public long getQueen() {
        return queen;
    }

    public void setQueen(long queen) {
        this.queen = queen;
    }

    public long getRook() {
        return rook;
    }

    public void setRook(long rook) {
        this.rook = rook;
    }

    public long getBishop() {
        return bishop;
    }

    public void setBishop(long bishop) {
        this.bishop = bishop;
    }

    public long getKnight() {
        return knight;
    }

    public void setKnight(long knight) {
        this.knight = knight;
    }

    public long getPawn() {
        return pawn;
    }

    public void setPawn(long pawn) {
        this.pawn = pawn;
    }
}
