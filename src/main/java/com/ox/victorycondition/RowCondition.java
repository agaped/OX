package com.ox.victorycondition;

import com.ox.coordinates.BoardFieldCoordinate;
import com.ox.core.GameConfig;
import com.ox.core.Board;
import com.ox.core.Player;

import java.util.Optional;

public class RowCondition implements VictoryCondition {

    @Override
    public Optional<Player> isThereAVictory(BoardFieldCoordinate lastMove, Board board, Player currentPlayer, GameConfig gameConfig) {

        int lastMoveRow = getLastMoveRow(lastMove, gameConfig);
        int winCondition = gameConfig.getLengthOfCombinationToWin();
        char currentChar = currentPlayer.name().charAt(0);

        int counter = 1;
        int start = lastMove.getX();
        int endOfLastRow = (lastMoveRow + 1) * gameConfig.getBoardColumn();
        int beginningOfLastRow = endOfLastRow - (gameConfig.getBoardColumn() - 1);

        for (int i = start + 1; i <= endOfLastRow; i++) {
            if (board.getBoardState().get(i) != null && board.getBoardState().get(i).equals(currentChar)) {
                counter++;
            } else {
                break;
            }
        }
        for (int i = start - 1; i >= beginningOfLastRow; i--) {
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

    private int getLastMoveRow(BoardFieldCoordinate lastMove, GameConfig gameConfig) {
        if (lastMove.getX() % gameConfig.getBoardColumn() == 0) {
            return lastMove.getX() / gameConfig.getBoardColumn() - 1;
        } else {
            return lastMove.getX() / gameConfig.getBoardColumn();
        }
    }
}
