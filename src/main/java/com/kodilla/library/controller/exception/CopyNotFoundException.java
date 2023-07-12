package com.kodilla.library.controller.exception;

public class CopyNotFoundException extends Exception {
    public CopyNotFoundException() {
        super("Copy with given id does not exist");
    }
}
