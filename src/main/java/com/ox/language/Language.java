package com.ox.language;

import java.util.HashMap;
import java.util.Map;

public class Language {

    private static Map<String, String> language;

    public Language() {
        this.language = new HashMap<>();
    }

    public void add(String shortage, String message){
        this.language.put(shortage, message);
    }

    public static String get(String shortage){
        return language.get(shortage);
    }

}
