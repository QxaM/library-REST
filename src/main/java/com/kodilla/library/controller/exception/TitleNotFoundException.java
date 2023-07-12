package com.kodilla.library.controller.exception;

public class TitleNotFoundException extends Exception {
    public TitleNotFoundException() {
        super("Title with given id does not exist");
    }
}
