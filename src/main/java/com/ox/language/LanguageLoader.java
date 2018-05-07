package com.ox.language;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LanguageLoader {

    private Language language;
    private File file;
    private Scanner fileReader;

    public LanguageLoader(Language language, String fileName) {
        this.language = language;
        this.file = new File(fileName);
    }

    public void load() {
        try {
            this.fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String[] parts = line.split(":");
                this.language.add(parts[0], parts[1]);
            }
        } catch (FileNotFoundException e) {
            System.err.print("Problem with loading the language file");
        } finally {
            this.fileReader.close();
        }
    }
}
