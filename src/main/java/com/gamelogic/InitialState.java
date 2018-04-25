package com.gamelogic;

import java.util.Scanner;

public class InitialState implements GameState{

    //private Player currentPlayer;
    private Scanner reader;


    public void start(Scanner reader) {
        System.out.println("Welcome in XO, who shall start?");
        Player currentPlayer = Player.valueOf(reader.nextLine());

        System.out.println("Provide board dimensions x y: ");
        int x = Integer.parseInt(reader.next());
        int y = Integer.parseInt(reader.next());

        System.out.println("Provide k-lenght winning combination ");
        int k = Integer.parseInt(reader.next());
        
    }
}
