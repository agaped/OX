package com.gamelogic.states;

import com.gamelogic.Board;
import com.gamelogic.GameConfig;
import com.gamelogic.Player;
import com.gamelogic.VictoryChecker;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.testng.Assert.*;

public class PlayStateTest {

    @BeforeMethod
    public void setUp() {
    }

    @AfterMethod
    public void tearDown() {
    }


    @Test
    public void checkIfMoveToTheNextStateWorksForPlayState_staysAtPlayState(){
        //given
        GameState play=new PlayState(Player.X,new Board(new GameConfig()), new VictoryChecker());

        //when
        play=play.moveToTheNextState(new Scanner(System.in)::nextLine,System.out::println);


        //then
        assertEquals(play.getClass(), new DrawState().getClass());

    }




}