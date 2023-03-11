package com.jamdb.japi.exceptions;

import com.jamdb.japi.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {RuntimeException.class})
    protected ResponseEntity<?> handleConflict(RuntimeException ex, WebRequest request) {
        return new ResponseEntity<>(new ApiResponse(ex.getMessage()), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {UserAuthException.class})
    protected ResponseEntity<?> handleUserAuthException(UserAuthException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse(ex.getMessage()));
    }
}
