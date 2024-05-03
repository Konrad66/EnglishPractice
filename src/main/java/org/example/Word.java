package org.example;

public class Word {
    private String polishWord;
    private String englishWord;
    private int attempt;
    private int numberOfCorrectAttempts;

    Word(String polishWord, String englishWord, int attempt, int numberOfCorrectAttempts) {
        this.polishWord = polishWord;
        this.englishWord = englishWord;
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
        if(numberOfCorrectAttempts >= 1){
            return true;
        }
        return false;
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
        return englishWord + ";" + polishWord + ";" + attempt + ";" + numberOfCorrectAttempts;
    }

    @Override
    public String toString() {
        return "Word{" +
                "polishWord='" + polishWord + '\'' +
                ", englishWord='" + englishWord + '\'' +
                ", attempt=" + attempt +
                ", numberOfCorrectAttempts=" + numberOfCorrectAttempts +
                '}';
    }
}