package com.ox.validators;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Consumer;

import static org.testng.Assert.*;

public class GameConfigValidatorTest {

    private String input;
    private GameConfigValidator gameConfigValidator;
    private Consumer<String> output;

    @BeforeMethod
    public void setUp() {
        gameConfigValidator=new GameConfigValidator();
        output=System.out::println;
    }

    @Test
    public void validateBoardSize_whenValuesAreNegative() {
        //given and when
        input = "-1 -2\n-4 5\n4 -3\n3 3";
        InputStream in=new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        //then
        assertEquals(gameConfigValidator.validateBoardSize(output,new Scanner(System.in)::nextLine),Optional.of("3 3"));
    }


    @Test
    public void validateBoardSize_whenValuesOutOfDefaultRange() {
        //given and when
        input = "1 1\n1 2\n2 3\n520 20\n1000 11\n 20000 2000\n3 3";
        InputStream in=new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        //then
        assertEquals(gameConfigValidator.validateBoardSize(output,new Scanner(System.in)::nextLine),Optional.of("3 3"));
    }

    @Test
    public void validateBoardSize_whenValuesAreNotNumerical() {
        //given and when
        input = "1 z\n1 \n21 7&\nq1 41\n   \n)0 3\n3 3";
        InputStream in=new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        //then
        assertEquals(gameConfigValidator.validateBoardSize(output,new Scanner(System.in)::nextLine),Optional.of("3 3"));
    }

    @Test
    public void validateLengthOfCombinationToWin_whenValueIsNegative() {
        //given
        input = "4 4";
        InputStream in=new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        gameConfigValidator.validateBoardSize(output,new Scanner(System.in)::nextLine);
        // when
        input = "-1\n-34\n-5\n-0\n3";
        in=new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        //then
        assertEquals(gameConfigValidator.validateLengthOfCombinationToWin(output,new Scanner(System.in)::nextLine),Optional.of("3"));
    }

    @Test
    public void validateLengthOfCombinationToWin_whenValueOutOfGivenRange() {
        //given
        input = "4 4";
        InputStream in=new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        gameConfigValidator.validateBoardSize(output,new Scanner(System.in)::nextLine);
        // when
        input = "1\n2\n5\n10\n285\n3";
        in=new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        //then
        assertEquals(gameConfigValidator.validateLengthOfCombinationToWin(output,new Scanner(System.in)::nextLine),Optional.of("3"));
    }

    @Test
    public void validateLengthOfCombinationToWin_whenValueIsNotNumerical() {
        //given
        input = "4 4";
        InputStream in=new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        gameConfigValidator.validateBoardSize(output,new Scanner(System.in)::nextLine);
        // when
        input = " \n2w\ntra\n*8\nt&2\n3";
        in=new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        //then
        assertEquals(gameConfigValidator.validateLengthOfCombinationToWin(output,new Scanner(System.in)::nextLine),Optional.of("3"));
    }
}