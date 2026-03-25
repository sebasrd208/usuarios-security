package com.mx.usuarios.service;

import java.util.*;
import org.modelmapper.*;
import com.mx.usuarios.dao.*;
import com.mx.usuarios.dto.*;
import com.mx.usuarios.dominio.*;
import com.mx.usuarios.exceptions.*;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.crypto.password.*;

@Service
public class UsuarioService {

    @Autowired
    private iUsuariosDao dao;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioResponseDTO registrar(UsuarioRequestDTO dto){
        Usuarios usuario= mapper.map(dto, Usuarios.class);
        usuario.setPassword(passwordEncoder.encode(dto.getPassword()));

        return mapper.map(dao.save(usuario), UsuarioResponseDTO.class);
    }

    public List<UsuarioResponseDTO> listar(){
        return dao.findAll().stream().map(p -> mapper.map(p, UsuarioResponseDTO.class)).toList();
    }

    public UsuarioResponseDTO editar(int id, UsuarioRequestDTO dto, String passwordActual) {

        Usuarios usuario = dao.findById(id)
                .orElseThrow(() ->
                        new RecursoNoEncontradoException("Usuario no encontrado con id: " + id));

        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {

            if (!passwordEncoder.matches(passwordActual, usuario.getPassword())) {
                throw new PasswordMismatchException("Las contraseñas no coinciden");
            }

            usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
        }

        if (dto.getUsername() != null) {
            usuario.setUsername(dto.getUsername());
        }else{
            throw new RuntimeException("Usuario es obligatorio");
        }

        if (dto.getRol() != null){
            usuario.setRol(dto.getRol());
        }else{
            throw new RuntimeException("Rol es obligatorio");
        }

        return mapper.map(dao.save(usuario), UsuarioResponseDTO.class);
    }

    public Usuarios buscarPorUsername(String username) {
        return dao.findByUsername(username)
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no encontrado"));
    }

}
