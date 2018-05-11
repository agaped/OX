package com.ox.core;

import java.util.function.Consumer;

public class BoardPrinter {

    private Board board;

    public BoardPrinter(Board board) {
        this.board=board;
    }


    public void printBoardState(Consumer<String> output) {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= board.getMaxFieldsNumber(); i++) {

            result.append(" ");

            if (i < 10) {
                result.append("   ");
            }
            if (i >= 10 && i < 100) {
                result.append("  ");
            }
            if (i >= 100 && i < 1000) {
                result.append(" ");
            }
            if (this.board.getBoardState().containsKey(i)) {
                if (i >= 10) {
                    result.append(" ");
                }
                if (i >= 100) {
                    result.append(" ");
                }
                if (i >= 1000) {
                    result.append(" ");
                }
                result.append(this.board.getBoardState().get(i));
            } else {
                result.append(i);
            }
            if (i % this.board.getColumns()== 0) {
                result.append("\n");
            }
        }
        output.accept(result.toString());
    }
}
