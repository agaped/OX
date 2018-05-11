package com.ox.validators;

import com.ox.core.Player;
import com.ox.language.Language;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class PlayerNameAndSignValidator {

    public void validatePlayerName(Consumer<String> output, Supplier<String> userInputProvider, Player player) {
        output.accept(Language.get("setPlayerNameFor")+" "+player);
        String input=userInputProvider.get();
        while(!input.matches("[A-Z][a-z]+")) {
            output.accept(Language.get("validatePlayerName"));
            input=userInputProvider.get();
        }
        player.setPlayerName(input);
    }

    public String validateGivenSign(Consumer<String> output, Supplier<String> userInputProvider, String userInput) {
        while (!userInput.matches("[XO]")) {
            output.accept(Language.get("initWrongChar"));
            userInput = userInputProvider.get();
        }
        return userInput;
    }
}
