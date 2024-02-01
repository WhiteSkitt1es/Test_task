package com.whiteskittles.minesweeper.exceptions;

public class EndGameException extends RuntimeException {
    public EndGameException(String message) {
        super(message);
    }
}
