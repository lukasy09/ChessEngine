package com.agh.technology.chess.engine.model.state;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class BoardState {

    private BlackState blackState;
    private WhiteState whiteState;

    public BoardState(){
        super();
    }

    public BoardState(BoardState stateToCopy){
        this.whiteState = new WhiteState(stateToCopy.whiteState);
        this.blackState = new BlackState(stateToCopy.blackState);
    }

    public static ArrayList<Integer> getPositionIndexes(long bitboard){
        ArrayList<Integer> positionIndexList = new ArrayList<>();
        while (bitboard != 0) {
            int position = Long.numberOfTrailingZeros(bitboard);
            positionIndexList.add(position);
            bitboard &= ~(1L << position);
        }

        return positionIndexList;
    }

    public long getOccupied(){
        return whiteState.getOccupied() | blackState.getOccupied();
    }

    public BlackState getBlackState() {
        return blackState;
    }

    public void setBlackState(BlackState blackState) {
        this.blackState = blackState;
    }

    public WhiteState getWhiteState() {
        return whiteState;
    }

    public void setWhiteState(WhiteState whiteState) {
        this.whiteState = whiteState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoardState that = (BoardState) o;
        return Objects.equals(blackState, that.blackState) &&
                Objects.equals(whiteState, that.whiteState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(blackState, whiteState);
    }
}
