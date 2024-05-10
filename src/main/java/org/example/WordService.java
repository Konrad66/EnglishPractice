package org.example;

import java.util.*;

public class WordService {


    private List<Word> words;
    private Settings settings;

    private WordFileRepository wordFileRepository;
    private PracticeService practiceService;

    //w momencie tworzenia Listy musimy przypisać do niej pustą listę inaczej zwróci null
    //pola są inicjowane domyślnymi wartościami. dla typów prymitywnych: 0 lub false. Dla obiktowych null
    //zmienne lokalne nie sa domyslnie inicjowane
    //zmienna zadeklarowana w klasie to pole
    //zmienna zadeklarowana w metodzie to zmienna lokalna

    public WordService() {
        wordFileRepository = new WordFileRepository();
        words = wordFileRepository.loadAllWords();
        settings = wordFileRepository.loadSettings();
        practiceService = new PracticeService(settings, words);
    }

    void save() {
        wordFileRepository.save(words);
        wordFileRepository.saveSettings(settings);
    }

    Word getRandomWord() {
        return practiceService.getRandomWord();
    }

    boolean tryAnswer(String answer, Word word) {
        return practiceService.tryAnswer(answer, word);
    }


    void changeSessionSize(int newWordsPerSession) {
        settings.setSingleSessionSize(newWordsPerSession);
        practiceService.reloadSession();
    }

    void changeCategory(String selectedCategory) {
        settings.setActualCategory(selectedCategory);
        practiceService.reloadSession();
    }

    List<Word> getAllWords() {
        return words;
    }

    List<String> getAllCategories() {
        List<String> categories = new ArrayList<>();
        categories.add(Settings.DEFAULT_CATEGORY);
        for (Word word : words) {
            if (!categories.contains(word.getCategory())) {
                categories.add(word.getCategory());
            }
        }
        return categories;
    }

    int getSingleSessionSize() {
        return settings.getSingleSessionSize();
    }

    String getActualCategory() {
        return settings.getActualCategory();
    }

    public int getWordsCount() {
        return practiceService.getWordsCount();
    }

    public Language getTypingLanguage() {
        return settings.getTypingLanguage();
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