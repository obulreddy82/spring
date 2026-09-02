package com.rajtechnologies.springbootdatajpa.exception;

import com.rajtechnologies.springbootdatajpa.model.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GenericException {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorMessage> handleIllegalArgumentException(IllegalArgumentException illegalArgumentException){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorMessage.builder()
                        .status(HttpStatus.BAD_REQUEST.name())
                        .message(illegalArgumentException.getMessage())
                        .path(illegalArgumentException.getStackTrace()[0].getClassName())
                        .build());
    }
}
