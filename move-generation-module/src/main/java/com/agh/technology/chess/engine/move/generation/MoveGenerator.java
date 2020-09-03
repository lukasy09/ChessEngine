package com.agh.technology.chess.engine.move.generation;

import com.agh.technology.chess.engine.model.element.Color;
import com.agh.technology.chess.engine.model.element.PieceType;
import com.agh.technology.chess.engine.model.state.BlackState;
import com.agh.technology.chess.engine.model.state.BoardState;
import com.agh.technology.chess.engine.model.state.WhiteState;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MoveGenerator {
    private final long FIRST_ROW_MASK = 0x00000000000000FFL;
    private final long EIGHT_ROW_MASK = -0x100000000000000L;


    private final AttackMasks attackMasks;
    private static MoveGenerator instance;

    private MoveGenerator(){
        attackMasks = AttackMasks.getInstance();
    }

    public static MoveGenerator getInstance(){
        if(instance == null){
            instance = new MoveGenerator();
        }

        return instance;
    }

    private Long generatePieceMoves(PieceType pieceType, int pieceIndex, BoardState boardState, Color color){
        if(pieceType.equals(PieceType.PAWN)){
            return generatePawnMoves(pieceType, pieceIndex, boardState, color);
        }

        long ts = attackMasks.getAttackMoves().get(pieceType).get(pieceIndex);
        for(long b = boardState.getOccupied() & attackMasks.getBlockersAndBeyond().get(pieceType).get(pieceIndex); b != 0L; b = b & (b-1)){
            int sq = Long.numberOfTrailingZeros(b);
            ts = ts & ~attackMasks.getBehind()[pieceIndex][sq];
        }
        return ts;
    }

    private Long generatePawnMoves(PieceType pieceType, int pieceIndex, BoardState boardState, Color color){
        if(!pieceType.equals(PieceType.PAWN)){
            throw new UnsupportedOperationException();
        }

        long nonCaptureMoves;
        if(Color.WHITE.equals(color)){
            nonCaptureMoves = attackMasks.getPawnMoves().get(color).get(pieceIndex) & ~(boardState.getOccupied() | ((boardState.getOccupied() << 8) &~(1L << (pieceIndex + 8))));
        }else{
            nonCaptureMoves = attackMasks.getPawnMoves().get(color).get(pieceIndex) & ~(boardState.getOccupied() | ((boardState.getOccupied() >>> 8) &~(1L << (pieceIndex - 8))));
        }

        long captureMoves = attackMasks.getPawnCaptureMoves().get(color).get(pieceIndex) & (color.equals(Color.BLACK) ? boardState.getWhiteState().getOccupied() : boardState.getBlackState().getOccupied());

        return captureMoves | nonCaptureMoves;
    }



    public Set<BoardState> generateNextPossibleStates(BoardState boardState, Color color){
        if(color.equals(Color.WHITE)){
            return generateNextPossibleWhiteStates(boardState);
        } else{
            return generateNextPossibleBlackStates(boardState);
        }
    }
    private Set<BoardState> generateNextPossibleBlackStates(BoardState boardState){
        Set<BoardState> possibleStates = new HashSet<>();
        BlackState blackState = boardState.getBlackState();
        for(PieceType pieceType : PieceType.values()){
            long pieceBitmap = blackState.getPieceBitboard(pieceType);
            for(int index : BoardState.getPositionIndexes(pieceBitmap)){
                long availableMoves = generatePieceMoves(pieceType, index, boardState, Color.BLACK) & ~blackState.getOccupied();
                if(availableMoves != 0L){
                    List<Integer> availableIndexes = BoardState.getPositionIndexes(availableMoves);
                    for(int nextIndex : availableIndexes){
                        long nextIndexBitboard = 1L << nextIndex;
                        BoardState nextBoardState = new BoardState(boardState);
                        nextBoardState.getBlackState().setPieceBitboard(pieceType, (pieceBitmap & (~(1L << index))) | nextIndexBitboard);
                        nextBoardState.setMove(new int[]{index, nextIndex});
                        if((boardState.getWhiteState().getOccupied() & nextIndexBitboard) != 0L){
                            for(PieceType opponentPieceType : PieceType.values()){
                                long opponentPieceBitmap = boardState.getWhiteState().getPieceBitboard(opponentPieceType);
                                if((opponentPieceBitmap & nextIndexBitboard) != 0L){
                                    nextBoardState.getWhiteState().setPieceBitboard(opponentPieceType, opponentPieceBitmap & ~nextIndexBitboard);
                                    break;
                                }
                            }
                        }
                        //Check if pawn should be replaced with queen or knight
                        if((nextBoardState.getBlackState().getPawn() & FIRST_ROW_MASK) == 0){
                            possibleStates.add(nextBoardState);
                        }else{
                            long firstRowPawnBitmap = nextBoardState.getBlackState().getPawn() & FIRST_ROW_MASK;

                            BoardState pawnQueenChangeState = new BoardState(nextBoardState);
                            pawnQueenChangeState.getBlackState().setQueen(pawnQueenChangeState.getBlackState().getQueen() | firstRowPawnBitmap);
                            pawnQueenChangeState.getBlackState().setPawn(pawnQueenChangeState.getBlackState().getPawn() & (~firstRowPawnBitmap));
                            pawnQueenChangeState.setPawnPromotion('q');
                            possibleStates.add(pawnQueenChangeState);

                            BoardState pawnKnightChangeState = new BoardState(nextBoardState);
                            pawnKnightChangeState.getBlackState().setKnight(pawnKnightChangeState.getBlackState().getKnight() | firstRowPawnBitmap);
                            pawnKnightChangeState.getBlackState().setPawn(pawnKnightChangeState.getBlackState().getPawn() & (~firstRowPawnBitmap));
                            pawnQueenChangeState.setPawnPromotion('n');
                            possibleStates.add(pawnKnightChangeState);
                        }
                    }
                }
            }
        }

        return possibleStates;
    }
    private Set<BoardState> generateNextPossibleWhiteStates(BoardState boardState){
        Set<BoardState> possibleStates = new HashSet<>();
        WhiteState whiteState = boardState.getWhiteState();
        for(PieceType pieceType : PieceType.values()){
            long pieceBitmap = whiteState.getPieceBitboard(pieceType);
            for(int index : BoardState.getPositionIndexes(pieceBitmap)){
                long availableMoves = generatePieceMoves(pieceType, index, boardState, Color.WHITE) & ~whiteState.getOccupied();
                if(availableMoves != 0L){
                    List<Integer> availableIndexes = BoardState.getPositionIndexes(availableMoves);
                    for(int nextIndex : availableIndexes){
                        long nextIndexBitboard = 1L << nextIndex;
                        BoardState nextBoardState = new BoardState(boardState);
                        nextBoardState.setMove(new int[]{index, nextIndex});
                        nextBoardState.getWhiteState().setPieceBitboard(pieceType, (pieceBitmap & (~(1L << index))) | nextIndexBitboard);
                        if((boardState.getBlackState().getOccupied() & nextIndexBitboard) != 0L){
                            for(PieceType opponentPieceType : PieceType.values()){
                                long opponentPieceBitmap = boardState.getBlackState().getPieceBitboard(opponentPieceType);
                                if((opponentPieceBitmap & nextIndexBitboard) != 0L){
                                    nextBoardState.getBlackState().setPieceBitboard(opponentPieceType, opponentPieceBitmap & ~nextIndexBitboard);
                                    break;
                                }
                            }
                        }
                        //Check if pawn should be replaced with queen or knight
                        if((nextBoardState.getWhiteState().getPawn() & EIGHT_ROW_MASK) == 0){
                            possibleStates.add(nextBoardState);
                        }else{
                            long eightRowPawnBitmap = nextBoardState.getWhiteState().getPawn() & EIGHT_ROW_MASK;

                            BoardState pawnQueenChangeState = new BoardState(nextBoardState);
                            pawnQueenChangeState.getWhiteState().setQueen(pawnQueenChangeState.getWhiteState().getQueen() | eightRowPawnBitmap);
                            pawnQueenChangeState.getWhiteState().setPawn(pawnQueenChangeState.getWhiteState().getPawn() & (~eightRowPawnBitmap));
                            pawnQueenChangeState.setPawnPromotion('q');
                            possibleStates.add(pawnQueenChangeState);

                            BoardState pawnKnightChangeState = new BoardState(nextBoardState);
                            pawnKnightChangeState.getWhiteState().setKnight(pawnKnightChangeState.getWhiteState().getKnight() | eightRowPawnBitmap);
                            pawnKnightChangeState.getWhiteState().setPawn(pawnKnightChangeState.getWhiteState().getPawn() & (~eightRowPawnBitmap));
                            pawnQueenChangeState.setPawnPromotion('n');
                            possibleStates.add(pawnKnightChangeState);
                        }
                    }
                }
            }
        }

        return possibleStates;
    }
}
