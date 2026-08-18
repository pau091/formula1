package com.f1proyect.dominio.puertos.out;


import com.f1proyect.dominio.modelos.Equipo;
import java.util.List;
import java.util.Optional;

public interface EquipoRepository {
    Equipo guardar(Equipo equipo);
    Optional<Equipo> buscarPorNombre(String nombre);
    List<Equipo> obtenerTodos();
    void eliminar(String nombre);
}

/* se usa interface pq estan conectados a
 puertos in y out, los cuales son interfaces que permiten 
 seprar lo que se necesite implementar en el proyecto */
