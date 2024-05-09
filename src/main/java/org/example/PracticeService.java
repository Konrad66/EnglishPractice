package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PracticeService {

    private List<Word> wordsSession;
    private Settings settings;
    private List<Word> words;

    public PracticeService(Settings settings, List<Word> words) {
        this.words = words;
        this.settings = settings;
        reloadSession();
        if (wordsSession.isEmpty()) {
            settings.setTypingLanguage(Language.ENGLISH);
            reloadSession();
        }
    }

    void reloadSession() {
        wordsSession = new ArrayList<>();
        for (int i = 0; wordsSession.size() < settings.getSingleSessionSize() && i < words.size(); i++) {
            Word choosenWord = words.get(i);
            if (!choosenWord.isPracticed(settings.getTypingLanguage())) {
                if (settings.getActualCategory().equals(Settings.DEFAULT_CATEGORY) || choosenWord.getCategory().equals(settings.getActualCategory())) {
                    //jeśli aktualna kategoria jest all a jesli nie to kategoria rozpatrywanego słowa jest taka jaka wybvral użytkownik
                    wordsSession.add(choosenWord);
                }
            }
        }
    }

    Word getRandomWord() {
        Random random = new Random();
        int randomIndex = random.nextInt(wordsSession.size());
        return wordsSession.get(randomIndex);
    }

    boolean tryAnswer(String answer, Word word) {
        word.incrementAttempt();
        if (answer.equals(word.getWordByLanguage(settings.getTypingLanguage()))) {
            wordsSession.remove(word);
            word.incrementNumberOfCorrectAttempts(settings.getTypingLanguage());
            return true;
        }
        return false;
    }

    int getWordsCount() {
        return wordsSession.size();
    }


}

//wzorzec Singleton