package org.example;

public class Word {
    private String polishWord;
    private String englishWord;

    Word(String polishWord, String englishWord) {
        this.polishWord = polishWord;
        this.englishWord = englishWord;
    }

    String getPolishWord() {
        return polishWord;
    }

    String getEnglishWord() {
        return englishWord;
    }
}