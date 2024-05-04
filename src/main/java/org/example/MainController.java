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
        System.out.println("Program ustawiony na trenowanie " + wordService.getSingleSessionSize() + " słów dziennie");

        System.out.println("Co chcesz zrobić?");
        System.out.println("1. Wyświetl wszystkie fiszki");
        System.out.println("2. Poćwicz słówka");
        System.out.println("3. Zmienić ilość słów dziennie");
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
            case 3:
                System.out.println("Ile ustawić słów dziennie?");
                int newWordsPerSession = input.readNumber();
                wordService.setWordsPerSessionCount(newWordsPerSession);
                System.out.println("Wielkość dziennej sesji zaktualizowana!");
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
       /*docelowa wersja
       PracticeSession practiceSession= wordService.getPracticeSession();
        System.out.println("Jesteś w trybie pisania po: " + practiceSession.getLaguage());
        System.out.println("Zostało słówek: " + practiceSession.getWordsCount());
        Language typingLanguage = practiceSession.getLanguage().getSecond();
        Word word = wordService.getRandomWord();
        System.out.println("Przetłumacz: " + word.getWordByLanguage(typingLanguage) + "?");
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

        Language typingLanguage = wordService.getTypingLanguage();
        Language wordLanguage = typingLanguage.getSecond();
        System.out.println("Jesteś w trybie pisania po: " + typingLanguage);
        System.out.println("Zostało słówek: " + wordService.getWordsCount()); // jeśli ostatnio dało 3 i odpowiedziałem dobrze to teraz da 2


        Word word = wordService.getRandomWord(); //oczekuję że nie zwraca mi tych na które już odpowiedziałem dobrze
        System.out.println("Przetłumacz: " + word.getWordByLanguage(wordLanguage) + "?");
        String answer = input.readText();
        boolean correct = wordService.tryAnswer(answer, word);
        if (correct) {
            System.out.println("Dobra odpowiedź!");
        } else {
            System.out.println("Zła odpowiedź!");
        }
    }
}