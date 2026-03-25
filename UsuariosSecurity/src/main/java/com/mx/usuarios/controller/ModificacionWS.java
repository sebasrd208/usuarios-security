package com.mx.usuarios.controller;

import com.mx.usuarios.dominio.Usuarios;
import com.mx.usuarios.dto.*;
import com.mx.usuarios.service.*;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.*;
import org.springframework.beans.factory.annotation.*;

@RestController
public class ModificacionWS {

    @Autowired
    private UsuarioService service;

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("modificacion")
    public ResponseEntity<?> editar(@RequestParam int id, @RequestParam String passwordActual, @RequestBody UsuarioRequestDTO dto){
        return ResponseEntity.ok(service.editar(id, dto, passwordActual));
    }
}
