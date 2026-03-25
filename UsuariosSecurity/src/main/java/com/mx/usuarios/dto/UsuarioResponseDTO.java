package com.mx.usuarios.dto;

import com.mx.usuarios.dominio.Rol;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class UsuarioResponseDTO {

    private int id;
    private String username;
    @Enumerated(EnumType.STRING)
    private Rol rol;

}
