package com.ox.core;

import com.ox.victorycondition.ColumnCondition;
import com.ox.victorycondition.DiagonalCondition;
import com.ox.victorycondition.RowCondition;
import com.ox.coordinates.BoardFieldCoordinate;
import com.ox.victorycondition.VictoryCondition;

import java.util.*;

public class VictoryChecker {

    private RowCondition rowCondition=new RowCondition();
    private ColumnCondition columnCondition=new ColumnCondition();
    private DiagonalCondition diagonalCondition=new DiagonalCondition();
    private List<VictoryCondition> conditions;
    private Optional<Player> potentialWinner;

    public VictoryChecker() {
        conditions= Arrays.asList(rowCondition,columnCondition);
    }

    public Optional<Player> isThereAWinner(BoardFieldCoordinate lastMove, NewBoard board, Player currentPlayer, GameConfig gameConfig) {
        for (VictoryCondition condition: conditions) {
            potentialWinner=condition.isThereAVictory(lastMove,board,currentPlayer,gameConfig);
            if(potentialWinner.isPresent()){
                return potentialWinner;
            }
        }
        return potentialWinner;
    }
}
