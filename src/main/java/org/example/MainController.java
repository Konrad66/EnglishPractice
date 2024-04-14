package org.example;

import java.util.List;
import java.util.Scanner;

public class MainController {

    private WordService wordService = new WordService();

    void startProgram() {
        printOptions();
        int decision = chooseOption();
        executeOption(decision);
    }

    private void printOptions() {
        System.out.println("Witaj w programie do nauki słówek!");
        System.out.println("Co chcesz zrobić?");
        System.out.println("1. Wyświetl wszystkie fiszki");
        System.out.println("2. Poćwiczmy");
        System.out.println("0. Koniec");
    }

    private int chooseOption() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private void executeOption(int decision) {
        switch (decision) {
            case 1:
                List<Word> allWords = wordService.getAllWords();
                System.out.println("Oto słowa do przećwiczenia: ");
                for (Word word : allWords) {
                    System.out.println(word.getPolishWord() + " - " + word.getEnglishWord());
                }
                break;
        }
    }
}