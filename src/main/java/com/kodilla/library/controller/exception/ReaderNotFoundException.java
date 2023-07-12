package com.kodilla.library.controller.exception;

public class ReaderNotFoundException extends Exception {
    public ReaderNotFoundException() {
        super("Reader with given id does not exist");
    }
}
