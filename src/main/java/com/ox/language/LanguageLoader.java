package com.ox.language;

import com.ox.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LanguageLoader {

    private Language language;
    private String fileName;

    public LanguageLoader(Language language, String fileName) {
        this.language = language;
        this.fileName = fileName;
    }

    public void load() {

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Main.class.getResourceAsStream(fileName)));
            String thisLine;

            while((thisLine=bufferedReader.readLine())!=null) {
                String[] parts = thisLine.split(":");
                language.add(parts[0], parts[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

    }
}
