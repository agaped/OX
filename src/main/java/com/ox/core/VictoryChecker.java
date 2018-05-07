package com.ox.core;

import com.ox.coordinates.BoardFieldCoordinate;
import com.ox.victorycondition.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class VictoryChecker {

    private RowCondition rowCondition = new RowCondition();
    private ColumnCondition columnCondition = new ColumnCondition();
    private DiagonalCondition diagonalCondition = new DiagonalCondition();
    private AntiDiagonalCondition antiDiagonalCondition = new AntiDiagonalCondition();
    private List<VictoryCondition> conditions;
    private Optional<Player> potentialWinner;

    public VictoryChecker() {
        conditions = Arrays.asList(rowCondition, columnCondition, diagonalCondition, antiDiagonalCondition);
    }

    public Optional<Player> isThereAWinner(BoardFieldCoordinate lastMove, Board board, Player currentPlayer, GameConfig gameConfig) {
        for (VictoryCondition condition : conditions) {
            potentialWinner = condition.isThereAVictory(lastMove, board, currentPlayer, gameConfig);
            if (potentialWinner.isPresent()) {
                return potentialWinner;
            }
        }
        return potentialWinner;
    }
}
