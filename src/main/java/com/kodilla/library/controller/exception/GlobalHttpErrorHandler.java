package com.kodilla.library.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler {

    @ExceptionHandler(ReaderNotFoundException.class)
    public ResponseEntity<Object> handleReaderNotFoundException(ReaderNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TitleNotFoundException.class)
    public ResponseEntity<Object> handleReaderNotFoundException(TitleNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CopyNotFoundException.class)
    public ResponseEntity<Object> handleCopyNotFoundException(CopyNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RentNotFoundException.class)
    public ResponseEntity<Object> handleRentNotFoundException(RentNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CopyNotInCirculationException.class)
    public ResponseEntity<Object> handleCopyNotInCirculationException(CopyNotInCirculationException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
