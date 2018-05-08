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

import static com.ox.core.Player.O;
import static com.ox.core.Player.X;
import static org.testng.Assert.assertEquals;

public class RowConditionTest {

    private GameConfig gameConfig;
    private RowCondition rowCondition;
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
        rowCondition = new RowCondition();
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

    @DataProvider(name = "victoryInARow")
    public static Object[][] victoryInARow() {

        return new Object[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {10, 11, 12, 13}, {21, 22, 23, 24}};

    }

    @DataProvider(name = "victoryNotInARow")
    public static Object[][] victoryNotInARow() {

        return new Object[][]{{1, 3, 2, 4}, {4, 6, 7, 5}, {13, 12, 11, 10}, {24, 21, 23, 22}};

    }

    @DataProvider(name = "noVictory")
    public static Object[][] noVictory() {

        return new Object[][]{{1, 3, 2, 10}, {4, 12, 20, 28}, {8, 16, 24, 32}, {1, 10, 19, 28}};

    }

    @Test(dataProvider = "victoryInARow")
    public void isThereAVictory_PlayerXWins_GivenFieldsAreInARow(int first, int second, int third, int fourth) {

        board.addMove(new BoardFieldCoordinate(first), X);
        board.addMove(new BoardFieldCoordinate(second), X);
        board.addMove(new BoardFieldCoordinate(third), X);
        board.addMove(new BoardFieldCoordinate(fourth), X);
        board.printBoardState(System.out::println);

        assertEquals(Optional.of(X), rowCondition.isThereAVictory(new BoardFieldCoordinate(third), board, X, gameConfig));
    }

    @Test(dataProvider = "victoryNotInARow")
    public void isThereAVictory_PlayerXWins_GivenFieldsNotInARow(int first, int second, int third, int fourth) {

        board = new Board(gameConfig);
        board.addMove(new BoardFieldCoordinate(first), X);
        board.addMove(new BoardFieldCoordinate(second), X);
        board.addMove(new BoardFieldCoordinate(third), X);
        board.addMove(new BoardFieldCoordinate(fourth), X);
        board.printBoardState(System.out::println);

        assertEquals(Optional.of(X), rowCondition.isThereAVictory(new BoardFieldCoordinate(second), board, X, gameConfig));
    }

    @Test(dataProvider = "noVictory")
    public void isThereAVictory_CheckIfReturnsEmptyPlayer_IfNoVictoryInARowFound(int first, int second, int third, int fourth) {

        board = new Board(gameConfig);
        board.addMove(new BoardFieldCoordinate(first), O);
        board.addMove(new BoardFieldCoordinate(second), O);
        board.addMove(new BoardFieldCoordinate(third), O);
        board.addMove(new BoardFieldCoordinate(fourth), O);
        board.printBoardState(System.out::println);

        assertEquals(Optional.empty(), rowCondition.isThereAVictory(new BoardFieldCoordinate(fourth), board, X, gameConfig));
    }
}