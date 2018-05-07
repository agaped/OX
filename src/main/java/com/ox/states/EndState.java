package com.ox.states;

import com.ox.Game;
import com.ox.language.Language;
import com.ox.core.GameConfig;
import com.ox.core.Player;
import com.ox.core.ScoreBoard;

import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class EndState implements GameState {

    private ScoreBoard scoreBoard;

    public EndState(ScoreBoard scoreBoard) {
        this.scoreBoard = scoreBoard;
    }


    @Override
    public void beginCurrentState(Consumer<String> output, Supplier<String> userInputProvider) {
        output.accept(Language.get("endThanks")+"\n\n"+Language.get("ensStat"));
        for (Player key : this.scoreBoard.getScores().keySet()) {
            output.accept(Language.get("playPlayer") +" "+key + Language.get("endPoints") +" "+ this.scoreBoard.getScores().get(key));
        }
        output.accept("");
        output.accept(Language.get("endAskToContinue"));
    }

    @Override
    public GameState moveToTheNextState(Supplier<String> userInputProvider, Consumer<String> output) {
        String input = userInputProvider.get();
        if (input.equals("y")||input.equals("t")) {
            new Game(new Scanner(System.in)::nextLine, System.out::println, new GameConfig(), new ScoreBoard()).start();
            return null;
        } else if (input.equals("n")) {
            return null;
        } else {
            output.accept(Language.get("endUnknownComment"));
            return null;
        }
    }
}
