package com.f1proyect.dominio.puertos.out;

import java.util.List;
import java.util.Optional;

import com.f1proyect.dominio.modelos.Vehiculo;

public interface VehiculoRepositorio {

    void  guardar(Vehiculo vehiculo);

    Optional<Vehiculo> buscarPorEquipoYModelo(String equipo, String modelo);

    boolean eliminar(String equipo, String modelo);

    List<Vehiculo> obtenerTodos();

    List<Vehiculo> buscarPorCriterio(String criterio);

    
}
