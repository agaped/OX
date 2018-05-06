package com.ox;

import com.ox.core.GameConfig;
import com.ox.core.ScoreBoard;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;
import java.util.function.Supplier;

public class Main {

    public static void main(String[] args) {

        new Game(new Scanner(System.in)::nextLine,
                System.out::println, new GameConfig(), new ScoreBoard()).start();

    }
}
