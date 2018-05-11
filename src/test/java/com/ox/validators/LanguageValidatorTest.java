package com.ox.validators;

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

public class LanguageValidatorTest {

    private String input;
    private LanguageValidator languageValidator;
    private Consumer<String> output;
    String language="en";
    Language lan=new Language();
    LanguageLoader loader=new LanguageLoader(lan,language);

    @BeforeMethod
    public void setUp() {
        languageValidator = new LanguageValidator();
        output = System.out::println;
        loader.load();
    }

    @Test
    public void testValidateLanguageChosenByPlayer_WhenFirstWrongInput_ThenChosenPl() {
        //given and when
        input = "Pl\npl";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        //then
        assertEquals(languageValidator.validateLanguageChosenByPlayer(new Scanner(System.in)::nextLine,output), "pl");
    }

    @Test
    public void testValidateLanguageChosenByPlayer_WhenFirstWrongInput_ThenChosenEn() {
        //given and when
        input = "pll\n2 3pl\nen";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        //then
        assertEquals(languageValidator.validateLanguageChosenByPlayer(new Scanner(System.in)::nextLine,output), "en");
    }
}