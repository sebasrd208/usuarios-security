package com.mx.usuarios.controller;

import com.mx.usuarios.dto.TelefonoRequestDTO;
import com.mx.usuarios.service.TelefonoService;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.*;

@RestController
@RequestMapping("telefono")
public class TelefonoWS {

    @Autowired
    private TelefonoService service;

    @GetMapping
    public ResponseEntity<?> listar(){
        return ResponseEntity.ok(service.listar());
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> guardar(@RequestBody TelefonoRequestDTO dto){
        return ResponseEntity.status(201).body(service.guardar(dto));
    }

    @GetMapping("/buscar")
    @PreAuthorize("hasRole('ADMIN', 'USER')")
    public ResponseEntity<?> buscar(@RequestParam int id){
        return ResponseEntity.ok(service.buscar(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> editar(@PathVariable int id, @RequestBody TelefonoRequestDTO dto){
        return ResponseEntity.ok(service.editar(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> eliminar(@PathVariable int id){
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{marca}")
    public ResponseEntity<?> buscarMarca(@PathVariable String marca){
        return ResponseEntity.ok(service.buscarMarca(marca));
    }

    @GetMapping("/categoria")
    public ResponseEntity<?> buscarCategoria(@RequestParam String categoria){
        return ResponseEntity.ok(service.buscarCategoria(categoria));
    }
}
