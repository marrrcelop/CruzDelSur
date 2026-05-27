package com.reservas.CruzDelSur.service;

import com.reservas.CruzDelSur.entity.Reserva;
import com.reservas.CruzDelSur.repository.ReservaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository repository;

    public List<Reserva> listarTodos() {
        return repository.findAll();
    }

    public Optional<Reserva> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    @Transactional
    public Reserva guardar(Reserva reserva) {
        return repository.save(reserva);
    }

    @Transactional
    public Reserva actualizar(Integer id, Reserva reservaActualizada) {
        Reserva reservaExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        reservaExistente.setCliente(reservaActualizada.getCliente());
        reservaExistente.setViaje(reservaActualizada.getViaje());
        reservaExistente.setNumero_asiento(reservaActualizada.getNumero_asiento());
        reservaExistente.setMetodo_pago(reservaActualizada.getMetodo_pago());
        reservaExistente.setEstado_pago(reservaActualizada.getEstado_pago());
        reservaExistente.setEstado_reserva(reservaActualizada.getEstado_reserva());

        // Si no deseas actualizar la fecha de registro en cada modificación,
        // puedes omitir esta línea o validarla.
        reservaExistente.setFecha_registro(reservaActualizada.getFecha_registro());

        return repository.save(reservaExistente);
    }

    @Transactional
    public void eliminar(Integer id) {
        Reserva reserva = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
        repository.delete(reserva);
    }
}
