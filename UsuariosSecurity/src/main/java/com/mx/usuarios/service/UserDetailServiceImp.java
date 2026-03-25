package com.mx.usuarios.service;

import com.mx.usuarios.dao.*;
import com.mx.usuarios.dominio.*;
import com.mx.usuarios.exceptions.*;
import org.springframework.stereotype.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.userdetails.*;

@Service
public class UserDetailServiceImp implements UserDetailsService {

    @Autowired
    private iUsuariosDao dao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuarios usuario = dao.findByUsername(username)
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no encontrado."));

        return User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .roles(usuario.getRol().name().replace("ROLE_", ""))
                .build();
    }
}
