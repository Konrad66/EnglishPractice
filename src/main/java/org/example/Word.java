package org.example;

public class Word {
    private String polishWord;
    private String englishWord;

    public Word(String polishWord, String englishWord) {
        this.polishWord = polishWord;
        this.englishWord = englishWord;
    }

    public String getPolishWord() {
        return polishWord;
    }

    public String getEnglishWord() {
        return englishWord;
    }


}
