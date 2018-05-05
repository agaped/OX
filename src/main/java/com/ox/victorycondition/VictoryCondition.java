package com.ox.victorycondition;

import com.ox.coordinates.BoardFieldCoordinate;
import com.ox.core.Board;
import com.ox.core.GameConfig;
import com.ox.core.Player;

import java.util.Optional;

public interface VictoryCondition {

    Optional<Player> isThereAVictory(BoardFieldCoordinate lastMove, Board board, Player currentPlayer, GameConfig gameConfig);
}
