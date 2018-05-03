package com.ox;

import com.ox.core.GameConfig;
import com.ox.core.ScoreBoard;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        new Game(new Scanner(System.in)::nextLine,
                System.out::println, new GameConfig(), new ScoreBoard()).start();

    }
}
