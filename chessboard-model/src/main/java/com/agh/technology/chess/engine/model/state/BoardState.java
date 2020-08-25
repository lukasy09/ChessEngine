package com.agh.technology.chess.engine.model.state;

import java.util.ArrayList;
import java.util.Collection;

public class BoardState implements Comparable {

    private BlackState blackState;
    private WhiteState whiteState;
    private Integer boardEvaluation;

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

    public Integer getBoardEvaluation() {
        return boardEvaluation;
    }

    public void setBoardEvaluation(int boardEvaluation) {
        this.boardEvaluation = boardEvaluation;
    }

    @Override
    public int compareTo(Object o) {
        return getBoardEvaluation() != null ? getBoardEvaluation().compareTo(((BoardState) o).getBoardEvaluation()) : 0;
    }
}
