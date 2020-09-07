package com.agh.technology.chess.engine.evaluation.material;

import com.agh.technology.chess.engine.model.element.PieceType;
import com.agh.technology.chess.engine.model.state.BlackState;
import com.agh.technology.chess.engine.model.state.BoardState;
import com.agh.technology.chess.engine.model.state.WhiteState;

import static com.agh.technology.chess.engine.evaluation.material.MaterialValueCalculator.calculatePieceMaterialValue;

public class MaterialRatingEvaluation {


    public int evaluateMaterialRating(BoardState boardState) {
        int materialScoreAccumulator = 0;

        for(PieceType pieceType : PieceType.values()){
            materialScoreAccumulator += accumulateMaterialRatingForPieceType(boardState.getWhiteState().getPieceBitboard(pieceType), pieceType);
            materialScoreAccumulator -= accumulateMaterialRatingForPieceType(boardState.getBlackState().getPieceBitboard(pieceType), pieceType);
        }

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
            return (int) calculatePieceMaterialValue(piece);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
