package com.ox.victorycondition;

import com.ox.coordinates.BoardFieldCoordinate;
import com.ox.core.GameConfig;
import com.ox.core.NewBoard;
import com.ox.core.Player;

import java.util.Optional;

public class DiagonalCondition implements VictoryCondition{

    @Override
    public Optional<Player> isThereAVictory(BoardFieldCoordinate lastMove, NewBoard board, Player currentPlayer, GameConfig gameConfig) {



        return Optional.empty();
    }
}
