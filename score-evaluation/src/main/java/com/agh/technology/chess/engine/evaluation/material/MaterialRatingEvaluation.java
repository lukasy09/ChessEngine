package com.agh.technology.chess.engine.evaluation.material;

import com.agh.technology.chess.engine.model.element.PieceType;
import com.agh.technology.chess.engine.model.state.BlackState;
import com.agh.technology.chess.engine.model.state.BoardState;
import com.agh.technology.chess.engine.model.state.WhiteState;

import static com.agh.technology.chess.engine.evaluation.material.MaterialValueCalculator.calculatePieceMaterialValue;

public class MaterialRatingEvaluation {


    public int evaluateMaterialRating(BoardState boardState) {
        int materialScoreAccumulator = 0;

        materialScoreAccumulator += accumulateMaterialRatingForPieceType(boardState.getWhiteState().getKing(), PieceType.KING);
        materialScoreAccumulator += accumulateMaterialRatingForPieceType(boardState.getWhiteState().getQueen(), PieceType.QUEEN);
        materialScoreAccumulator += accumulateMaterialRatingForPieceType(boardState.getWhiteState().getRook(), PieceType.ROOK);
        materialScoreAccumulator += accumulateMaterialRatingForPieceType(boardState.getWhiteState().getBishop(), PieceType.BISHOP);
        materialScoreAccumulator += accumulateMaterialRatingForPieceType(boardState.getWhiteState().getKnight(), PieceType.KNIGHT);
        materialScoreAccumulator += accumulateMaterialRatingForPieceType(boardState.getWhiteState().getPawn(), PieceType.PAWN);

        materialScoreAccumulator -= accumulateMaterialRatingForPieceType(boardState.getBlackState().getKing(), PieceType.KING);
        materialScoreAccumulator -= accumulateMaterialRatingForPieceType(boardState.getBlackState().getQueen(), PieceType.QUEEN);
        materialScoreAccumulator -= accumulateMaterialRatingForPieceType(boardState.getBlackState().getRook(), PieceType.ROOK);
        materialScoreAccumulator -= accumulateMaterialRatingForPieceType(boardState.getBlackState().getBishop(), PieceType.BISHOP);
        materialScoreAccumulator -= accumulateMaterialRatingForPieceType(boardState.getBlackState().getKnight(), PieceType.KNIGHT);
        materialScoreAccumulator -= accumulateMaterialRatingForPieceType(boardState.getBlackState().getPawn(), PieceType.PAWN);

        return materialScoreAccumulator;
    }

    private int accumulateMaterialRatingForPieceType(long pieceTypePosition, PieceType pieceType) {
        int partScore = 0;
        while (pieceTypePosition != 0) {
            pieceTypePosition = pieceTypePosition & (pieceTypePosition - 1);
            partScore += calculateMaterialValue(pieceType);
        }
        return partScore;
    }


    private int calculateMaterialValue(PieceType piece) {
        try {
            switch (piece) {
                case PAWN:
                    return (int) calculatePieceMaterialValue(PieceType.PAWN);

                case KNIGHT:
                    return (int) calculatePieceMaterialValue(PieceType.KNIGHT);

                case BISHOP:
                    return (int) calculatePieceMaterialValue(PieceType.BISHOP);

                case ROOK:
                    return (int) calculatePieceMaterialValue(PieceType.ROOK);

                case QUEEN:
                    return (int) calculatePieceMaterialValue(PieceType.QUEEN);

                case KING:
                    return (int) calculatePieceMaterialValue(PieceType.KING);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] args) {

        BoardState state = new BoardState();
        WhiteState whiteState = new WhiteState();
        whiteState.setKing(2);
        whiteState.setQueen(10);
        whiteState.setRook(1);
        whiteState.setBishop(1);
        whiteState.setKnight(1);
        whiteState.setPawn(1);

        BlackState blackState = new BlackState();
        blackState.setKing(7);
        blackState.setQueen(15);
        blackState.setRook(1);
        blackState.setBishop(1);
        blackState.setKnight(1);
        blackState.setPawn(1);
        state.setWhiteState(whiteState);
        state.setBlackState(blackState);


       MaterialRatingEvaluation evaluation = new MaterialRatingEvaluation();
       int testRating = evaluation.evaluateMaterialRating(state);

        System.out.println(testRating);
        System.out.println(evaluation.accumulateMaterialRatingForPieceType(1, PieceType.KNIGHT));
    }
}
