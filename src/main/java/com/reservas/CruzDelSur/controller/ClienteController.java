package com.reservas.CruzDelSur.controller;

import com.reservas.CruzDelSur.entity.Cliente;
import com.reservas.CruzDelSur.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private ClienteService service;
    @GetMapping
    public List<Cliente> listar(){
        return service.listarTodos();
    }
    @GetMapping("/{id}")
    public Optional<Cliente> buscarPorId(@PathVariable Integer id){
        return service.buscarPorId(id);
    }
    @GetMapping("/nombre/{nombre}")
    public List<Cliente> buscarPorNombre(@PathVariable String nombre){
        return service.buscarPorNombre(nombre);
    }
    @PostMapping
    public Cliente guardar(@RequestBody Cliente cliente){
        return service.guardar(cliente);
    }
    @PutMapping("/{id}")
    public Cliente actualizar(@PathVariable Integer id,
                              @RequestBody Cliente cliente){
        return service.actualizar(id, cliente);
    }
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id){
        service.eliminar(id);
    }
}

