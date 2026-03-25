package com.mx.usuarios.exceptions;

import java.time.*;
import java.util.*;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    public ResponseEntity<?> buildResponse(String mensaje, HttpStatus status){
        Map<String, Object> error = new HashMap<>();

        error.put("timestamp", LocalDateTime.now());
        error.put("mensaje", mensaje);
        error.put("código", status.value());

        return new ResponseEntity<>(error, status);
    }

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<?> manejoNotFound(RecursoNoEncontradoException ex){
        return buildResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PasswordMismatchException.class)
    public ResponseEntity<?> manejoPasswordMismatchException(PasswordMismatchException ex){
        return buildResponse(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(TelefonoNotFoundException.class)
    public ResponseEntity<?> manejoTelefonoNoEncontrado(TelefonoNotFoundException ex){
        return buildResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TelefonoDuplicadoException.class)
    public ResponseEntity<?> manejoTelefonoDuplicado(TelefonoDuplicadoException ex){
        return buildResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TelefonoDataInvalidException.class)
    public ResponseEntity<?> manejoTelefonoInvalido(TelefonoDataInvalidException ex){
        return buildResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> manejoValidationError(MethodArgumentNotValidException ex){
        Map<String, String> errores = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errores.put(error.getField(), error.getDefaultMessage());
        });

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("codigo", HttpStatus.BAD_REQUEST.value());
        response.put("errores", errores);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> manejoGlobalGeneral(Exception ex){
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("mensaje", "Error generico no manejado.");
        error.put("codigo", HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.put("detalle", ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
