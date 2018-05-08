package com.ox.victorycondition;

import com.ox.coordinates.BoardFieldCoordinate;
import com.ox.core.Board;
import com.ox.core.GameConfig;
import com.ox.language.Language;
import com.ox.language.LanguageLoader;
import com.ox.validators.GameConfigValidator;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Optional;
import java.util.Scanner;

import static com.ox.core.Player.X;
import static org.testng.Assert.assertEquals;

public class DiagonalConditionTest {

    private GameConfig gameConfig;
    private DiagonalCondition diagonalCondition;
    private GameConfigValidator gameConfigValidator;
    private String boardSize;
    private String winCombination;
    private InputStream inSize;
    private InputStream inWin;
    String language = "en";
    Language lan = new Language();
    LanguageLoader loader = new LanguageLoader(lan, language);
    private Board board;

    @BeforeMethod
    public void setUp() {
        gameConfig = new GameConfig();
        diagonalCondition = new DiagonalCondition();
        gameConfigValidator = new GameConfigValidator();
        loader.load();

        boardSize = "4 8";
        inSize = new ByteArrayInputStream(boardSize.getBytes());
        System.setIn(inSize);
        gameConfig.setBoardSize(System.out::println, new Scanner(System.in)::nextLine, gameConfigValidator);

        winCombination = "4";
        inWin = new ByteArrayInputStream(winCombination.getBytes());
        System.setIn(inWin);
        gameConfig.setLengthOfCombinationToWin(System.out::println, new Scanner(System.in)::nextLine, gameConfigValidator);

        board = new Board(gameConfig);
    }

    @DataProvider(name = "victoryInADiagonal")
    public static Object[][] victoryInARow() {

        return new Object[][]{{1, 10, 19, 28}, {2,11,20,29}, {5,14,23,32}, {3,12,21,30}};

    }


    @DataProvider(name = "noVictory")
    public static Object[][] noVictory() {

        return new Object[][]{{1, 3, 2, 10}, {4, 12, 20, 28}, {8, 16, 24, 32}, {1, 10, 19, 28}};

    }

    @Test(dataProvider = "victoryInADiagonal")
    public void isThereAVictory_PlayerXWins(int first, int second, int third, int fourth) {

        board.addMove(new BoardFieldCoordinate(first), X);
        board.addMove(new BoardFieldCoordinate(second), X);
        board.addMove(new BoardFieldCoordinate(third), X);
        board.addMove(new BoardFieldCoordinate(fourth), X);
        board.printBoardState(System.out::println);

        assertEquals(Optional.of(X), diagonalCondition.isThereAVictory(new BoardFieldCoordinate(fourth), board, X, gameConfig));
    }

    @Test
    public void isThereAVictory_CheckIfReturnsEmptyPlayer_IfNoVictoryInADiagonalFound() {

        boardSize = "3 5";
        inSize = new ByteArrayInputStream(boardSize.getBytes());
        System.setIn(inSize);
        gameConfig.setBoardSize(System.out::println, new Scanner(System.in)::nextLine, gameConfigValidator);

        winCombination = "3";
        inWin = new ByteArrayInputStream(winCombination.getBytes());
        System.setIn(inWin);
        gameConfig.setLengthOfCombinationToWin(System.out::println, new Scanner(System.in)::nextLine, gameConfigValidator);

        Board board = new Board(gameConfig);
        board.addMove(new BoardFieldCoordinate(1), X);
        board.addMove(new BoardFieldCoordinate(3), X);
        board.addMove(new BoardFieldCoordinate(9), X);
        board.addMove(new BoardFieldCoordinate(10), X);
        board.addMove(new BoardFieldCoordinate(7), X);
        board.addMove(new BoardFieldCoordinate(13), X);

        assertEquals(Optional.empty(), diagonalCondition.isThereAVictory(new BoardFieldCoordinate(6), board, X, gameConfig));
    }
}