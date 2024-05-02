package org.example;

public class Word {
    private String polishWord;
    private String englishWord;
    private boolean practiced;
    private int attempt;

    Word(String polishWord, String englishWord, boolean practiced, int attempt) {
        this.polishWord = polishWord;
        this.englishWord = englishWord;
        this.practiced = practiced;
        this.attempt = attempt;

    }

    String getPolishWord() {
        return polishWord;
    }

    String getEnglishWord() {
        return englishWord;
    }

    public int getAttempt() {
        return attempt;
    }

    public boolean isPracticed() {
        return practiced;
    }

    public void setPracticed(boolean practiced) {
        this.practiced = practiced;
    }

    public void setAttempt(int attempt) {
        this.attempt = attempt;
    }

    String getWordByLanguage(Language language) {
        if (language == Language.POLISH) {
            return polishWord;
        } else {
            return englishWord;
        }
    }

    String toCsv() {
        return englishWord + ";" + polishWord + ";" + practiced + ";" + attempt;
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