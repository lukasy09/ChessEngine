package com.agh.technology.chess.engine.model.state;

import com.agh.technology.chess.engine.model.element.Color;
import com.agh.technology.chess.engine.model.element.ColorPiece;
import com.agh.technology.chess.engine.model.element.PieceType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

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

    public static String displayAsFormattedBinary(long value){
        String unreversedBoard = displayAsBinary(value).replaceAll("(.{8})", "$1\n");
        return Arrays.stream(unreversedBoard.split("\n")).map(line -> new StringBuilder(line).reverse().toString()).collect(Collectors.joining("\n"));
    }

    public static String displayAsBinary(long value){
        String binaryString = Long.toBinaryString(value);
        return String.format("%64s", binaryString).replace(' ', '0');
    }

    public BoardState(){
        this.blackState = new BlackState();
        this.whiteState = new WhiteState();
    }

    public BoardState(BoardState stateToCopy){
        this.whiteState = new WhiteState(stateToCopy.whiteState);
        this.blackState = new BlackState(stateToCopy.blackState);
        this.move = stateToCopy.getMove();
        this.pawnPromotion = stateToCopy.getPawnPromotion();
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
        Character pawnPromotion = move.length() == 5 ? move.charAt(4) : null;
        PieceType pieceMoved = null;

        if(Color.WHITE.equals(color)){
            for(PieceType pieceType : PieceType.values()){
                if((whiteState.getPieceBitboard(pieceType) & (1L << indexFrom)) != 0L){
                    if(PieceType.PAWN.equals(pieceType)){
                        if((indexTo == (indexFrom + 7) || indexTo == (indexFrom + 9)) && ((blackState.getOccupied() & (1L << indexTo)) == 0L)){
                            blackState.setPawn(blackState.getPawn() & ~(1L << (indexTo - 8)));
                        }
                    }
                    pieceMoved = pieceType;
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
                    if(PieceType.PAWN.equals(pieceType)){
                        if((indexTo == (indexFrom - 7) || indexTo == (indexFrom - 9)) && ((whiteState.getOccupied() & (1L << indexTo)) == 0L)){
                            whiteState.setPawn(whiteState.getPawn() & ~(1L << (indexTo + 8)));
                        }
                    }
                    pieceMoved = pieceType;
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

        if(pawnPromotion != null){
            if(color.equals(Color.WHITE)){
                whiteState.setPawn(whiteState.getPawn() & (~(1L << indexTo)));
                switch (pawnPromotion){
                    case 'q':
                        whiteState.setQueen(whiteState.getQueen() | (1L << indexTo));
                        break;
                    case 'n':
                        whiteState.setKnight(whiteState.getKnight() | (1L << indexTo));
                        break;
                    case 'r':
                        whiteState.setRook(whiteState.getRook() | (1L << indexTo));
                        break;
                    case 'b':
                        whiteState.setBishop(whiteState.getBishop() | (1L << indexTo));
                        break;
                }
            } else {
                blackState.setPawn(blackState.getPawn() & (~(1L << indexTo)));
                switch (pawnPromotion){
                    case 'q':
                        blackState.setQueen(blackState.getQueen() | (1L << indexTo));
                        break;
                    case 'n':
                        blackState.setKnight(blackState.getKnight() | (1L << indexTo));
                        break;
                    case 'r':
                        blackState.setRook(blackState.getRook() | (1L << indexTo));
                        break;
                    case 'b':
                        blackState.setBishop(blackState.getBishop() | (1L << indexTo));
                        break;
                }
            }
        }
        //detect castling move
        if(PieceType.KING.equals(pieceMoved)){
            if(indexFrom == 4 && indexTo == 6){
                whiteState.setRook((whiteState.getRook() & (~(1L << 7))) | (1L << 5));
            } else if(indexFrom == 4 && indexTo == 2){
                whiteState.setRook((whiteState.getRook() & (~1L)) | (1L << 3));
            } else if(indexFrom == 60 && indexTo == 62){
                blackState.setRook((blackState.getRook() & (~(1L << 63))) | (1L << 61));
            } else if(indexFrom == 60 && indexTo == 58){
                blackState.setRook((blackState.getRook() & (~(1L << 56))) | (1L << 59));
            }
        }
    }

    private static int translateUCIIndexToIntIndex(String UCIIndex){
        return ((int)UCIIndex.charAt(0) - 97) + (Character.getNumericValue(UCIIndex.charAt(1)) - 1) * 8;
    }

    public static String translateIntIndexToUCIIndex(int index){
        StringBuilder uciIndexBuilder = new StringBuilder();
        uciIndexBuilder.append((char)((index%8) + 97));
        uciIndexBuilder.append((index/8)+1);

        return uciIndexBuilder.toString();
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

}
