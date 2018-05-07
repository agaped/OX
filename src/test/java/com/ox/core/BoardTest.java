package com.ox.core;

import com.ox.coordinates.BoardFieldCoordinate;
import com.ox.language.Language;
import com.ox.language.LanguageLoader;
import com.ox.validators.GameConfigValidator;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;
import java.util.function.Consumer;

import static org.testng.Assert.assertEquals;

public class BoardTest {

    private Consumer<String> output;
    private String input;
    private Board boardFull;
    private Board board3x3;
    private GameConfig gameConfig;
    String language="src/main/java/com/ox/resources/en";
    Language lan=new Language();
    LanguageLoader loader=new LanguageLoader(lan,language);

    @BeforeMethod
    public void setUp() {
        output = System.out::println;
        gameConfig = new GameConfig();
        input = "3 3";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        gameConfig.setBoardSize(output, new Scanner(System.in)::nextLine, new GameConfigValidator());
        boardFull = new Board(gameConfig);
        board3x3 = new Board(gameConfig);
        loader.load();
    }

    @Test
    public void testIsBoardFull_whenBoardFull() {
        //given when
        boardFull.addMove(new BoardFieldCoordinate(1), Player.X);
        boardFull.addMove(new BoardFieldCoordinate(2), Player.X);
        boardFull.addMove(new BoardFieldCoordinate(3), Player.O);
        boardFull.addMove(new BoardFieldCoordinate(4), Player.X);
        boardFull.addMove(new BoardFieldCoordinate(5), Player.O);
        boardFull.addMove(new BoardFieldCoordinate(6), Player.X);
        boardFull.addMove(new BoardFieldCoordinate(7), Player.O);
        boardFull.addMove(new BoardFieldCoordinate(8), Player.X);
        boardFull.addMove(new BoardFieldCoordinate(9), Player.X);
        // then
        assertEquals(boardFull.isBoardFull(), true);

    }

    @Test
    public void testIsBoardFull_whenBoardNotFull() {
        //given when
        board3x3.addMove(new BoardFieldCoordinate(1), Player.X);
        // then
        assertEquals(board3x3.isBoardFull(), false);

    }
}