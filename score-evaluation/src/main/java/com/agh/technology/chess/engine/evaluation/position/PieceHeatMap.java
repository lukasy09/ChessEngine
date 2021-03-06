package com.agh.technology.chess.engine.evaluation.position;

public class PieceHeatMap {

    private static final int[][][] PAWN_MAP = {
            {
                    { 0,  0,  0,  0,  0,  0,  0,  0 },
                    { 5, 10, 10,-20,-20, 10, 10,  5},
                    { 5, -5,-10,  0,  0,-10, -5,  5},
                    { 0,  0,  0, 20, 20,  0,  0,  0},
                    { 5,  5, 10, 25, 25, 10,  5,  5},
                    { 10, 10, 20, 30, 30, 20, 10, 10},
                    { 50, 50, 50, 50, 50, 50, 50, 50},
                    { 0,  0,  0,  0,  0,  0,  0,  0 }
            },
            {
                    { 0,  0,  0,  0,  0,  0,  0,  0 },
                    { 50, 50, 50, 50, 50, 50, 50, 50},
                    { 10, 10, 20, 30, 30, 20, 10, 10},
                    { 5,  5, 10, 25, 25, 10,  5,  5},
                    { 0,  0,  0, 20, 20,  0,  0,  0},
                    { 5, -5,-10,  0,  0,-10, -5,  5},
                    { 5, 10, 10,-20,-20, 10, 10,  5},
                    { 0,  0,  0,  0,  0,  0,  0,  0}
    }
    };

    private static final int[][][] KNIGHT_MAP = {
            {
                    { -50,-40,-30,-30,-30,-30,-40,-50 },
                    { -40,-20,  0,  5,  5,  0,-20,-40 },
                    { -30,  5, 10, 15, 15, 10,  5,-30 },
                    { -30,  0, 15, 20, 20, 15,  0,-30 },
                    { -30,  5, 15, 20, 20, 15,  5,-30 },
                    { -30,  0, 10, 15, 15, 10,  0,-30 },
                    { -40,-20,  0,  0,  0,  0,-20,-40 },
                    { -50,-40,-30,-30,-30,-30,-40,-50 }
            },{
                    { -50,-40,-30,-30,-30,-30,-40,-50 },
                    { -40,-20,  0,  0,  0,  0,-20,-40 },
                    { -30,  0, 10, 15, 15, 10,  0,-30 },
                    { -30,  5, 15, 20, 20, 15,  5,-30 },
                    { -30,  0, 15, 20, 20, 15,  0,-30 },
                    { -30,  5, 10, 15, 15, 10,  5,-30 },
                    { -40,-20,  0,  5,  5,  0,-20,-40 },
                    { -50,-40,-30,-30,-30,-30,-40,-50 }
    }
    };

    private static final int[][][] BISHOP_MAP = {
            {
                    { -20,-10,-10,-10,-10,-10,-10,-20 },
                    { -10,  5,  0,  0,  0,  0,  5,-10 },
                    { -10, 10, 10, 10, 10, 10, 10,-10 },
                    { -10,  0, 10, 10, 10, 10,  0,-10 },
                    { -10,  5,  5, 10, 10,  5,  5,-10 },
                    { -10,  0,  5, 10, 10,  5,  0,-10 },
                    { -10,  0,  0,  0,  0,  0,  0,-10 },
                    { -20,-10,-10,-10,-10,-10,-10,-20 }
            },{
                    { -20,-10,-10,-10,-10,-10,-10,-20 },
                    { -10,  0,  0,  0,  0,  0,  0,-10 },
                    { -10,  0,  5, 10, 10,  5,  0,-10 },
                    { -10,  5,  5, 10, 10,  5,  5,-10 },
                    { -10,  0, 10, 10, 10, 10,  0,-10 },
                    { -10, 10, 10, 10, 10, 10, 10,-10 },
                    { -10,  5,  0,  0,  0,  0,  5,-10 },
                    { -20,-10,-10,-10,-10,-10,-10,-20 }
    }
    };

    private static final int[][][] ROOK_MAP = {
            {
                    { 0,  0,  0,  5,  5,  0,  0,  0 },
                    { -5,  0,  0,  0,  0,  0,  0, -5 },
                    { -5,  0,  0,  0,  0,  0,  0, -5 },
                    { -5,  0,  0,  0,  0,  0,  0, -5 },
                    { -5,  0,  0,  0,  0,  0,  0, -5 },
                    { -5,  0,  0,  0,  0,  0,  0, -5 },
                    { 5, 10, 10, 10, 10, 10, 10,  5 },
                    { 0,  0,  0,  5,  5,  0,  0,  0 }
            },{
                    { 0,  0,  0,  0,  0,  0,  0,  0 },
                    { 5, 10, 10, 10, 10, 10, 10,  5 },
                    { -5,  0,  0,  0,  0,  0,  0, -5 },
                    { -5,  0,  0,  0,  0,  0,  0, -5 },
                    { -5,  0,  0,  0,  0,  0,  0, -5 },
                    { -5,  0,  0,  0,  0,  0,  0, -5 },
                    { -5,  0,  0,  0,  0,  0,  0, -5 },
                    { 0,  0,  0,  5,  5,  0,  0,  0 }
    }
    };

    private static final int[][][] QUEEN_MAP = {
            {
                    { -20,-10,-10, -5, -5,-10,-10,-20 },
                    { -10,  0,  5,  0,  0,  0,  0,-10 },
                    { -10,  5,  5,  5,  5,  5,  0,-10 },
                    { 0,  0,  5,  5,  5,  5,  0, -5 },
                    { -5,  0,  5,  5,  5,  5,  0, -5 },
                    { -10,  0,  5,  5,  5,  5,  0,-10 },
                    { -10,  0,  0,  0,  0,  0,  0,-10 },
                    { -20,-10,-10, -5, -5,-10,-10,-20 }
            },{
                    { -20,-10,-10, -5, -5,-10,-10,-20 },
                    { -10,  0,  0,  0,  0,  0,  0,-10 },
                    { -10,  0,  5,  5,  5,  5,  0,-10 },
                    { -5,  0,  5,  5,  5,  5,  0, -5 },
                    { 0,  0,  5,  5,  5,  5,  0, -5 },
                    { -10,  5,  5,  5,  5,  5,  0,-10 },
                    { -10,  0,  5,  0,  0,  0,  0,-10 },
                    { -20,-10,-10, -5, -5,-10,-10,-20 }
    }
    };

    private static final int[][][] KING_MAP = {
            {
                    { 20, 30, 10,  0,  0, 10, 30, 20 },
                    { 20, 20,  0,  0,  0,  0, 20, 20 },
                    { -10,-20,-20,-20,-20,-20,-20,-10 },
                    { -20,-30,-30,-40,-40,-30,-30,-20 },
                    { -30,-40,-40,-50,-50,-40,-40,-30 },
                    { -30,-40,-40,-50,-50,-40,-40,-30 },
                    { -30,-40,-40,-50,-50,-40,-40,-30 },
                    { -30,-40,-40,-50,-50,-40,-40,-30 }
            },{
                    { -30,-40,-40,-50,-50,-40,-40,-30 },
                    { -30,-40,-40,-50,-50,-40,-40,-30 },
                    { -30,-40,-40,-50,-50,-40,-40,-30 },
                    { -30,-40,-40,-50,-50,-40,-40,-30 },
                    { -20,-30,-30,-40,-40,-30,-30,-20 },
                    { -10,-20,-20,-20,-20,-20,-20,-10 },
                    { 20, 20,  0,  0,  0,  0, 20, 20 },
                    { 20, 30, 10,  0,  0, 10, 30, 20 }
    }
    };

    public static int[][][] getPawnMap() {
        return PAWN_MAP;
    }

    public static int[][][] getKnightMap() {
        return KNIGHT_MAP;
    }

    public static int[][][] getBishopMap() {
        return BISHOP_MAP;
    }

    public static int[][][] getRookMap() {
        return ROOK_MAP;
    }

    public static int[][][] getQueenMap() {
        return QUEEN_MAP;
    }

    public static int[][][] getKingMap() {
        return KING_MAP;
    }
}
