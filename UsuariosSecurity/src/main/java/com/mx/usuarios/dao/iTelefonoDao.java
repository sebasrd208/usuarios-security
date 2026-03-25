package com.mx.usuarios.dao;

import com.mx.usuarios.dominio.Telefono;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface iTelefonoDao extends JpaRepository<Telefono, Integer> {

    public Telefono findByMarcaIgnoringCase(String telefono);
    public List<Telefono> findByCategoriaIgnoringCase(String categoria);

}
