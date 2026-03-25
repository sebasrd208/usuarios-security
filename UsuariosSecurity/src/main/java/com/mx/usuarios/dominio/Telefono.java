package com.mx.usuarios.dominio;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "TELEFONOS_BD")
@Data
public class Telefono {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String marca;
    private String categoria;
    private double precio;

}
