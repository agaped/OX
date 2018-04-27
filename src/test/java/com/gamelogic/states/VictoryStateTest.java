//package com.gamelogic.states;
//
//import com.gamelogic.GameConfig;
//import com.gamelogic.Player;
//import org.testng.annotations.Test;
//
//import java.util.function.Consumer;
//
//import static org.testng.Assert.assertEquals;
//
//public class VictoryStateTest {
//
//    @Test
//    public void checkIfMoveToTheNextStateWorksForVictoryState_moveToInitialState(){
//        //given
//        GameState victory=new VictoryState(Player.X);
//
//        //when
//        victory=victory.moveToTheNextState("y", );
//
//        //then
//        assertEquals(victory.getClass(), new InitialState(new GameConfig()).getClass());
//    }
//
//    @Test
//    public void checkIfMoveToTheNextStateWorksForVictoryState_moveToEndState(){
//        //given
//        GameState victory=new VictoryState(Player.X);
//
//        //when
//        victory=victory.moveToTheNextState("n");
//
//        //then
//        assertEquals(victory.getClass(), new EndState().getClass());
//    }
//}
