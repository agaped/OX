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

public class AntiDiagonalConditionTest {

    private GameConfig gameConfig;
    private AntiDiagonalCondition AntiDiagonalCondition;
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
        AntiDiagonalCondition = new AntiDiagonalCondition();
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
        board.addMove(new BoardFieldCoordinate(3), X);
        board.addMove(new BoardFieldCoordinate(9), X);
        board.addMove(new BoardFieldCoordinate(6), X);

        assertEquals(Optional.of(X), AntiDiagonalCondition.isThereAVictory(new BoardFieldCoordinate(6), board, X, gameConfig));
    }

    @Test
    public void isThereAVictory_CheckIfReturnsEmptyPlayer_IfNoVictoryInADiagonalFound() {

        boardSize = "3 3";
        inSize = new ByteArrayInputStream(boardSize.getBytes());
        System.setIn(inSize);
        gameConfig.setBoardSize(System.out::println, new Scanner(System.in)::nextLine, gameConfigValidator);

        winCombination = "3";
        inWin = new ByteArrayInputStream(winCombination.getBytes());
        System.setIn(inWin);
        gameConfig.setLengthOfCombinationToWin(System.out::println, new Scanner(System.in)::nextLine, gameConfigValidator);

        Board board = new Board(gameConfig);
        board.addMove(new BoardFieldCoordinate(7), X);
        board.addMove(new BoardFieldCoordinate(5), X);
        board.addMove(new BoardFieldCoordinate(9), X);


        assertEquals(Optional.empty(), AntiDiagonalCondition.isThereAVictory(new BoardFieldCoordinate(5), board, X, gameConfig));

    }
}