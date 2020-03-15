package com.agh.technology.project.studio.chess.engine;

import com.agh.technology.project.studio.chess.engine.model.Chessboard;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
public class ChessApplication {

    public static void main(String[] args) {
//        SpringApplication.run(ChessApplication.class, args);

        Chessboard.ChessBoardBuilder chessBoardBuilder = new Chessboard.ChessBoardBuilder();
        chessBoardBuilder.initializeBoard();


        Chessboard chessboard = chessBoardBuilder.build();
        chessboard.displayBoardColor();


    }
}
