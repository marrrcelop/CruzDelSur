package com.reservas.CruzDelSur.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "viajes")
@Data
public class Viajes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_viaje;
    private String origen;
    private String destino;
    private Date fecha;
    private Time hora;
    private String tipo_servicio;
    private Double precio;
    private String estado;
}
