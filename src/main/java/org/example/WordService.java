package org.example;

import java.util.ArrayList;
import java.util.List;

public class WordService {

    List<Word> getAllWords(){

        List<Word> words = new ArrayList<>();
        Word potato = new Word("ziemniak", "potato");
        Word tomato = new Word("pomidor", "tomato");
        words.add(tomato);
        words.add(potato);

        return words;
    }
}