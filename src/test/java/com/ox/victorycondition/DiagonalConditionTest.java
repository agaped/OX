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
    String language="en";
    Language lan=new Language();
    LanguageLoader loader=new LanguageLoader(lan,language);

    @BeforeMethod
    public void setUp() {
        gameConfig = new GameConfig();
        diagonalCondition = new DiagonalCondition();
        gameConfigValidator = new GameConfigValidator();
        loader.load();
    }

    @Test
    public void isThereAVictory_PlayerXWins() {

        boardSize = "3 4";
        inSize = new ByteArrayInputStream(boardSize.getBytes());
        System.setIn(inSize);
        gameConfig.setBoardSize(System.out::println, new Scanner(System.in)::nextLine, gameConfigValidator);

        winCombination = "3";
        inWin = new ByteArrayInputStream(winCombination.getBytes());
        System.setIn(inWin);
        gameConfig.setLengthOfCombinationToWin(System.out::println, new Scanner(System.in)::nextLine, gameConfigValidator);

        Board board = new Board(gameConfig);
        board.addMove(new BoardFieldCoordinate(1), X);
        board.addMove(new BoardFieldCoordinate(6), X);
        board.addMove(new BoardFieldCoordinate(11), X);

        assertEquals(Optional.of(X), diagonalCondition.isThereAVictory(new BoardFieldCoordinate(11), board, X, gameConfig));
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