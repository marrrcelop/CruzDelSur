package com.reservas.CruzDelSur.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;

@Entity
@Table(name = "reservas")
@Data
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_reserva;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_viaje")
    private Viajes viaje;

    private Integer numero_asiento;
    private String metodo_pago;
    private String estado_pago;
    private String estado_reserva;
    private Timestamp fecha_registro;
}
