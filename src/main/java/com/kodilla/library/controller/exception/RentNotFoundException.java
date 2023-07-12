package com.kodilla.library.controller.exception;

public class RentNotFoundException extends Exception {
    public RentNotFoundException() {
        super("Rent with given id does not exist");
    }
}
