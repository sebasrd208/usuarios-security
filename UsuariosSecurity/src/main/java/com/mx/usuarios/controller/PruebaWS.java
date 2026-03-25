package com.mx.usuarios.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.*;

@RestController
public class PruebaWS {

    @GetMapping("/prueba")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> prueba(){
        return ResponseEntity.ok("Este es un mensaje de prueba desde UsuariosWS.");
    }
}
