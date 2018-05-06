package com.ox.language;

import com.ox.language.Language;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class LanguageLoader {

    private Language language;
    private File file;
    private String path;

    public LanguageLoader(Language language, String fileName) {
        this.language = language;
        this.file = new File(fileName);
        this.path=file.getAbsolutePath();
    }

    public void load(){
        try {
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String[] parts = line.split(":");
                this.language.add(parts[0], parts[1]);
            }
        } catch (FileNotFoundException e) {
            System.err.print("Problem with loading the language file");
        }
    }
}
