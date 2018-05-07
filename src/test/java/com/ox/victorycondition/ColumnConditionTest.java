package com.ox.victorycondition;

import com.ox.coordinates.BoardFieldCoordinate;
import com.ox.core.Board;
import com.ox.core.GameConfig;
import com.ox.language.Language;
import com.ox.language.LanguageLoader;
import com.ox.validators.GameConfigValidator;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Optional;
import java.util.Scanner;

import static com.ox.core.Player.O;
import static com.ox.core.Player.X;
import static org.testng.Assert.assertEquals;

public class ColumnConditionTest {

    private GameConfig gameConfig;
    private ColumnCondition columnCondition;
    private GameConfigValidator gameConfigValidator;
    private String boardSize;
    private String winCombination;
    private InputStream inSize;
    private InputStream inWin;
    String language="src/main/java/com/ox/resources/en";
    Language lan=new Language();
    LanguageLoader loader=new LanguageLoader(lan,language);

    @BeforeMethod
    public void setUp() {
        gameConfig = new GameConfig();
        columnCondition = new ColumnCondition();
        gameConfigValidator = new GameConfigValidator();
        loader.load();
    }

    @Test
    public void isThereAVictory_PlayerXWins_GivenFieldsAreInFirstColumn() {

        boardSize = "3 3";
        inSize = new ByteArrayInputStream(boardSize.getBytes());
        System.setIn(inSize);
        gameConfig.setBoardSize(System.out::println, new Scanner(System.in)::nextLine, gameConfigValidator);

        winCombination = "3";
        inWin = new ByteArrayInputStream(winCombination.getBytes());
        System.setIn(inWin);
        gameConfig.setLengthOfCombinationToWin(System.out::println, new Scanner(System.in)::nextLine, gameConfigValidator);

        Board board = new Board(gameConfig);
        board.addMove(new BoardFieldCoordinate(1), X);
        board.addMove(new BoardFieldCoordinate(4), X);
        board.addMove(new BoardFieldCoordinate(7), X);

        assertEquals(Optional.of(X), columnCondition.isThereAVictory(new BoardFieldCoordinate(7), board, X, gameConfig));
    }

    @Test
    public void isThereAVictory_PlayerXWins_GivenFieldsAreInMiddleColumn() {

        boardSize = "5 5";
        inSize = new ByteArrayInputStream(boardSize.getBytes());
        System.setIn(inSize);
        gameConfig.setBoardSize(System.out::println, new Scanner(System.in)::nextLine, gameConfigValidator);

        winCombination = "4";
        inWin = new ByteArrayInputStream(winCombination.getBytes());
        System.setIn(inWin);
        gameConfig.setLengthOfCombinationToWin(System.out::println, new Scanner(System.in)::nextLine, gameConfigValidator);

        Board board = new Board(gameConfig);
        board.addMove(new BoardFieldCoordinate(3), X);
        board.addMove(new BoardFieldCoordinate(13), X);
        board.addMove(new BoardFieldCoordinate(8), X);
        board.addMove(new BoardFieldCoordinate(18), X);

        assertEquals(Optional.of(X), columnCondition.isThereAVictory(new BoardFieldCoordinate(18), board, X, gameConfig));
    }

    @Test
    public void isThereAVictory_CheckIfReturnsEmptyPlayer_IfNoVictoryInAColumnFound() {

        boardSize = "3 3";
        inSize = new ByteArrayInputStream(boardSize.getBytes());
        System.setIn(inSize);
        gameConfig.setBoardSize(System.out::println, new Scanner(System.in)::nextLine, gameConfigValidator);

        winCombination = "3";
        inWin = new ByteArrayInputStream(winCombination.getBytes());
        System.setIn(inWin);
        gameConfig.setLengthOfCombinationToWin(System.out::println, new Scanner(System.in)::nextLine, gameConfigValidator);

        Board board = new Board(gameConfig);
        board.addMove(new BoardFieldCoordinate(1), X);
        board.addMove(new BoardFieldCoordinate(5), X);
        board.addMove(new BoardFieldCoordinate(10), X);
        board.addMove(new BoardFieldCoordinate(15), O);

        assertEquals(Optional.empty(), columnCondition.isThereAVictory(new BoardFieldCoordinate(15), board, O, gameConfig));
    }
}