package com.letscode.starwarsapi.controllers.exceptions;

import com.letscode.starwarsapi.services.exceptions.RebelNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class RebelExceptionHandler {

    @ExceptionHandler(RebelNotFoundException.class)
    public ResponseEntity<StandardError> rebelNotFound(RebelNotFoundException e, HttpServletRequest request) {
        String error = "Rebel not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
