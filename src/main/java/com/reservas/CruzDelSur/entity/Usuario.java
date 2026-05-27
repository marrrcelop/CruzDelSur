package com.reservas.CruzDelSur.entity;
import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table(name = "usuarios") 
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_usuario;
    private String nombres;
    private String correo;
    private String contrasena_hash;
    private String rol;
}
