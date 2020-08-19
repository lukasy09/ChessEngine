package com.agh.technology.chess.engine.evaluation.position;

import com.agh.technology.chess.engine.model.element.PieceType;
import com.agh.technology.chess.engine.model.state.BoardState;


public class PositionRatingEvaluation {

    private final int WHITE_INDEX = 1;
    private final int BLACK_INDEX = 0;

    public int evaluatePositionRating(BoardState boardState) {
        int positionRating = 0;

        positionRating += calculatePiecePositionRating(boardState.getWhiteState().getKing(), PieceType.KING, WHITE_INDEX);
        positionRating += calculatePiecePositionRating(boardState.getWhiteState().getQueen(), PieceType.QUEEN, WHITE_INDEX);
        positionRating += calculatePiecePositionRating(boardState.getWhiteState().getRook(), PieceType.ROOK, WHITE_INDEX);
        positionRating += calculatePiecePositionRating(boardState.getWhiteState().getBishop(), PieceType.BISHOP, WHITE_INDEX);
        positionRating += calculatePiecePositionRating(boardState.getWhiteState().getKnight(), PieceType.KNIGHT, WHITE_INDEX);
        positionRating += calculatePiecePositionRating(boardState.getWhiteState().getPawn(), PieceType.PAWN, WHITE_INDEX);


        positionRating += calculatePiecePositionRating(boardState.getBlackState().getKing(), PieceType.KING, BLACK_INDEX);
        positionRating += calculatePiecePositionRating(boardState.getBlackState().getQueen(), PieceType.QUEEN, BLACK_INDEX);
        positionRating += calculatePiecePositionRating(boardState.getBlackState().getRook(), PieceType.ROOK, BLACK_INDEX);
        positionRating += calculatePiecePositionRating(boardState.getBlackState().getBishop(), PieceType.BISHOP, BLACK_INDEX);
        positionRating += calculatePiecePositionRating(boardState.getBlackState().getKnight(), PieceType.KNIGHT, BLACK_INDEX);
        positionRating += calculatePiecePositionRating(boardState.getBlackState().getPawn(), PieceType.PAWN, BLACK_INDEX);

        return positionRating;
    }

    private int calculatePiecePositionRating(long bitboard, PieceType pieceType, int color) {
        int score = 0;
        long piecePosition = bitboard & -bitboard;
        while (piecePosition != 0) {
            int position = Long.numberOfTrailingZeros(piecePosition);
            switch (pieceType) {
                case PAWN:
                    score += PieceHeatMap.getPawnMap()[color][position / 8][position % 8];
                    break;

                case KNIGHT:
                    score += PieceHeatMap.getKnightMap()[color][position / 8][position % 8];
                    break;

                case BISHOP:
                    score += PieceHeatMap.getBishopMap()[color][position / 8][position % 8];
                    break;

                case ROOK:
                    score += PieceHeatMap.getRookMap()[color][position / 8][position % 8];
                    break;

                case QUEEN:
                    score += PieceHeatMap.getQueenMap()[color][position / 8][position % 8];
                    break;

                case KING:
                    score += PieceHeatMap.getKingMap()[color][position / 8][position % 8];
                    break;
            }
            bitboard &= ~piecePosition;
            piecePosition = bitboard & -bitboard;
        }
        return score;
    }
}
