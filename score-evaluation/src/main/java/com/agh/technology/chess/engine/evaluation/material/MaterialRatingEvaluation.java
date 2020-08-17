package com.agh.technology.chess.engine.evaluation.material;

import com.agh.technology.chess.engine.model.element.PieceType;
import com.agh.technology.chess.engine.model.state.BoardState;

import static com.agh.technology.chess.engine.evaluation.material.MaterialValueCalculator.calculatePieceMaterialValue;

public class MaterialRatingEvaluation {


    public int evaluateTotalRating(BoardState boardState) {
        int positionScore = 0;
        positionScore += evaluateMaterialRating(boardState);

        return positionScore;
    }


    private int evaluateMaterialRating(BoardState boardState) {
        int materialScoreAccumulator = 0;

        materialScoreAccumulator += accumulateMaterialRatingForPieceType(boardState.getWhiteState().getKing(), PieceType.KING);
        materialScoreAccumulator += accumulateMaterialRatingForPieceType(boardState.getWhiteState().getQueen(), PieceType.QUEEN);
        materialScoreAccumulator += accumulateMaterialRatingForPieceType(boardState.getWhiteState().getRook(), PieceType.ROOK);
        materialScoreAccumulator += accumulateMaterialRatingForPieceType(boardState.getWhiteState().getBishop(), PieceType.BISHOP);
        materialScoreAccumulator += accumulateMaterialRatingForPieceType(boardState.getWhiteState().getKnight(), PieceType.KNIGHT);
        materialScoreAccumulator += accumulateMaterialRatingForPieceType(boardState.getWhiteState().getPawn(), PieceType.PAWN);

        materialScoreAccumulator += accumulateMaterialRatingForPieceType(boardState.getBlackState().getQueen(), PieceType.QUEEN);
        materialScoreAccumulator += accumulateMaterialRatingForPieceType(boardState.getBlackState().getQueen(), PieceType.QUEEN);
        materialScoreAccumulator += accumulateMaterialRatingForPieceType(boardState.getBlackState().getRook(), PieceType.ROOK);
        materialScoreAccumulator += accumulateMaterialRatingForPieceType(boardState.getBlackState().getBishop(), PieceType.BISHOP);
        materialScoreAccumulator += accumulateMaterialRatingForPieceType(boardState.getBlackState().getKnight(), PieceType.KNIGHT);
        materialScoreAccumulator += accumulateMaterialRatingForPieceType(boardState.getBlackState().getPawn(), PieceType.PAWN);

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
//       MaterialRatingEvaluation evaluation = new MaterialRatingEvaluation();
//        System.out.println(evaluation.calculateMaterialValue(PieceType.QUEEN));
    }
}
