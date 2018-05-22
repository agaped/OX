package com.ox.validators;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class LanguageValidator {

    public String validateLanguageChosenByPlayer(Supplier<String> userInputProvider, Consumer<String> output) {
        output.accept("Choose language: en/pl");
        String input = userInputProvider.get().trim();
        while (!input.matches("(en|pl)")) {
            output.accept("Wrong input. Choose again");
            input = userInputProvider.get().trim();
        }
        return input;
    }
}
