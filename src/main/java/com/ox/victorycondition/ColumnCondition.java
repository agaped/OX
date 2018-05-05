package com.ox.victorycondition;

import com.ox.coordinates.BoardFieldCoordinate;
import com.ox.core.GameConfig;
import com.ox.core.Board;
import com.ox.core.Player;

import java.util.Optional;

public class ColumnCondition implements VictoryCondition {

    @Override
    public Optional<Player> isThereAVictory(BoardFieldCoordinate lastMove, Board board, Player currentPlayer, GameConfig gameConfig) {

        int lastMoveColumn = getLastMoveColumn(lastMove, gameConfig);
        int winCondition = gameConfig.getLengthOfCombinationToWin();
        char currentChar = currentPlayer.name().charAt(0);

        int counter = 1;
        int start = lastMove.getX();
        int endOfLastColumn = gameConfig.getBoardColumn() * gameConfig.getBoardRow() - (gameConfig.getBoardColumn() - lastMoveColumn);
        int beginningOfLastColumn = lastMoveColumn;

        for (int i = start + gameConfig.getBoardColumn(); i <= endOfLastColumn; i += gameConfig.getBoardColumn()) {
            if (board.getBoardState().get(i) != null && board.getBoardState().get(i).equals(currentChar)) {
                counter++;
            } else {
                break;
            }
        }
        for (int i = start - gameConfig.getBoardColumn(); i >= beginningOfLastColumn; i -= gameConfig.getBoardColumn()) {
            if (board.getBoardState().get(i) != null && board.getBoardState().get(i).equals(currentChar)) {
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

    private int getLastMoveColumn(BoardFieldCoordinate lastMove, GameConfig gameConfig) {
        return lastMove.getX() % gameConfig.getBoardColumn();
    }
}
