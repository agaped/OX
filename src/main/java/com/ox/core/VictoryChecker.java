package com.ox.core;

import com.ox.victorycondition.RowCondition;
import com.ox.coordinates.BoardFieldCoordinate;

import java.util.Optional;

public class VictoryChecker {

    private RowCondition rowCondition;

    public Optional<Player> isThereAWinner(BoardFieldCoordinate lastMove, NewBoard board, Player currentPlayer, GameConfig gameConfig) {

        return rowCondition.isThereAVictory(lastMove,board,currentPlayer,gameConfig);
    }
}
