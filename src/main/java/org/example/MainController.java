package org.example;

import java.util.List;

public class MainController {


    private final WordService wordService = new WordService();
    private final Input input = new Input();
    private boolean running = true;

    void startProgram() {

        do {
            printOptions();
            int decision = input.readNumber();
            executeOption(decision);
        } while (running);
    }

    private void printOptions() {
        System.out.println("Witaj w programie do nauki słówek!");
        System.out.println("Co chcesz zrobić?");
        System.out.println("1. Wyświetl wszystkie fiszki");
        System.out.println("2. Poćwicz słówka");
        System.out.println("0. Koniec");
    }

    private void executeOption(int decision) {
        switch (decision) {
            case 0:
                wordService.save();
                System.out.println("Dane na temat dzisiejszej sesji zostały zapamiętane!");
                running = false;
                break;
            case 1:
                List<Word> allWords = wordService.getSessionWords();
                System.out.println("Oto słowa do przećwiczenia: ");
                for (Word word : allWords) {
                    System.out.println(word.getPolishWord() + " - " + word.getEnglishWord());
                }
                break;
            case 2:
                practice();
                break;
            default:
                System.out.println("Zły wybór. Wybierz opcje z listy.");
        }
    }

    private void practice() {
        oneWordPractice();
     /*   if (wordService.isDailySesionOver()) {
            System.out.println("Gratulacje! Wszystkie słowa przećwiczone, przyjdź jutro ćwiczyć kolejne");
        }*/
    }

    private void oneWordPractice() {
      /* docelowa wersja
       PracticeSession practiceSession= wordService.getPracticeSession();
        System.out.println("Jesteś w trybie pisania po: " + practiceSession.getLaguage());
        System.out.println("Zostało słówek: " + practiceSession.getWordsCount());
        Language secondLanguage = practiceSession.getLanguage().getSecond();
        Word word = wordService.getRandomWord();
        System.out.println("Przetłumacz: " + word.getWordByLanguage(secondLanguage) + "?");
        String answer = input.readText();
        boolean correct = wordService.tryAnswer(answer, word);
        if (correct) {
            System.out.println("Dobra odpowiedź!");
        } else {
            System.out.println("Zła odpowiedź!");
        }*/

        if (wordService.getWordsCount() == 0) {
            System.out.println("Przyjdź później, koniec słów");
            return;
        }

        System.out.println("Jesteś w trybie pisania po: " + Language.ENGLISH);
        System.out.println("Zostało słówek: " + wordService.getWordsCount()); // jeśli ostatnio dało 3 i odpowiedziałem dobrze to teraz da 2
        Language secondLanguage = Language.POLISH;
        Word word = wordService.getRandomWord(); //oczekuję że nie zwraca mi tych na które już odpowiedziałem dobrze
        System.out.println("Przetłumacz: " + word.getWordByLanguage(secondLanguage) + "?");
        String answer = input.readText();
        boolean correct = wordService.tryAnswer(answer, word, secondLanguage);
        if (correct) {
            System.out.println("Dobra odpowiedź!");
        } else {
            System.out.println("Zła odpowiedź!");
        }
    }
}