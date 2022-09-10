package com.company.todolist.exception;

import com.company.todolist.dto.ResponseErrorEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler { // consider extending ResponseEntityExceptionHandler

    @ExceptionHandler
    public ResponseEntity<?> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.badRequest().body(
                new ResponseErrorEntity(HttpStatus.NOT_FOUND.toString(), ex.getBindingResult().toString()));
    }

    @ExceptionHandler
    public ResponseEntity<?> handleNullEntityReferenceException(NullEntityReferenceException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.badRequest().body(new ResponseErrorEntity(
                HttpStatus.BAD_REQUEST.toString(), ex.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseErrorEntity(HttpStatus.NOT_FOUND.toString(), ex.getMessage())
        );
    }

    @ExceptionHandler
    public ResponseEntity<?> handleAccessDeniedException(AccessDeniedException ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                new ResponseErrorEntity(HttpStatus.FORBIDDEN.toString(), ex.getMessage()));
    }
    @ExceptionHandler
    public ResponseEntity<?> handleException(Exception ex) {
        log.error(ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ResponseErrorEntity(HttpStatus.INTERNAL_SERVER_ERROR.toString(), ex.getMessage()));
    }
}
