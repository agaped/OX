package com.ox.victorycondition;

import com.ox.coordinates.BoardFieldCoordinate;
import com.ox.core.Board;
import com.ox.core.BoardPrinter;
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
    private static Board board;
    private BoardPrinter boardPrinter;

    @BeforeMethod
    public void setUp() {
        gameConfig = new GameConfig();
        diagonalCondition = new DiagonalCondition();
        gameConfigValidator = new GameConfigValidator();
        loader.load();

        boardSize = "4 8";
        inSize = new ByteArrayInputStream(boardSize.getBytes());
        System.setIn(inSize);
        gameConfig.setBoardSize(s -> {}, new Scanner(System.in)::nextLine, gameConfigValidator);

        winCombination = "4";
        inWin = new ByteArrayInputStream(winCombination.getBytes());
        System.setIn(inWin);
        gameConfig.setLengthOfCombinationToWin(s -> {}, new Scanner(System.in)::nextLine, gameConfigValidator);

        board = new Board(gameConfig);
        boardPrinter=new BoardPrinter(board);
    }

    private static void fillBoard(Player player, int... coordinates) {
        for (int coordinate : coordinates) {
            board.addMove(new BoardFieldCoordinate(coordinate), player);
        }
    }

    @DataProvider(name = "victoryInADiagonal")
    public static Object[][] victoryInADiagonal() {

        return new Object[][]{{1, 10, 19, 28, 2, 3, 4}, {2, 11, 20, 29, 21, 22, 23}, {5, 14, 23, 32, 21, 30, 29}, {3, 12, 21, 30, 24, 23, 22}};

    }

    @DataProvider(name = "noVictory")
    public static Object[][] noVictory() {

        return new Object[][]{{1, 19, 12, 10, 3, 26, 17}, {4, 12, 20, 28, 29, 30, 24}, {8, 16, 24, 32, 1, 9, 17}, {1, 18, 19, 28, 8, 9, 25}};

    }

    @Test(dataProvider = "victoryInADiagonal")
    public void isThereAVictory_LastSignPutOnTheEdge(int first, int second, int third, int fourth, int fifth, int sixth, int seventh) {
        //given and when
        fillBoard(Player.X, first, second, third, fourth, fifth, sixth, seventh);
        //then
        assertEquals(diagonalCondition.isThereAVictory(new BoardFieldCoordinate(first), board, X, gameConfig), Optional.of(X));
    }

    @Test(dataProvider = "victoryInADiagonal")
    public void isThereAVictory_LastSignPutInTheMiddle(int first, int second, int third, int fourth, int fifth, int sixth, int seventh) {
        //given and when
        fillBoard(Player.X, first, second, third, fourth, fifth, sixth, seventh);
        //then
        assertEquals(diagonalCondition.isThereAVictory(new BoardFieldCoordinate(second), board, X, gameConfig), Optional.of(X));
    }

    @Test(dataProvider = "noVictory")
    public void isThereAVictory_NoVictoryInADiagonalFound(int first, int second, int third, int fourth, int fifth, int sixth, int seventh) {
        //given and when
        fillBoard(Player.X, first, second, third, fourth, fifth, sixth, seventh);
        //then
        assertEquals(diagonalCondition.isThereAVictory(new BoardFieldCoordinate(first), board, X, gameConfig), Optional.empty());
    }
}