package com.agh.technology.chess.engine.gui;

import com.agh.technology.chess.engine.model.element.Color;
import com.agh.technology.chess.engine.model.state.BoardState;
import com.agh.technology.chess.engine.move.generation.MoveGenerator;
import com.agh.technology.chess.engine.runner.Algorithm;


import java.util.Scanner;

public class GuiInterface {
    static BoardState boardState;
    static boolean whitemove = true;

    public static void communicateToUCI() {
        Scanner input = new Scanner(System.in);
        while (true) {
            String inputString = input.nextLine();
            if (inputString.equals("uci")) {
                inputUCI();
            } else if (inputString.startsWith("setoption")) {
                setOptions(inputString);
            } else if (inputString.equals("isready")) {
                isReady();
            } else if (inputString.equals("ucinewgame")) {
                restartGame();
            } else if (inputString.startsWith("position")) {
                setPosition(inputString);
            } else if (inputString.startsWith("go")) {
                go();
            } else if (inputString.startsWith("print")) {
                print();
            } else if (inputString.equals("quit")) {
                quit();
                break;
            }
        }
        input.close();
    }

    static void inputUCI() {
        System.out.println("id name ChessEngineProject");
        System.out.println("id author ChessEngineTeam");
        //ChessBoardFactory.initiateChessBoard();
        //MoveIterator.PLAYER = MoveIterator.PLAYER_BLACK;
        System.out.println("uciok");
    }

    static void setOptions(String input) {
        //set options
    }

    static void isReady() {
        MoveGenerator.getInstance();
        System.out.println("readyok");
    }

    static void restartGame() {
        //ChessBoardFactory.initiateChessBoard();
    }

    static void setPosition(String input) {
        whitemove = true;
        if (input.contains("startpos")) {
            boardState = BoardState.getStartingState();
        }
        if (input.contains("moves")) {
            input = input.substring(input.indexOf("moves") + 6);
            while (input.length() > 3) {
                String move = "";
                if (input.length() == 4) {
                    move = input;
                } else {
                    if (input.charAt(4) == ' ') {
                        move = input.substring(0, 4);
                    } else {
                        move = input.substring(0, 5);
                    }
                }
                boardState.applyUCIMove(move, whitemove ? Color.WHITE : Color.BLACK);
                whitemove = !whitemove;
                if (input.contains(" ")) {
                    input = input.substring(input.indexOf(' ') + 1);
                } else {
                    input = input.substring(4);
                }
            }
        }
    }

    public static void go() {
        BoardState bestState;
        long startTime = System.currentTimeMillis();
        bestState = new Algorithm().minmax(boardState,7, whitemove ? Color.WHITE : Color.BLACK);
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println(elapsedTime);
        System.out.println(
                "bestmove " + BoardState.translateIntIndexToUCIIndex(bestState.getMove()[0])
                        + BoardState.translateIntIndexToUCIIndex(bestState.getMove()[1])
                        + (bestState.getPawnPromotion() != null ? bestState.getPawnPromotion() : "")
        );
    }

    static void print() {
        //GenerateBoard.drawboard(WP,WN,WB,WR,WQ,WK,BP,BN,BB,BR,BQ,BK);
    }

    static void quit() {
        System.out.println("It was a good game");
    }




    public static void main(String[] args) {
        communicateToUCI();
    }

}

