package com.gamelogic;

import com.gamelogic.core.GameConfig;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        new Game(new Scanner(System.in)::nextLine,
                System.out::println, new GameConfig()).start();

    }
}
