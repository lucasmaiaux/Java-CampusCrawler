package fr.campus.dungeoncrawler.exceptions;

public class OutOfBoardException extends Exception {

    public OutOfBoardException() {    }

    public OutOfBoardException(String errorMessage) {
        super(errorMessage);
    }
}
