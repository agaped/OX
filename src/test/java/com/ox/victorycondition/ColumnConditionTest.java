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
    String language = "en";
    Language lan = new Language();
    LanguageLoader loader = new LanguageLoader(lan, language);
    private static Board board;

    @BeforeMethod
    public void setUp() {
        gameConfig = new GameConfig();
        columnCondition = new ColumnCondition();
        gameConfigValidator = new GameConfigValidator();
        loader.load();

        boardSize = "4 6";
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

    @DataProvider(name = "victoryInAColumn")
    public static Object[][] victoryInAColumn() {

        return new Object[][]{{1, 7, 13, 19}, {2, 8, 14, 20}, {3, 9, 15, 21}, {4, 10, 16, 22}, {5, 11, 17, 23}, {6, 12, 18, 24}};

    }

    @DataProvider(name = "noVictory")
    public static Object[][] noVictory() {

        return new Object[][]{{1, 8, 13, 19}, {1, 8, 14, 20}, {1, 2, 15, 21}, {17, 24, 16, 22}, {5, 11, 18, 23}, {1, 2, 3, 4}};

    }

    @Test(dataProvider = "victoryInAColumn")
    public void isThereAVictory_LastSignPutOnTheEdge(int first, int second, int third, int fourth) {
        //given and when
        fillBoard(Player.X, first, second, third, fourth);
        board.printBoardState(System.out::println);
        //then
        assertEquals(columnCondition.isThereAVictory(new BoardFieldCoordinate(first), board, X, gameConfig), Optional.of(X));
    }

    @Test(dataProvider = "victoryInAColumn")
    public void isThereAVictory_LastSignPutInTheMiddle(int first, int second, int third, int fourth) {
        //given and when
        fillBoard(Player.X, first, second, third, fourth);
        board.printBoardState(System.out::println);
        //then
        assertEquals(columnCondition.isThereAVictory(new BoardFieldCoordinate(second), board, X, gameConfig), Optional.of(X));
    }


    @Test(dataProvider = "noVictory")
    public void isThereAVictory_NoVictoryInAColumn(int first, int second, int third, int fourth) {
        //given and when
        fillBoard(Player.X, first, second, third, fourth);
        board.printBoardState(System.out::println);
        //then
        assertEquals(columnCondition.isThereAVictory(new BoardFieldCoordinate(first), board, O, gameConfig), Optional.empty());
    }
}