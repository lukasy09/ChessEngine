package com.agh.technology.chess.engine.model.state;

import com.agh.technology.chess.engine.model.element.Board;
import com.agh.technology.chess.engine.model.element.Color;
import com.agh.technology.chess.engine.model.element.ColorPiece;
import com.agh.technology.chess.engine.model.element.PieceType;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import static com.agh.technology.chess.engine.model.element.ColorPiece.*;
import static com.agh.technology.chess.engine.model.element.ColorPiece.WR;

public class BoardState {

    private BlackState blackState;
    private WhiteState whiteState;
    private int[] move;
    private Character pawnPromotion;

    private static final ColorPiece[][] initialBoard = {
            {WR, WN, WB, WQ, WK, WB, WN, WR},
            {WP, WP, WP, WP, WP, WP, WP, WP},
            {NO, NO, NO, NO, NO, NO, NO, NO},
            {NO, NO, NO, NO, NO, NO, NO, NO},
            {NO, NO, NO, NO, NO, NO, NO, NO},
            {NO, NO, NO, NO, NO, NO, NO, NO},
            {BP, BP, BP, BP, BP, BP, BP, BP},
            {BR, BN, BB, BQ, BK, BB, BN, BR}
    };

    public BoardState(){
        this.blackState = new BlackState();
        this.whiteState = new WhiteState();
    }

    public BoardState(BoardState stateToCopy){
        this.whiteState = new WhiteState(stateToCopy.whiteState);
        this.blackState = new BlackState(stateToCopy.blackState);
    }

    public static BoardState getStartingState(){
        BoardState initialState = new BoardState();
        initialState.buildPositions(initialBoard);
        return initialState;
    }

    private void buildPositions(ColorPiece[][] board) {
        for (int i = 0; i < 64; i++) {
            switch (board[i / 8][i % 8]) {
                case WP:
                    whiteState.setPawn(whiteState.getPawn() | (1L << i));
                    break;
                case WN:
                    whiteState.setKnight(whiteState.getKnight() | (1L << i));
                    break;
                case WB:
                    whiteState.setBishop(whiteState.getBishop() | (1L << i));
                    break;
                case WR:
                    whiteState.setRook(whiteState.getRook() | (1L << i));
                    break;
                case WQ:
                    whiteState.setQueen(whiteState.getQueen() | (1L << i));
                    break;
                case WK:
                    whiteState.setKing(whiteState.getKing() | (1L << i));
                    break;
                case BP:
                    blackState.setPawn(blackState.getPawn() | (1L << i));
                    break;
                case BB:
                    blackState.setBishop(blackState.getBishop() | (1L << i));
                    break;
                case BN:
                    blackState.setKnight(blackState.getKnight() | (1L << i));
                    break;
                case BR:
                    blackState.setRook(blackState.getRook() | (1L << i));
                    break;
                case BQ:
                    blackState.setQueen(blackState.getQueen() | (1L << i));
                    break;
                case BK:
                    blackState.setKing(blackState.getKing() | (1L << i));
                    break;
            }
        }
    }
    public void applyUCIMove(String move, Color color){
        int indexFrom = translateUCIIndexToIntIndex(move.substring(0,2));
        int indexTo = translateUCIIndexToIntIndex(move.substring(2,4));

        if(Color.WHITE.equals(color)){
            for(PieceType pieceType : PieceType.values()){
                if((whiteState.getPieceBitboard(pieceType) & (1L << indexFrom)) != 0L){
                    whiteState.setPieceBitboard(pieceType, (whiteState.getPieceBitboard(pieceType) & (~(1L << indexFrom))) | 1L << indexTo);
                    if((blackState.getOccupied() & (1L << indexTo)) != 0L){
                        for(PieceType opponentPieceType : PieceType.values()){
                            long opponentPieceBitmap = blackState.getPieceBitboard(opponentPieceType);
                            if((opponentPieceBitmap & (1L << indexTo)) != 0L){
                                blackState.setPieceBitboard(opponentPieceType, opponentPieceBitmap & ~(1L << indexTo));
                                break;
                            }
                        }
                    }
                    break;
                }
            }
        } else{
            for(PieceType pieceType : PieceType.values()){
                if((blackState.getPieceBitboard(pieceType) & (1L << indexFrom)) != 0L){
                    blackState.setPieceBitboard(pieceType, (blackState.getPieceBitboard(pieceType) & (~(1L << indexFrom))) | 1L << indexTo);
                    if((whiteState.getOccupied() & (1L << indexTo)) != 0L){
                        for(PieceType opponentPieceType : PieceType.values()){
                            long opponentPieceBitmap = whiteState.getPieceBitboard(opponentPieceType);
                            if((opponentPieceBitmap & (1L << indexTo)) != 0L){
                                whiteState.setPieceBitboard(opponentPieceType, opponentPieceBitmap & ~(1L << indexTo));
                                break;
                            }
                        }
                    }
                    break;
                }
            }
        }
    }

    private static int translateUCIIndexToIntIndex(String UCIIndex){
        return ((int)UCIIndex.charAt(0) - 97) + (Character.getNumericValue(UCIIndex.charAt(1)) - 1) * 8;
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

    public int[] getMove() {
        return move;
    }

    public void setMove(int[] move) {
        this.move = move;
    }

    public Character getPawnPromotion() {
        return pawnPromotion;
    }

    public void setPawnPromotion(Character pawnPromotion) {
        this.pawnPromotion = pawnPromotion;
    }

    public static void main(String[] args) {

        BoardState board = BoardState.getStartingState();
        board.applyUCIMove("e2e7", Color.WHITE);
        System.out.println(Board.displayAsFormattedBinary(board.whiteState.getPawn()));
        System.out.println();
        System.out.println(Board.displayAsFormattedBinary(board.blackState.getPawn()));
    }
}
