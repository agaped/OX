package com.ox.victorycondition;

import com.ox.coordinates.BoardFieldCoordinate;
import com.ox.core.Board;
import com.ox.core.GameConfig;
import com.ox.core.Player;
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
    private static Board board;

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

    private static void fillBoard(Player player, int... coordinates) {
        for (int coordinate : coordinates) {
            board.addMove(new BoardFieldCoordinate(coordinate), player);
        }
    }

    @DataProvider(name = "victory")
    public static Object[][] victoryInARow() {

        return new Object[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {10, 11, 12, 13}, {20, 21, 22, 23}, {21, 22, 23, 24}, {29, 30, 31, 32}};

    }

    @DataProvider(name = "noVictory")
    public static Object[][] noVictory() {

        return new Object[][]{{1, 3, 2, 10}, {4, 12, 20, 28}, {8, 16, 24, 32}};

    }

    @Test(dataProvider = "victory")
    public void isThereAVictory_LastSignPutOnTheEdge(int first, int second, int third, int fourth) {
        //given and when
        fillBoard(Player.X, first, second, third, fourth);
        board.printBoardState(System.out::println);
        //then
        assertEquals(rowCondition.isThereAVictory(new BoardFieldCoordinate(first), board, X, gameConfig), Optional.of(X));
    }

    @Test(dataProvider = "victory")
    public void isThereAVictory_LastSignPutInTheMiddle(int first, int second, int third, int fourth) {
        //given and when
        fillBoard(Player.X, first, second, third, fourth);
        board.printBoardState(System.out::println);
        //then
        assertEquals(rowCondition.isThereAVictory(new BoardFieldCoordinate(third), board, X, gameConfig), Optional.of(X));
    }

    @Test(dataProvider = "noVictory")
    public void isThereAVictory_CheckIfReturnsEmptyPlayer_IfNoVictoryInARowFound(int first, int second, int third, int fourth) {
        //given and when
        fillBoard(Player.O, first, second, third, fourth);
        board.printBoardState(System.out::println);
        //then
        assertEquals(rowCondition.isThereAVictory(new BoardFieldCoordinate(fourth), board, X, gameConfig), Optional.empty());
    }
}