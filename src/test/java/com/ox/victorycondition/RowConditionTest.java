package com.ox.victorycondition;

import com.ox.coordinates.BoardFieldCoordinate;
import com.ox.core.Board;
import com.ox.core.GameConfig;
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

public class RowConditionTest {

    private GameConfig gameConfig;
    private RowCondition rowCondition;
    private GameConfigValidator gameConfigValidator;
    private String boardSize;
    private String winCombination;
    private InputStream inSize;
    private InputStream inWin;

    @BeforeMethod
    public void setUp() {
        gameConfig = new GameConfig();
        rowCondition = new RowCondition();
        gameConfigValidator = new GameConfigValidator();
    }

    @Test
    public void isThereAVictory_PlayerXWins_GivenFieldsAreInARow() {

        boardSize = "3 2";
        inSize = new ByteArrayInputStream(boardSize.getBytes());
        System.setIn(inSize);
        gameConfig.setBoardSize(System.out::println, new Scanner(System.in)::nextLine, gameConfigValidator);

        winCombination = "2";
        inWin = new ByteArrayInputStream(winCombination.getBytes());
        System.setIn(inWin);
        gameConfig.setLengthOfCombinationToWin(System.out::println, new Scanner(System.in)::nextLine, gameConfigValidator);

        Board board = new Board(gameConfig);
        board.addMove(new BoardFieldCoordinate(1), X);
        board.addMove(new BoardFieldCoordinate(2), X);

        assertEquals(Optional.of(X), rowCondition.isThereAVictory(new BoardFieldCoordinate(2), board, X, gameConfig));
    }

    @Test
    public void isThereAVictory_PlayerXWins_GivenFieldsNotInARow() {

        boardSize = "3 4";
        inSize = new ByteArrayInputStream(boardSize.getBytes());
        System.setIn(inSize);
        gameConfig.setBoardSize(System.out::println, new Scanner(System.in)::nextLine, gameConfigValidator);

        winCombination = "3";
        inWin = new ByteArrayInputStream(winCombination.getBytes());
        System.setIn(inWin);
        gameConfig.setLengthOfCombinationToWin(System.out::println, new Scanner(System.in)::nextLine, gameConfigValidator);

        Board board = new Board(gameConfig);
        board.addMove(new BoardFieldCoordinate(5), X);
        board.addMove(new BoardFieldCoordinate(7), X);
        board.addMove(new BoardFieldCoordinate(6), X);

        assertEquals(Optional.of(X), rowCondition.isThereAVictory(new BoardFieldCoordinate(6), board, X, gameConfig));
    }

    @Test
    public void isThereAVictory_CheckIfReturnsEmptyPlayer_IfNoVictoryInARowFound() {

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
        board.addMove(new BoardFieldCoordinate(2), O);
        board.addMove(new BoardFieldCoordinate(3), X);
        board.addMove(new BoardFieldCoordinate(5), X);

        assertEquals(Optional.empty(), rowCondition.isThereAVictory(new BoardFieldCoordinate(5), board, X, gameConfig));
    }
}