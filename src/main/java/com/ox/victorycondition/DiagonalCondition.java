package com.ox.victorycondition;

import com.ox.coordinates.BoardFieldCoordinate;
import com.ox.core.Board;
import com.ox.core.GameConfig;
import com.ox.core.Player;

import java.util.Optional;

public class DiagonalCondition implements VictoryCondition {

    @Override
    public Optional<Player> isThereAVictory(BoardFieldCoordinate lastMove, Board board, Player currentPlayer, GameConfig gameConfig) {

        int lastMoveColumn = getLastMoveColumn(lastMove.getX(), gameConfig);
        int lastMoveRow = getLastMoveRow(lastMove.getX(), gameConfig);
        int start = lastMove.getX();

        int winCondition = gameConfig.getLengthOfCombinationToWin();
        char currentChar = currentPlayer.name().charAt(0);

        int currentPosition = start;
        int currentRow = lastMoveRow;
        int currentColumn = lastMoveColumn;

        int counter = 1;
        while (true) {
            lastMoveColumn += 1;
            lastMoveRow += 1;
            start = lastMoveRow * gameConfig.getBoardColumn() + lastMoveColumn;
            if ((lastMoveRow <= gameConfig.getBoardRow() && lastMoveColumn <= gameConfig.getBoardColumn()) && board.getBoardState().get(start) != null && board.getBoardState().get(start).equals(currentChar)) {
                counter++;
            } else {
                break;
            }
        }
        while (true) {
            currentColumn -= 1;
            currentRow -= 1;
            currentPosition = currentRow * gameConfig.getBoardColumn() + currentColumn;
            if ((lastMoveRow >= 0 && lastMoveColumn > 0) && board.getBoardState().get(currentPosition) != null && board.getBoardState().get(currentPosition).equals(currentChar)) {
                counter++;
            } else {
                break;
            }
        }

        if (counter >= winCondition) {
            return Optional.of(currentPlayer);
        } else {
            return Optional.empty();
        }
    }

    private int getLastMoveRow(int lastMove, GameConfig gameConfig) {
        if (lastMove % gameConfig.getBoardColumn() == 0) {
            return lastMove / gameConfig.getBoardColumn() - 1;
        } else {
            return lastMove / gameConfig.getBoardColumn();
        }
    }

    private int getLastMoveColumn(int lastMove, GameConfig gameConfig) {
        if (lastMove % gameConfig.getBoardColumn() == 0) {
            return lastMove % gameConfig.getBoardColumn() + gameConfig.getBoardColumn();
        } else {
            return lastMove % gameConfig.getBoardColumn();
        }
    }
}
