package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordService {

    private static final String FILE_PATH = "words.csv";

    List<Word> getAllWords() {
        List<Word> words = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(FILE_PATH))) {
            while (scanner.hasNextLine()) {
                String text = scanner.nextLine();
                String[] data = text.split(";");
                String polishWord = data[0];
                String englishWord = data[1];
                Word word = new Word(polishWord, englishWord);
                words.add(word);
            }
            System.out.println("Słowa zostały zczytane prawidłowo.");
        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku: " + e);
        }
        return words;
    }
}