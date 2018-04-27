package com.gamelogic.states;

import com.gamelogic.GameConfig;
import com.gamelogic.Player;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class VictoryState implements GameState {

    private Player winner;

    public VictoryState(Player winner) {
        this.winner = winner;
    }


    @Override
    public void beginCurrentState(Consumer<String> output, Supplier<String> userInputProvider) {
        output.accept("Winner is "+winner);
        output.accept("Dou you want to play again? y/n");
    }

    @Override
    public GameState moveToTheNextState(Supplier<String> userInputProvider, Consumer<String> output) {
        if(userInputProvider.get().equals("y")) {
            return new InitialState(new GameConfig());
        }else {
            return new EndState();
        }
    }
}
