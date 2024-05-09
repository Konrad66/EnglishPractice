package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordFileRepository {

    private static final String FILE_PATH_WORDS = "words.csv";
    private static final String FILE_PATH_SESSION_SIZE = "sessionSize.bin";
    private static final int DEFAULT_SESSION_SIZE = 5;


    List<Word> loadAllWords() {
        List<Word> words = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(FILE_PATH_WORDS))) {
            while (scanner.hasNextLine()) {
                String text = scanner.nextLine();
                String[] data = text.split(";");
                String englishWord = data[0];
                String polishWord = data[1];
                int attempt = Integer.parseInt(data[2]);
                int correctAttemptsPolish = Integer.parseInt(data[3]);
                int correctAttemptsEnglish = Integer.parseInt(data[4]);
                String category = data[5];
                Word word = new Word(polishWord, englishWord, attempt, correctAttemptsPolish, correctAttemptsEnglish, category);
                words.add(word);
            }
            System.out.println("Słowa zostały zczytane prawidłowo.");
        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku: " + e);
        }
        return words;
    }

    void save(List<Word> words) {
        try (PrintWriter printWriter = new PrintWriter(FILE_PATH_WORDS)) {
            for (Word word : words) {
                printWriter.println(word.toCsv());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Nie znaleziono pliku o nazwie: " + FILE_PATH_WORDS);
        }
    }

    void saveSettings(Settings settings){ //todo refactor
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILE_PATH_SESSION_SIZE))) {
            //FileOutputStream fileOutputStream
            objectOutputStream.writeInt(settings.getSingleSessionSize());
        } catch (IOException e) {
            System.out.println("Błąd z zapisem pliku: " + FILE_PATH_SESSION_SIZE);
        }
    }

    Settings loadSettings() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILE_PATH_SESSION_SIZE))) {
            return new Settings(objectInputStream.readInt());
        } catch (IOException e) {
            System.out.println("Błąd z odczytem pliku: " + FILE_PATH_SESSION_SIZE);
        }
        return new Settings(DEFAULT_SESSION_SIZE);
    }
}