package com.reservas.CruzDelSur.controller;

import com.reservas.CruzDelSur.entity.Reserva;
import com.reservas.CruzDelSur.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService service;

    @GetMapping
    public List<Reserva> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public Optional<Reserva> buscarPorId(@PathVariable Integer id) {
        return service.buscarPorId(id);
    }

    @PostMapping
    public Reserva guardar(@RequestBody Reserva reserva) {
        return service.guardar(reserva);
    }

    @PutMapping("/{id}")
    public Reserva actualizar(@PathVariable Integer id, @RequestBody Reserva reserva) {
        return service.actualizar(id, reserva);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }
}
