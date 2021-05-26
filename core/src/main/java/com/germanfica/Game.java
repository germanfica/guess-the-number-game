package com.germanfica;

public interface Game {
    int getNumber();
    int getGuess();
    void setGuess(int guess);
    int getSamallest();
    int getBiggest();
    int getRemainingGuesses();
    void reset();
    void check();
    boolean isValidNumberRange();
    boolean isGameWon();
    boolean isGameLost();
}