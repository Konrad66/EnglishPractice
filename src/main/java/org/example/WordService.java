package org.example;

import java.io.*;
import java.util.*;

public class WordService {

    private static final String DEFAULT_CATEGORY = "all";
    private int singleSessionSize;
    private String actualCategory = DEFAULT_CATEGORY;
    private List<Word> words;
    private List<Word> wordsSession;
    private Language typingLanguage = Language.POLISH;
    private WordFileRepository wordFileRepository = new WordFileRepository();

    //w momencie tworzenia Listy musimy przypisać do niej pustą listę inaczej zwróci null
    //pola są inicjowane domyślnymi wartościami. dla typów prymitywnych: 0 lub false. Dla obiktowych null
    //zmienne lokalne nie sa domyslnie inicjowane
    //zmienna zadeklarowana w klasie to pole
    //zmienna zadeklarowana w metodzie to zmienna lokalna

    public WordService() {
        words = wordFileRepository.loadAllWords();
        singleSessionSize = wordFileRepository.loadSessionSize();
        reloadSession();
        if (wordsSession.isEmpty()) {
            changeTypingLanguage(Language.ENGLISH);
        }
    }

    void save(){
        wordFileRepository.save(words);
        wordFileRepository.saveSingleSessionSize(singleSessionSize);
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



//moduł utrwalania w pliku
    //odczyt i zapis
    //ustawienia programu i pytania
//moduł ćwiczenia angielskiego
    //tryb nowych słów i tryb powtórek








//todo space distribution - jak czesto sa przypominane slowa, jakby to mialo dzialac, na bazie tego czy dobrze odpowiedzielismy, zaplanowac schemat powtórki słówek, jeśli dobrze odpowiedziałęś to przypomni np za 3 dni


//jeśli dobrze odpowiedziałeś to przypomni za 3 min a jeśli nie to za 1 min
//3 min / 9 min /


//powtórka słowek
// powtórka - po ilu dniach przypomni
// dla dobrych odpowiedzi
// 1 - 1
// 2 -2
// 3 - 4
// 4 - 7
// 5 - 14
// 6 - 30
// 7 - 60
// 8 - 180
// 9 - 360
// 10 - 720

//dla złych będzie pytał tak długo aż dobrze odpowiesz podczas sesji i jak odpowiemy dobrze to trzymamy się skali wyżej;