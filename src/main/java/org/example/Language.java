package org.example;

public enum Language {
    POLISH, ENGLISH;

    Language getSecond() {
        if (this.equals(POLISH)) {
            return ENGLISH;
        } else {
            return POLISH;
        }
    }
}
