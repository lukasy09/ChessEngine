import com.agh.technology.chess.engine.evaluation.ScoreEvaluation;
import com.agh.technology.chess.engine.model.element.PieceType;
import com.agh.technology.chess.engine.model.state.BlackState;
import com.agh.technology.chess.engine.model.state.BoardState;
import com.agh.technology.chess.engine.model.state.WhiteState;

import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

public class MoveGenerator {
    private RayAttacks rayAttacks;

    public MoveGenerator(){
        rayAttacks = RayAttacks.getInstance();
    }

    private Long generatePieceMoves(PieceType pieceType, int pieceIndex, BoardState boardState, Color color){
        if(pieceType.equals(PieceType.PAWN)){
            return generatePawnMoves(pieceType, pieceIndex, boardState, color);
        }

        long ts = rayAttacks.getAttackMoves().get(pieceType).get(pieceIndex);
        for(long b = boardState.getOccupied() & rayAttacks.getBlockersAndBeyond().get(pieceType).get(pieceIndex); b != 0L; b = b & (b-1)){
            int sq = Long.numberOfTrailingZeros(b);
            ts = ts & ~rayAttacks.getBehind()[pieceIndex][sq];
        }
        return ts;
    }

    private Long generatePawnMoves(PieceType pieceType, int pieceIndex, BoardState boardState, Color color){
        if(!pieceType.equals(PieceType.PAWN)){
            throw new UnsupportedOperationException();
        }
        long nonCaptureMoves = rayAttacks.getPawnMoves().get(color).get(pieceIndex) & ~boardState.getOccupied();
        long captureMoves = rayAttacks.getPawnCaptureMoves().get(color).get(pieceIndex) & (color.equals(Color.BLACK) ? boardState.getWhiteState().getOccupied() : boardState.getBlackState().getOccupied());

        return captureMoves | nonCaptureMoves;
    }



    public TreeSet<BoardState> generateNextPossibleStates(BoardState boardState, Color color){
        if(color.equals(Color.WHITE)){
            return generateNextPossibleWhiteStates(boardState);
        } else{
            return generateNextPossibleBlackStates(boardState);
        }
    }
    private TreeSet<BoardState> generateNextPossibleBlackStates(BoardState boardState){
        TreeSet<BoardState> possibleStates = new TreeSet<>();
        BlackState blackState = boardState.getBlackState();
        for(PieceType pieceType : PieceType.values()){
            long pieceBitmap = blackState.getPieceBitboard(pieceType);
            for(int index : BoardState.getPositionIndexes(pieceBitmap)){
                long availableMoves = generatePieceMoves(pieceType, index, boardState, Color.BLACK) & ~blackState.getOccupied();
                if(availableMoves != 0L){
                    List<Integer> availableIndexes = BoardState.getPositionIndexes(availableMoves);
                    for(int nextIndex : availableIndexes){
                        long nextIndexBitboard = 1L << nextIndex;
                        if((boardState.getWhiteState().getOccupied() & nextIndexBitboard) == 0L){
                            BoardState nextBoardState = new BoardState(boardState);
                            nextBoardState.getBlackState().setPieceBitboard(pieceType, (pieceBitmap & (~(1L << index))) | nextIndexBitboard);
                            nextBoardState.setBoardEvaluation(ScoreEvaluation.getInstance().evaluateTotalRating(nextBoardState));
                            possibleStates.add(nextBoardState);
                        } else {
                            BoardState nextBoardState = new BoardState(boardState);
                            nextBoardState.getBlackState().setPieceBitboard(pieceType, (pieceBitmap & (~(1L << index))) | nextIndexBitboard);
                            for(PieceType opponentPieceType : PieceType.values()){
                                long opponentPieceBitmap = boardState.getWhiteState().getPieceBitboard(opponentPieceType);
                                if((opponentPieceBitmap & nextIndexBitboard) != 0L){
                                    boardState.getWhiteState().setPieceBitboard(opponentPieceType, opponentPieceBitmap & ~nextIndexBitboard);
                                    break;
                                }
                            }
                            nextBoardState.setBoardEvaluation(ScoreEvaluation.getInstance().evaluateTotalRating(nextBoardState));
                            possibleStates.add(nextBoardState);
                        }
                    }
                }
            }
        }

        return possibleStates;
    }
    private TreeSet<BoardState> generateNextPossibleWhiteStates(BoardState boardState){
        TreeSet<BoardState> possibleStates = new TreeSet<>();
        WhiteState whiteState = boardState.getWhiteState();
        for(PieceType pieceType : PieceType.values()){
            long pieceBitmap = whiteState.getPieceBitboard(pieceType);
            for(int index : BoardState.getPositionIndexes(pieceBitmap)){
                long availableMoves = generatePieceMoves(pieceType, index, boardState, Color.WHITE) & ~whiteState.getOccupied();
                if(availableMoves != 0L){
                    List<Integer> availableIndexes = BoardState.getPositionIndexes(availableMoves);
                    for(int nextIndex : availableIndexes){
                        long nextIndexBitboard = 1L << nextIndex;
                        if((boardState.getBlackState().getOccupied() & nextIndexBitboard) == 0L){
                            BoardState nextBoardState = new BoardState(boardState);
                            nextBoardState.getWhiteState().setPieceBitboard(pieceType, (pieceBitmap & (~(1L << index))) | nextIndexBitboard);
                            nextBoardState.setBoardEvaluation(ScoreEvaluation.getInstance().evaluateTotalRating(nextBoardState));
                            possibleStates.add(nextBoardState);
                        } else {
                            BoardState nextBoardState = new BoardState(boardState);
                            nextBoardState.getWhiteState().setPieceBitboard(pieceType, (pieceBitmap & (~(1L << index))) | nextIndexBitboard);
                            for(PieceType opponentPieceType : PieceType.values()){
                                long opponentPieceBitmap = boardState.getBlackState().getPieceBitboard(opponentPieceType);
                                if((opponentPieceBitmap & nextIndexBitboard) != 0L){
                                    boardState.getBlackState().setPieceBitboard(opponentPieceType, opponentPieceBitmap & ~nextIndexBitboard);
                                    break;
                                }
                            }
                            nextBoardState.setBoardEvaluation(ScoreEvaluation.getInstance().evaluateTotalRating(nextBoardState));
                            possibleStates.add(nextBoardState);
                        }
                    }
                }
            }
        }

        return possibleStates;
    }
}
