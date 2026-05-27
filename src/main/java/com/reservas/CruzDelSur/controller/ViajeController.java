package com.reservas.CruzDelSur.controller;
import com.reservas.CruzDelSur.entity.Viajes;
import com.reservas.CruzDelSur.service.ViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/viajes")
public class ViajeController {
    @Autowired
    private ViajeService service;
    @GetMapping
    public List<Viajes> listar(){
        return service.listarTodos();
    }
    @GetMapping("/{id}")
    public Optional<Viajes> buscarPorId(@PathVariable Integer id){
        return service.buscarPorId(id);
    }
    @GetMapping("/edad/{edad}")
    public List<Viajes> buscarPorPrecio(@PathVariable Double precio){
        return service.buscarPorPrecio(precio);
    }
    @PostMapping
    public Viajes guardar(@RequestBody Viajes viaje){
        return service.guardar(viaje);
    }
    @PutMapping("/{id}")
    public Viajes actualizar(@PathVariable Integer id,
                              @RequestBody Viajes viaje){
        return service.actualizar(id, viaje);
    }
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id){
        service.eliminar(id);
    }
}

