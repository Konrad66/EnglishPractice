package org.example;

public class Word {
    private String polishWord;
    private String englishWord;
    private int attempt;
    private int correctAttemptsPolish;
    private int correctAttemptsEnglish;


    Word(String polishWord, String englishWord, int attempt, int correctAttemptsPolish, int correctAttemptsEnglish) {
        this.polishWord = polishWord;
        this.englishWord = englishWord;
        this.attempt = attempt;
        this.correctAttemptsPolish = correctAttemptsPolish;
        this.correctAttemptsEnglish = correctAttemptsEnglish;

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

    public boolean isPracticed(Language language) {
        int correctAttemptsByLanguage;
        if(language.equals(Language.POLISH)){
            correctAttemptsByLanguage = correctAttemptsPolish;
        } else {
            correctAttemptsByLanguage = correctAttemptsEnglish;
        }

        if(correctAttemptsByLanguage >= 1){
            return true;
        }
        return false;
    }

    public void incrementAttempt() {
        attempt++;
    }

    public void incrementNumberOfCorrectAttempts(Language language) {
        if(language.equals(Language.POLISH)){
            correctAttemptsPolish++;
        } else {
            correctAttemptsEnglish++;
        }
    }

    String getWordByLanguage(Language language) {
        if (language == Language.POLISH) {
            return polishWord;
        } else {
            return englishWord;
        }
    }

    String toCsv() {
        return englishWord + ";" + polishWord + ";" + attempt + ";" + correctAttemptsPolish + ";" + correctAttemptsEnglish;
    }

    @Override
    public String toString() {
        return "Word{" +
                "polishWord='" + polishWord + '\'' +
                ", englishWord='" + englishWord + '\'' +
                ", attempt=" + attempt +
                ", numberOfCorrectAttempts=" + correctAttemptsPolish +
                '}';
    }
}