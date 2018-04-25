package com.gamelogic;

import java.util.Scanner;

public class InitialState implements GameState{


    private Player currentPlayer;
    private Board board;

    @Override
    public void start(Scanner reader) {
        System.out.println("Welcome in XO, who shall start?");
        this.currentPlayer = Player.valueOf(reader.nextLine());

        System.out.println("Provide board dimensions x y: ");
        int x = Integer.parseInt(reader.next());
        int y = Integer.parseInt(reader.next());

        System.out.println("Provide k-lenght winning combination ");
        int k = Integer.parseInt(reader.next());

        this.board=new Board(x,y,k);

    }

    @Override
    public GameState nextState() {
        return new PlayState(board);
    }


}
