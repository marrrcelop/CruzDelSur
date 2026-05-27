package com.reservas.CruzDelSur.entity;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table(name = "clientes")
@Data
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_cliente;
    private String nombres;
    private String apellidos;
    private String correo;
    private String documento;
}

