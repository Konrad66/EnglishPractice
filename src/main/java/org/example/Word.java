package org.example;

public class Word {
    private String polishWord;
    private String englishWord;
    private boolean practiced;
    private int attempt;
    private int numberOfCorrectNumbers;

    Word(String polishWord, String englishWord, boolean practiced, int attempt, int numberOfCorrectNumbers) {
        this.polishWord = polishWord;
        this.englishWord = englishWord;
        this.practiced = practiced;
        this.attempt = attempt;
        this.numberOfCorrectNumbers = numberOfCorrectNumbers;
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

    public int getNumberOfCorrectNumbers() {
        return numberOfCorrectNumbers;
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

    public void setNumberOfCorrectNumbers(int numberOfCorrectNumbers) {
        this.numberOfCorrectNumbers = numberOfCorrectNumbers;
    }

    String getWordByLanguage(Language language) {
        if (language == Language.POLISH) {
            return polishWord;
        } else {
            return englishWord;
        }
    }

    String toCsv() {
        return englishWord + ";" + polishWord + ";" + practiced + ";" + attempt + ";" + numberOfCorrectNumbers;
    }

    @Override
    public String toString() {
        return "Word{" +
                "polishWord='" + polishWord + '\'' +
                ", englishWord='" + englishWord + '\'' +
                ", practiced=" + practiced +
                ", attempt=" + attempt +
                ", numberOfCorrectNumbers=" + numberOfCorrectNumbers +
                '}';
    }
}