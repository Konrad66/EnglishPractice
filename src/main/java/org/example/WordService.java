package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class WordService {

    private static final String FILE_PATH_WORDS = "words.csv";
    private static final String FILE_PATH_SESSION_SIZE = "sessionSize.bin";
    private static final int DEFAULT_SESSION_SIZE = 5;
    private int singleSessionSize;
    private int numberOfCorrectNumbers;
    private List<Word> words;
    private List<Word> wordsSession;

    //w momencie tworzenia Listy musimy przypisać do niej pustą listę inaczej zwróci null
    //pola są inicjowane domyślnymi wartościami. dla typów prymitywnych: 0 lub false. Dla obiktowych null
    //zmienne lokalne nie sa domyslnie inicjowane
    //zmienna zadeklarowana w klasie to pole
    //zmienna zadeklarowana w metodzie to zmienna lokalna

    public WordService() {
        words = loadAllWords();
        setWordsPerSessionCount(loadSessionSize());
    }

    //załadować listę
    // do drugiej listy tylko tyle ile w single sesion size

    //todo
    //zmineić boolean na int z ile razy ćwiczyliśmy
    //int ile razy dobrze odpowiedziałem
    // później może brać pod uwagę te na które źle odpowiadaliśmy

    List<Word> loadAllWords() {
        List<Word> words = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(FILE_PATH_WORDS))) {
            while (scanner.hasNextLine()) {
                String text = scanner.nextLine();
                String[] data = text.split(";");
                String englishWord = data[0];
                String polishWord = data[1];
                boolean practiced = Boolean.parseBoolean(data[2]);
                int attempt = Integer.parseInt(data[3]);
                int numberOfCorrectNumbers = Integer.parseInt(data[4]);
                Word word = new Word(polishWord, englishWord, practiced, attempt, numberOfCorrectNumbers);
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
        int randomIndex = random.nextInt(wordsSession.size());
        return wordsSession.get(randomIndex);
    }

    boolean tryAnswer(String answer, Word word, Language typingLanguage) {
        if (checkAnswer(answer)) {
            word.setPracticed(true);
            wordsSession.remove(word);
            word.setAttempt(+1);
            word.setNumberOfCorrectNumbers(+1);
            return answer.equals(word.getWordByLanguage(typingLanguage));
        } else {
            word.setAttempt(+1);
        }
        return false;
    }

    boolean checkAnswer(String word) {
        return words.contains(word);
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

        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(FILE_PATH_SESSION_SIZE))) {
            //FileOutputStream fileOutputStream
            objectOutputStream.writeInt(singleSessionSize);
        } catch (IOException e) {
            System.out.println("Błąd z zapisem pliku: " + FILE_PATH_SESSION_SIZE);
        }
    }

    int loadSessionSize() {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILE_PATH_SESSION_SIZE))) {
            return objectInputStream.readInt();
        } catch (IOException e) {
            System.out.println("Błąd z odczytem pliku: " + FILE_PATH_SESSION_SIZE);
        }
        return DEFAULT_SESSION_SIZE;
    }

}
//różnice między fileWriter a PrintWriter