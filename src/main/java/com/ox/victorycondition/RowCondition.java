package com.ox.victorycondition;

import com.ox.coordinates.BoardFieldCoordinate;
import com.ox.core.GameConfig;
import com.ox.core.NewBoard;
import com.ox.core.Player;

import java.util.Map;
import java.util.Optional;

public class RowCondition {


    public static Optional<Player> isThereAVictory(BoardFieldCoordinate lastMove, NewBoard board, Player currentPlayer, GameConfig gameConfig) {
        int lastMoveRow=lastMove.getX() / gameConfig.getBoardColumn();

        if (lastMove.getX()%gameConfig.getBoardColumn()==0) {
            lastMoveRow=lastMove.getX() / gameConfig.getBoardColumn()-1;
        }

        int winCondition=gameConfig.getNumberCombinationToWin();
        int counter=0;
        char currentChar=currentPlayer.name().charAt(0);

        for (Map.Entry<Integer,Character> entry : board.getBoardState().entrySet()) {
            if(entry.getKey()/gameConfig.getBoardColumn()==lastMoveRow && entry.getValue().equals(currentChar)){
                ++counter;
                System.out.println("*1 if*Last move row is "+lastMoveRow+", Counter for current char " +currentChar+" is " +counter);
                if(counter==winCondition){
                    return Optional.of(currentPlayer);
                }
            }
            //checks the last cell in a row
            if(entry.getKey()/gameConfig.getBoardColumn()-1==lastMoveRow && entry.getKey()%gameConfig.getBoardColumn()==0 && entry.getValue().equals(currentChar)){
                ++counter;
                System.out.println("*2 if* Last move row is "+lastMoveRow+", Counter for current char " +currentChar+" is " +counter);
                if(counter==winCondition){
                    return Optional.of(currentPlayer);
                }
            }
        }

        return Optional.empty();
    }
}
