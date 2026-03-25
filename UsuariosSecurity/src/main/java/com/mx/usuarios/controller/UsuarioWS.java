package com.mx.usuarios.controller;

import com.mx.usuarios.dto.UsuarioRequestDTO;
import com.mx.usuarios.dto.UsuarioResponseDTO;
import com.mx.usuarios.service.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("usuarios")
public class UsuarioWS {

    @Autowired
    private UsuarioService service;

    @PostMapping("registrar")
    public ResponseEntity<?> registrar(@RequestBody UsuarioRequestDTO dto){
        return ResponseEntity.ok(service.registrar(dto));
    }

    @GetMapping
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(service.listar());
    }

}
