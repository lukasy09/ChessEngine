package com.agh.technology.chess.engine.evaluation.material;

import com.agh.technology.chess.engine.model.element.PieceType;

@SuppressWarnings("FieldCanBeLocal")
class MaterialValueCalculator {

    private static int KING_TRADITIONAL_MATERIAL_VALUE = 5000;
    private static int QUEEN_TRADITIONAL_MATERIAL_VALUE = 9;
    private static int ROOK_TRADITIONAL_MATERIAL_VALUE = 5;
    private static int BISHOP_TRADITIONAL_MATERIAL_VALUE = 3;
    private static int KNIGHT_TRADITIONAL_MATERIAL_VALUE = 3;
    private static int PAWN_TRADITIONAL_MATERIAL_VALUE = 1;

    private static double KING_LOGIC_FACTOR = 1d;
    private static double QUEEN_LOGIC_FACTOR = 8.8 / 9;
    private static double ROOK_LOGIC_FACTOR = 1.02;
    private static double KNIGHT_LOGIC_FACTOR = 1.06667;
    private static double BISHOP_LOGIC_FACTOR = 1.1;
    private static double PAWN_LOGIC_FACTOR = 1;

    private static int SCALE_FACTOR = 100;


    static double calculatePieceMaterialValue(PieceType pieceType) {

        switch (pieceType) {
            case KING:
                return KING_TRADITIONAL_MATERIAL_VALUE * KING_LOGIC_FACTOR;
            case QUEEN:
                return QUEEN_TRADITIONAL_MATERIAL_VALUE * QUEEN_LOGIC_FACTOR * SCALE_FACTOR;
            case ROOK:
                return ROOK_TRADITIONAL_MATERIAL_VALUE * ROOK_LOGIC_FACTOR * SCALE_FACTOR;
            case BISHOP:
                return BISHOP_TRADITIONAL_MATERIAL_VALUE * BISHOP_LOGIC_FACTOR * SCALE_FACTOR;
            case KNIGHT:
                return KNIGHT_TRADITIONAL_MATERIAL_VALUE * KNIGHT_LOGIC_FACTOR * SCALE_FACTOR;
            case PAWN:
                return PAWN_TRADITIONAL_MATERIAL_VALUE * PAWN_LOGIC_FACTOR * SCALE_FACTOR;
            default:
                System.out.println("Wrong piece type!");
                return 0;
        }
    }
}
