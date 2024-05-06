package org.example;

import java.io.*;
import java.util.*;

public class WordService {

    private static final String FILE_PATH_WORDS = "words.csv";
    private static final String FILE_PATH_SESSION_SIZE = "sessionSize.bin";
    private static final int DEFAULT_SESSION_SIZE = 5;
    private static final String DEFAULT_CATEGORY = "all";
    private int singleSessionSize;
    private String actualCategory = DEFAULT_CATEGORY;
    private List<Word> words;
    private List<Word> wordsSession;
    private Language typingLanguage = Language.POLISH;

    //w momencie tworzenia Listy musimy przypisać do niej pustą listę inaczej zwróci null
    //pola są inicjowane domyślnymi wartościami. dla typów prymitywnych: 0 lub false. Dla obiktowych null
    //zmienne lokalne nie sa domyslnie inicjowane
    //zmienna zadeklarowana w klasie to pole
    //zmienna zadeklarowana w metodzie to zmienna lokalna

    public WordService() {
        words = loadAllWords();
        singleSessionSize = loadSessionSize();
        reloadSession();
        if (wordsSession.isEmpty()) {
            changeTypingLanguage(Language.ENGLISH);
        }
    }

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

    int reloadSession() {
        wordsSession = new ArrayList<>();
        for (int i = 0; wordsSession.size() < singleSessionSize && i < words.size(); i++) {
            Word choosenWord = words.get(i);
            if (!choosenWord.isPracticed(typingLanguage)) {
                if (actualCategory.equals(DEFAULT_CATEGORY) || choosenWord.getCategory().equals(actualCategory)) {
                    //jeśli aktualna kategoria jest all a jesli nie to kategoria rozpatrywanego słowa jest taka jaka wybvral użytkownik
                    wordsSession.add(choosenWord);
                }
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

    List<Word> getAllWords() {
        return words;
    }

    Word getRandomWord() {
        Random random = new Random();
        int randomIndex = random.nextInt(wordsSession.size());
        return wordsSession.get(randomIndex);
    }

    boolean tryAnswer(String answer, Word word) {
        word.incrementAttempt();
        if (answer.equals(word.getWordByLanguage(getTypingLanguage()))) {
            wordsSession.remove(word);
            word.incrementNumberOfCorrectAttempts(getTypingLanguage());
            return true;
        }
        return false;
    }

    int getWordsCount() {
        return wordsSession.size();
    }

    int getSingleSessionSize() {
        return singleSessionSize;
    }

    Language getTypingLanguage() {
        return typingLanguage;
    }

    String getActualCategory() {
        return actualCategory;
    }

    List<String> getAllCategories() {
        List<String> categories = new ArrayList<>();
        categories.add(DEFAULT_CATEGORY);
        for (Word word : words) {
            if (!categories.contains(word.getCategory())) {
                categories.add(word.getCategory());
            }
        }
        return categories;
    }

    void changeCategory(String selectedCategory) {
        actualCategory = selectedCategory;
        reloadSession();
    }

    void changeSessionSize(int newWordsPerSession) {
        singleSessionSize = newWordsPerSession;
        reloadSession();
    }

    private void changeTypingLanguage(Language selectedLanguage) {
        typingLanguage = selectedLanguage;
        reloadSession();
    }
}


//ukrywanie zrobionych

//todo space distribution - jak czesto sa przypominane slowa, jakby to mialo dzialac, na bazie tego czy dobrze odpowiedzielismy, zaplanowac schemat powtórki słówek, jeśli dobrze odpowiedziałęś to przypomni np za 3 dni

//jeśli dobrze odpowiedziałeś to przypomni za 3 min a jeśli nie to za 1 min
//3 min / 9 min /