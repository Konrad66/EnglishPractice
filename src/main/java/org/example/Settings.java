package org.example;

public class Settings {

    static final String DEFAULT_CATEGORY = "all";
    private String actualCategory = DEFAULT_CATEGORY;
    private int singleSessionSize;
    private Language typingLanguage = Language.POLISH;

    public Settings(int singleSessionSize) {
        this.singleSessionSize = singleSessionSize;
    }

    public String getActualCategory() {
        return actualCategory;
    }

    public int getSingleSessionSize() {
        return singleSessionSize;
    }

    Language getTypingLanguage() {
        return typingLanguage;
    }

    public void setActualCategory(String actualCategory) {
        this.actualCategory = actualCategory;
    }

    public void setSingleSessionSize(int singleSessionSize) {
        this.singleSessionSize = singleSessionSize;
    }

    public void setTypingLanguage(Language typingLanguage) {
        this.typingLanguage = typingLanguage;
    }
}
