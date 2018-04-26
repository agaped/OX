package com.gamelogic.states;

import com.gamelogic.GameConfig;
import com.gamelogic.Player;

import java.util.function.Consumer;

public class VictoryState implements GameState {

    private Player winner;

    public VictoryState(Player winner) {
        this.winner = winner;
    }


    @Override
    public void beginCurrentState(Consumer<String> output) {
        output.accept("Winner is "+winner);
        output.accept("Dou you want to play again? y/n");
    }

    @Override
    public GameState moveToTheNextState(String userInput) {
        if(userInput.equals("y")) {
            return new InitialState(new GameConfig());
        }else {
            return new EndState();
        }
    }
}
