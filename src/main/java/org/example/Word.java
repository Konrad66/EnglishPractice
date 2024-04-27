package org.example;

public class Word {
    private String polishWord;
    private String englishWord;
    private boolean practiced;

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

    public boolean isPracticed() {
        return practiced;
    }

    public void setPracticed(boolean practiced) {
        this.practiced = practiced;
    }

    String getWordByLanguage(Language language) {
        if (language == Language.POLISH) {
            return polishWord;
        } else {
            return englishWord;
        }
    }

    String toCsv() {
        return englishWord + ";" + polishWord + ";" + practiced;
    }

    @Override
    public String toString() {
        return "Word{" +
                "polishWord='" + polishWord + '\'' +
                ", englishWord='" + englishWord + '\'' +
                ", practiced=" + practiced +
                '}';
    }
}