package com.agh.technology.project.studio.chess.engine.model;

import org.junit.jupiter.api.*;


import static com.agh.technology.project.studio.chess.engine.model.ChessPiece.*;
import static org.junit.jupiter.api.Assertions.*;
class ChessBoardTest {

    private ChessBoard testObject;
    private ChessBoard.ChessBoardBuilder builder;


    private final long blackRookInitialValue = 129;
    private final String blackRookInitialValueBinary = "10000001";
    private ChessPiece[][] blackRookInitial = {
            {BR, NO, NO, NO, NO, NO, NO, BR},
            {NO, NO, NO, NO, NO, NO, NO, NO},
            {NO, NO, NO, NO, NO, NO, NO, NO},
            {NO, NO, NO, NO, NO, NO, NO, NO},
            {NO, NO, NO, NO, NO, NO, NO, NO},
            {NO, NO, NO, NO, NO, NO, NO, NO},
            {NO, NO, NO, NO, NO, NO, NO, NO},
            {NO, NO, NO, NO, NO, NO, NO, NO},
            {NO, NO, NO, NO, NO, NO, NO, NO},
    };

    @BeforeAll
    static void init(){
        System.out.println("Starting ChessBoardTest");
    }

    @BeforeAll
    static void destroy(){
        System.out.println("Ending ChessBoardTest");
    }

    @BeforeEach
    void setup(){
        testObject = ChessBoard.getInstance();
        builder = testObject.new ChessBoardBuilder();
    }


    @Nested
    class PositionTest{

        @Test
        void testBlackRookInitial(){
            builder.buildPositions(blackRookInitial);
            assertNotEquals(testObject.BLACK_ROOK, 0L);
            assertEquals(testObject.BLACK_ROOK, blackRookInitialValue);
            assertEquals(testObject.displayAsBinary(testObject.BLACK_ROOK), blackRookInitialValueBinary);
        }

    }
}
