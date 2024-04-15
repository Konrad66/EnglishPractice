package org.example;

import java.util.List;

public class MainController {

    private final WordService wordService = new WordService();
    private final Input input = new Input();
    private boolean running = true;

    void startProgram() {

        do{
            printOptions();
            int decision = input.readNumber();
            executeOption(decision);
        }while(running);
    }

    private void printOptions() {
        System.out.println("Witaj w programie do nauki słówek!");
        System.out.println("Co chcesz zrobić?");
        System.out.println("1. Wyświetl wszystkie fiszki");
        System.out.println("2. Poćwiczmy pisanie polskiej wersji");
        System.out.println("3. Poćwiczmy pisanie angielskiej wersji");
        System.out.println("0. Koniec");
    }

    private void executeOption(int decision) {
        switch (decision) {
            case 0:
                running = false;
                break;
            case 1:
                List<Word> allWords = wordService.getAllWords();
                System.out.println("Oto słowa do przećwiczenia: ");
                for (Word word : allWords) {
                    System.out.println(word.getPolishWord() + " - " + word.getEnglishWord());
                }
                break;
            case 2:
                practice(Language.POLISH);
               break;
            case 3:
                practice(Language.ENGLISH);
               break;
            default:
                System.out.println("Zły wybór. Wybierz opcje z listy.");
        }
    }

    private void practice(Language typingLanguage) {
        Language secondLanguage = typingLanguage.getSecond();
        Word word = wordService.getRandomWord();
        System.out.println("Przetłumacz: " + word.getWordByLanguage(secondLanguage) + "?");
        String answer = input.readText();
        boolean correct = wordService.tryAnswer(answer, word, typingLanguage);
        if (correct) {
            System.out.println("Dobra odpowiedź!");
        } else {
            System.out.println("Zła odpowiedź!");
        }
    }

}