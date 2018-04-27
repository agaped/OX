//package com.gamelogic.states;
//
//import com.gamelogic.Board;
//import com.gamelogic.GameConfig;
//import com.gamelogic.Player;
//import com.gamelogic.VictoryChecker;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//import java.util.Scanner;
//import java.util.function.Consumer;
//import java.util.function.Supplier;
//
//import static org.testng.Assert.*;
//
//public class PlayStateTest {
//
//    @BeforeMethod
//    public void setUp() {
//    }
//
//    @AfterMethod
//    public void tearDown() {
//    }
//
//
//    @Test
//    public void checkIfMoveToTheNextStateWorksForPlayState_staysAtPlayState(){
//        //given
//        GameState play=new PlayState(Player.X,new Board(new GameConfig()), new VictoryChecker());
//        Consumer<String> output=System.out::println;
//        Supplier<String> input=new Scanner(System.in)::nextLine;
//
//        //when
//        play=play.moveToTheNextState("1 1", output);
//
//
//        //then
//        assertEquals(play.getClass(), new PlayState(Player.X,new Board(new GameConfig()), new VictoryChecker()).getClass());
//    }
//
//    @Test
//    public void checkIfMoveToTheNextStateWorksForPlayState_moveToVictoryState(){
//        //given
//        GameState play=new PlayState(Player.X,new Board(new GameConfig()), new VictoryChecker());
//
//        //when
//        play=play.moveToTheNextState("1 1");
//        play=play.moveToTheNextState("1 1");
//        play=play.moveToTheNextState("1 1");
//        play=play.moveToTheNextState("1 1");
//
//        //then
//        assertEquals(play.getClass(), new VictoryState(Player.X).getClass());
//    }
//
//
//
//
//}