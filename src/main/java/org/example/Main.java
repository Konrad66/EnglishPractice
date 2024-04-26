package org.example;

public class Main {
    public static void main(String[] args) {
        MainController mainController = new MainController();
        mainController.startProgram();
    }
}

//program na starcie wczytuje do pamięci wszystkie słowa w pliku
//wybiera z nich pierwsze dwa jako sesja
//w trakcie ćwiczeń korzystamy z tych kilku słów reprezentujacych sesje a nie ze wszystkich