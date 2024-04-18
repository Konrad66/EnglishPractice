package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class WordService {

    private static final String FILE_PATH = "words.csv";

    List<Word> getAllWords() {
        List<Word> words = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(FILE_PATH))) {
            while (scanner.hasNextLine()) {
                String text = scanner.nextLine();
                String[] data = text.split(";");
                String englishWord = data[0];
                String polishWord = data[1];
                Word word = new Word(polishWord, englishWord);
                words.add(word);
            }
            System.out.println("Słowa zostały zczytane prawidłowo.");
        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku: " + e);
        }
        return words;
    }

    Word getRandomWord() {
        List<Word> words = getAllWords();
        Random random = new Random();
        int randomIndex = random.nextInt(words.size());
        return words.get(randomIndex);
    }

    boolean tryAnswer(String answer, Word word, Language typingLanguage) {
        return answer.equals(word.getWordByLanguage(typingLanguage));
    }

    Language getPracticeSession(){


        return null;
    }

    int getWordsCount(){

        return 0;
    }
}