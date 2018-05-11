package com.ox.validators;

import com.ox.core.Player;
import com.ox.language.Language;
import com.ox.language.LanguageLoader;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Optional;
import java.util.Scanner;
import java.util.function.Consumer;

import static org.testng.Assert.*;

public class PlayerNameAndSignValidatorTest {


    private String input;
    private PlayerNameAndSignValidator playerNameAndSignValidator;
    private Consumer<String> output;
    String language="en";
    Language lan=new Language();
    LanguageLoader loader=new LanguageLoader(lan,language);

    @BeforeMethod
    public void setUp() {
        playerNameAndSignValidator = new PlayerNameAndSignValidator();
        output = System.out::println;
        loader.load();
    }

    @Test
    public void testValidatePlayerName_X() {
        //given
        input = "tomek\nTomek2\nT2omek\nTomek";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        //when
        playerNameAndSignValidator.validatePlayerName(output,new Scanner(System.in)::nextLine, Player.X);
        //then
        assertEquals(Player.X.getPlayerName(),"Tomek");
    }

    @Test
    public void testValidatePlayerName_O() {
        //given
        input = "23Ala\nQwe3_g\nT$Taa\nAla";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        //when
        playerNameAndSignValidator.validatePlayerName(output,new Scanner(System.in)::nextLine, Player.O);
        //then
        assertEquals(Player.O.getPlayerName(),"Ala");
    }


    @Test
    public void testValidateGivenSign() {
        //given and when
        input = "tw\nXX\nO2\nX";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        //then
        assertEquals(playerNameAndSignValidator.validateGivenSign(output,new Scanner(System.in)::nextLine,input), "X");
    }
}