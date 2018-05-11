package com.ox.states;

import com.ox.core.*;
import com.ox.language.Language;
import com.ox.language.LanguageLoader;
import com.ox.validators.GameConfigValidator;
import com.ox.validators.LanguageValidator;
import com.ox.validators.PlayerNameAndSignValidator;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.testng.Assert.assertEquals;

public class InitialStateTest {

    String language = "en";
    Language lan = new Language();
    LanguageLoader loader = new LanguageLoader(lan, language);

    @Test
    public void checkIfMoveToTheNextStateWorksForInitialState_WhenCorrectInputProvided() {
        //given
        loader.load();
        GameState init = new InitialState(new GameConfig(), new ScoreBoard(), new GameConfigValidator(), new PlayerNameAndSignValidator(), new LanguageValidator());
        String input = "W\nX";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        //when
        init = init.moveToTheNextState(new Scanner(System.in)::nextLine, System.out::println);

        //then
        assertEquals(init.getClass(), new PlayState(Player.X,Player.X, new Board(new GameConfig()), new VictoryChecker(), new GameConfig(), new ScoreBoard()).getClass());
    }


}