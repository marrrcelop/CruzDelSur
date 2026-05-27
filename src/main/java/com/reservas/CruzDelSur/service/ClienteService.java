package com.reservas.CruzDelSur.service;

import com.reservas.CruzDelSur.entity.Cliente;
import com.reservas.CruzDelSur.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;
    public List<Cliente> listarTodos(){
        return repository.findAll();
    }
    public Optional<Cliente> buscarPorId(Integer id){
        return repository.findById(id);
    }
    public List<Cliente> buscarPorNombre(String nombre){
        return repository.buscarPorNombre(nombre);
    }
    @Transactional
    public Cliente guardar(Cliente cliente){
        return repository.save(cliente);
    }
    @Transactional
    public Cliente actualizar(Integer id, Cliente cliente){
        Cliente cli = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        cli.setNombres(cliente.getNombres());
        cli.setCorreo(cliente.getCorreo());
        cli.setApellidos(cliente.getApellidos());
        cli.setDocumento(cliente.getDocumento());
        return repository.save(cli);
    }
    @Transactional
    public void eliminar(Integer id){
        Cliente cli = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        repository.delete(cli);
    }
}

