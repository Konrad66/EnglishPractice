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

    String getWordByLanguage(Language language){
        if(language == Language.POLISH){
            return polishWord;
        } else {
            return englishWord;
        }
    }

    @Override
    public String toString() {
        return "Word{" +
                "polishWord='" + polishWord + '\'' +
                ", englishWord='" + englishWord + '\'' +
                '}';
    }
}