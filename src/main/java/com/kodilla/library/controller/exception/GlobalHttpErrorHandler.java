package com.kodilla.library.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler {

    @ExceptionHandler(ReaderNotFoundException.class)
    public ResponseEntity<Object> handleReaderNotFoundException(ReaderNotFoundException exception) {
        return new ResponseEntity<>("Group with given id does not exist", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TitleNotFoundException.class)
    public ResponseEntity<Object> handleReaderNotFoundException(TitleNotFoundException exception) {
        return new ResponseEntity<>("Title with given id does not exist", HttpStatus.NOT_FOUND);
    }
}
