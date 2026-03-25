package com.mx.usuarios.exceptions;

public class TelefonoNotFoundException extends RuntimeException {
    public TelefonoNotFoundException(String message) {
        super(message);
    }
}
