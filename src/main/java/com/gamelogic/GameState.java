package com.gamelogic;

import java.util.Scanner;

public interface GameState {

    void start(Scanner reader);

    GameState nextState();
}
