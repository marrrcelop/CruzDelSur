package com.reservas.CruzDelSur.service;

import com.reservas.CruzDelSur.entity.Viajes;
import com.reservas.CruzDelSur.repository.ViajeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class ViajeService {
    @Autowired
    private ViajeRepository repository;
    public List<Viajes> listarTodos(){
        return repository.findAll();
    }
    public Optional<Viajes> buscarPorId(Integer id){
        return repository.findById(id);
    }
    public List<Viajes> buscarPorPrecio(Double precio){
        return repository.buscarPorPrecio(precio);
    }
    @Transactional
    public Viajes guardar(Viajes viaje){
        return repository.save(viaje);
    }
    @Transactional
    public Viajes actualizar(Integer id, Viajes viajes){
        Viajes vi = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("viaje no encontrado"));
        vi.setOrigen(viajes.getOrigen());
        vi.setDestino(viajes.getDestino());
        vi.setFecha(viajes.getFecha());
        vi.setHora(viajes.getHora());
        vi.setTipo_servicio(viajes.getTipo_servicio());
        vi.setPrecio(viajes.getPrecio());
        vi.setEstado(viajes.getEstado());
        return repository.save(vi);
    }
    @Transactional
    public void eliminar(Integer id){
        Viajes vi = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Viaje no encontrado"));
        repository.delete(vi);
    }
}
