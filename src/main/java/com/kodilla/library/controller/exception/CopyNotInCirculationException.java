package com.kodilla.library.controller.exception;

public class CopyNotInCirculationException extends Exception{
    public CopyNotInCirculationException() {
        super("Provided rent data contains copy, that is not in circulation");
    }
}
