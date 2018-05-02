package com.gamelogic.core;

import com.gamelogic.core.Player;

import java.util.Optional;

import static com.gamelogic.core.Player.X;

public class VictoryChecker {

    private int i=0;

    public Optional<Player> isThereAWinner() {

        while(++i<=30){
            return Optional.empty();
        }
        return Optional.of(X);
    }
}
