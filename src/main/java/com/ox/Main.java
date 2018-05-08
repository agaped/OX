package com.ox;

import com.ox.core.GameConfig;
import com.ox.core.ScoreBoard;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        if(args.length!=0) {
            StringBuilder sb = new StringBuilder();
            int i = 0;
            while (i < args.length) {
                sb.append(args[i]).append("\n");
                i++;
            }
            String input = sb.toString();
            InputStream in = new ByteArrayInputStream(input.getBytes());
            System.setIn(in);
        }

        new Game(new Scanner(System.in)::nextLine,
                System.out::println, new GameConfig(), new ScoreBoard()).start();


    }
}
