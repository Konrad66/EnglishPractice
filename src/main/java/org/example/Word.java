package org.example;

public class Word {
    private String polishWord;
    private String englishWord;
    private boolean practiced;
    private int attempt;
    private int numberOfCorrectAttempts;

    Word(String polishWord, String englishWord, boolean practiced, int attempt, int numberOfCorrectAttempts) {
        this.polishWord = polishWord;
        this.englishWord = englishWord;
        this.practiced = practiced;
        this.attempt = attempt;
        this.numberOfCorrectAttempts = numberOfCorrectAttempts;
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

    public int getNumberOfCorrectAttempts() {
        return numberOfCorrectAttempts;
    }

    public boolean isPracticed() {
        return practiced;
    }

    public void setPracticed(boolean practiced) {
        this.practiced = practiced;
    }

    public void incrementAttempt() {
        attempt++;
    }

    public void incrementNumberOfCorrectAttempts() {
        numberOfCorrectAttempts++;
    }

    String getWordByLanguage(Language language) {
        if (language == Language.POLISH) {
            return polishWord;
        } else {
            return englishWord;
        }
    }

    String toCsv() {
        return englishWord + ";" + polishWord + ";" + practiced + ";" + attempt + ";" + numberOfCorrectAttempts;
    }

    @Override
    public String toString() {
        return "Word{" +
                "polishWord='" + polishWord + '\'' +
                ", englishWord='" + englishWord + '\'' +
                ", practiced=" + practiced +
                ", attempt=" + attempt +
                ", numberOfCorrectNumbers=" + numberOfCorrectAttempts +
                '}';
    }
}