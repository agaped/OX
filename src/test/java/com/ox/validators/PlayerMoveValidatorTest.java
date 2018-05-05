package com.ox.validators;

import com.ox.coordinates.BoardFieldCoordinate;
import com.ox.core.Board;
import com.ox.core.GameConfig;
import com.ox.core.Player;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;
import java.util.function.Consumer;

import static org.testng.Assert.assertEquals;

public class PlayerMoveValidatorTest {

    private Consumer<String> output;
    private String input;
    private Board board4x3;
    private GameConfig gameConfig;


    @BeforeMethod
    public void setUp() {
        output = System.out::println;
        gameConfig = new GameConfig();
        input = "4 3";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        gameConfig.setBoardSize(output, new Scanner(System.in)::nextLine, new GameConfigValidator());
        board4x3 = new Board(gameConfig);
        board4x3.addMove(new BoardFieldCoordinate(1), Player.X);
        board4x3.addMove(new BoardFieldCoordinate(3), Player.X);
    }

    @Test
    public void validateInput_whenInputMatchesRegex() {
        //given and when
        input = "23";

        //then
        assertEquals(PlayerMoveValidator.validateInput(input, output), true);
    }

    @Test
    public void validateInput_whenInputIsNegative() {
        //given and when
        input = "-23";

        //then
        assertEquals(PlayerMoveValidator.validateInput(input, output), false);
    }

    @Test
    public void validateInput_whenInputContainsLetters() {
        //given and when
        input = "3s";

        //then
        assertEquals(PlayerMoveValidator.validateInput(input, output), false);
    }

    @Test
    public void validateInput_whenInputIsEmpty() {
        //given and when
        input = "";

        //then
        assertEquals(PlayerMoveValidator.validateInput(input, output), false);
    }

    @Test
    public void validateInput_whenInputIsNull() {
        //given and when
        input = null;

        //then
        assertEquals(PlayerMoveValidator.validateInput(input, output), false);
    }

    @Test
    public void validateMoveAccordingToBoardState_whenInputIsCorrect() {
        //given and when

        //then
        assertEquals(PlayerMoveValidator.validateMoveAccordingToBoardState(new BoardFieldCoordinate(4), board4x3, output), true);
    }

    @Test
    public void validateMoveAccordingToBoardState_whenFieldIsOccupied() {
        //given and when

        //then
        assertEquals(PlayerMoveValidator.validateMoveAccordingToBoardState(new BoardFieldCoordinate(3), board4x3, output), false);
    }

    @Test
    public void validateMoveAccordingToBoardState_whenInputIsGreaterThanGivenRange() {
        //given and when

        //then
        assertEquals(PlayerMoveValidator.validateMoveAccordingToBoardState(new BoardFieldCoordinate(24), board4x3, output), false);
    }

    @Test
    public void validateMoveAccordingToBoardState_whenInputIsLowerThanGivenRange() {
        //given and when

        //then
        assertEquals(PlayerMoveValidator.validateMoveAccordingToBoardState(new BoardFieldCoordinate(0), board4x3, output), false);
    }
}