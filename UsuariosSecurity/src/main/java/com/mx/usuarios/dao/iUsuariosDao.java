package com.mx.usuarios.dao;

import com.mx.usuarios.dominio.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface iUsuariosDao extends JpaRepository<Usuarios, Integer> {

    Optional<Usuarios> findByUsername(String username);
}
