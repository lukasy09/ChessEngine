package com.agh.technology.project.studio.chess.engine.model;


import com.agh.technology.project.studio.chess.engine.model.chessboard.Field;
import com.agh.technology.project.studio.chess.engine.model.enums.Color;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Chessboard {
    private static final List<String> fileIds = Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H"); // files
    private static final List<Integer> rankIds = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8); // ranks
    private static final int dimension = 8; // 8x8 board

    private Map<String, List<Field>> board; // Board map, A => {1, 2, 3, 4, 5 ,6 ,7, 8}, B => {1, 2, 3, 4, 5 ,6 ,7, 8} etc.

    public static class ChessBoardBuilder {

        private Chessboard chessboard;

        Map<String, List<Field>> builderBoard = new TreeMap<>();

        {
            chessboard = new Chessboard();
        }

        public void initializeBoard() {

            fileIds.forEach(file -> {
                List<Field> fileFields = new ArrayList<>(dimension);
                rankIds.forEach(rank -> {
                    fileFields.add(new Field(file, rank, getColorByFieldCoordinates(file, rank)));
                });

                builderBoard.put(file, fileFields);
            });
            chessboard.setBoard(builderBoard);
        }

        Color getColorByFieldCoordinates(String file, int rank) {
            int fileIndex = fileIds.indexOf(file);
            // Case when we have A - 0, C - 2, E - 4, G  - 6,
             if (fileIndex % 2 == 0) {
                if (rank % 2 == 0) {
                    return Color.WHITE;
                }
                return Color.BLACK;

            }
            // Case when we have B - 1, D - 3, F - 5 , H - 7
            if (rank % 2 == 0) {
                return Color.BLACK;
            }
            return Color.WHITE;

        }

        public Chessboard build() {
            return chessboard;
        }
    }

    public Map<String, List<Field>> getBoard() {
        return board;
    }

    private void setBoard(Map<String, List<Field>> board) {
        this.board = board;
    }

    public void displayBoardColor(){

        board.entrySet().forEach((file) -> {
            System.out.println(file);
//            System.out.println(fields);
        });
    }
}
