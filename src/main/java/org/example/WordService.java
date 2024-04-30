package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class WordService {

    private static final String FILE_PATH_WORDS = "words.csv";
    private int singleSessionSize = 4;
    private List<Word> words;
    private List<Word> wordsSession;

    //w momencie tworzenia Listy musimy przypisać do niej pustą listę inaczej zwróci null
    //pola są inicjowane domyślnymi wartościami. dla typów prymitywnych: 0 lub false. Dla obiktowych null
    //zmienne lokalne nie sa domyslnie inicjowane
    //zmienna zadeklarowana w klasie to pole
    //zmienna zadeklarowana w metodzie to zmienna lokalna

    public WordService() {
        words = loadAllWords();
        setWordsPerSessionCount(singleSessionSize);

    }

    //załadować listę
    // do drugiej listy tylko tyle ile w single sesion size

    List<Word> loadAllWords() {
        List<Word> words = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(FILE_PATH_WORDS))) {
            while (scanner.hasNextLine()) {
                String text = scanner.nextLine();
                String[] data = text.split(";");
                String englishWord = data[0];
                String polishWord = data[1];
                boolean practiced = Boolean.parseBoolean(data[2]);
                Word word = new Word(polishWord, englishWord, practiced);
                words.add(word);
            }
            System.out.println("Słowa zostały zczytane prawidłowo.");
        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku: " + e);
        }
        return words;
    }

    List<Word> getSessionWords() {
        return wordsSession;
    }

    Word getRandomWord() {
        Random random = new Random();
        int randomIndex;
        randomIndex = random.nextInt(wordsSession.size());
        return wordsSession.get(randomIndex);
    }

    boolean tryAnswer(String answer, Word word, Language typingLanguage) {
        word.setPracticed(true);
        wordsSession.remove(word);
        return answer.equals(word.getWordByLanguage(typingLanguage));
    }

    Language getPracticeSession() {
        return null;
    }

    int getWordsCount() {
        return wordsSession.size();
    }

    int getSingleSessionSize() {
        return singleSessionSize;
    }

    int setWordsPerSessionCount(int newWordsPerSession) {
        singleSessionSize = newWordsPerSession;
        wordsSession = new ArrayList<>();
        for (int i = 0; wordsSession.size() < singleSessionSize && i < words.size(); i++) {
            Word choosenWord = words.get(i);
            if (!choosenWord.isPracticed()) {
                wordsSession.add(choosenWord);
            }
            System.out.println(wordsSession);
        }
        return singleSessionSize;
    }

    void save() {
        try (PrintWriter printWriter = new PrintWriter(FILE_PATH_WORDS)) {
            for (Word word : words) {
                printWriter.println(word.toCsv());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku o nazwie: " + FILE_PATH_WORDS);
        }
    }
}
//różnice między fileWriter a PrintWriter