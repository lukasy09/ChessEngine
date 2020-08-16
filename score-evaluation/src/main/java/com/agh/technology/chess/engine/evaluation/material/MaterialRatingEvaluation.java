package com.agh.technology.chess.engine.evaluation.material;

import com.agh.technology.chess.engine.model.element.PieceType;

public class MaterialRatingEvaluation {


    private int computeMaterialValue(PieceType piece) {
        int materialValue = 0;
        switch (piece) {
            case PAWN:
                materialValue = 100;
                break;

            case KNIGHT:
                materialValue = 320;
                break;

            case BISHOP:
                materialValue = 333;
                break;

            case ROOK:
                materialValue = 510;
                break;

            case QUEEN:
                materialValue = 880;
                break;

            case KING:
                materialValue = 5000;
                break;
        }
        return materialValue;
    }





}
