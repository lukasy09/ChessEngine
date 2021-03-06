package com.agh.technology.chess.engine.model.state;


import com.agh.technology.chess.engine.model.element.PieceType;

import java.util.Objects;

public class BlackState {
    private long king;
    private long queen;
    private long rook;
    private long bishop;
    private long knight;
    private long pawn;

    public BlackState() {
        this.king = 0L;
        this.queen = 0L;
        this.rook = 0L;
        this.bishop = 0L;
        this.knight = 0L;
        this.pawn = 0L;
    }

    public BlackState(BlackState stateToCopy){
        king = stateToCopy.king;
        queen = stateToCopy.queen;
        rook = stateToCopy.rook;
        bishop = stateToCopy.bishop;
        knight = stateToCopy.knight;
        pawn = stateToCopy.pawn;
    }

    public long getOccupied(){
        return king
                | queen
                | rook
                | bishop
                | knight
                | pawn;
    }

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

    public long getPieceBitboard(PieceType pieceType){
        long bitboard = 0L;
        switch (pieceType){
            case KING:
                bitboard = king;
                break;
            case QUEEN:
                bitboard = queen;
                break;
            case BISHOP:
                bitboard = bishop;
                break;
            case ROOK:
                bitboard = rook;
                break;
            case KNIGHT:
                bitboard = knight;
                break;
            case PAWN:
                bitboard = pawn;
                break;
        }
        return bitboard;
    }

    public void setPieceBitboard(PieceType pieceType, long bitboard){
        switch (pieceType){
            case KING:
                king = bitboard;
                break;
            case QUEEN:
                queen = bitboard;
                break;
            case BISHOP:
                bishop = bitboard;
                break;
            case ROOK:
                rook = bitboard;
                break;
            case KNIGHT:
                knight = bitboard;
                break;
            case PAWN:
                pawn = bitboard;
                break;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlackState that = (BlackState) o;
        return king == that.king &&
                queen == that.queen &&
                rook == that.rook &&
                bishop == that.bishop &&
                knight == that.knight &&
                pawn == that.pawn;
    }

    @Override
    public int hashCode() {
        return Objects.hash(king, queen, rook, bishop, knight, pawn);
    }
}
