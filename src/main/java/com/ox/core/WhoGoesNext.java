package com.ox.core;

public class WhoGoesNext {

    private Player nextPlayer;

    public WhoGoesNext(Player startingPlayer) {
        this.nextPlayer = startingPlayer.getOppositePlayer();
    }

    public Player getNextPlayer() {
        return nextPlayer;
    }
}
