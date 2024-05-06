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
        System.out.println("Program ustawiony na kategorię: " + wordService.getActualCategory());

        System.out.println("Co chcesz zrobić?");
        System.out.println("1. Wyświetl wszystkie fiszki");
        System.out.println("2. Poćwicz słówka");
        System.out.println("3. Zmienić ilość słów dziennie");
        System.out.println("4. Zmień kategorię");
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
                List<Word> allWords = wordService.getAllWords();
                System.out.println("Oto słowa do przećwiczenia: ");
                for (Word word : allWords) {
                    System.out.println(word.getPolishWord() + " - " + word.getEnglishWord() + " - " + word.getCategory());
                }
                break;
            case 2:
                practice();
                break;
            case 3:
                System.out.println("Ile ustawić słów dziennie?");
                int newWordsPerSession = input.readNumber();
                wordService.changeSessionSize(newWordsPerSession);
                System.out.println("Wielkość dziennej sesji zaktualizowana!");
                break;
            case 4:
                selectCategory();

            default:
                System.out.println("Zły wybór. Wybierz opcje z listy.");
        }
    }

    private void selectCategory() {
        System.out.println("Jaką kategorię chcesz ustawić?");
        List<String> categories = wordService.getAllCategories(); //dosłownie wszystkie możliwe kategorie przećwiczone bądź nie
        for (int i = 0; i < categories.size(); i++) {
            String category = categories.get(i);
            System.out.println(i+1 + ". " + category);
        }
        int userCategoryIndex = input.readNumber() - 1;
        String selectedCategory = categories.get(userCategoryIndex);
        wordService.changeCategory(selectedCategory);
        System.out.println("Ustawiłeś kategorię "+ selectedCategory);
    }

    private void practice() {
        oneWordPractice();
    }

    private void oneWordPractice() {
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