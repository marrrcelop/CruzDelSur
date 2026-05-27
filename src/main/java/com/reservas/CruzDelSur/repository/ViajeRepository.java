package com.reservas.CruzDelSur.repository;
import com.reservas.CruzDelSur.entity.Viajes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViajeRepository extends JpaRepository<Viajes, Integer>{
    @Query("SELECT v FROM Viajes v WHERE v.precio >= :precio")
    List<Viajes> buscarPorPrecio(Double precio);

}
