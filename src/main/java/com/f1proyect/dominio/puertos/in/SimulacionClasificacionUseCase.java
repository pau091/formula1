package com.f1proyect.dominio.puertos.in;

import java.util.List;

import com.f1proyect.dominio.modelos.Circuito;
import com.f1proyect.dominio.modelos.ResultClasificacion;
import com.f1proyect.dominio.modelos.Vehiculo;

public interface SimulacionClasificacionUseCase {

    // Corre la clasificacion: clima aleatorio + tiempo de vuelta para cada piloto
    // asignado a cada vehiculo, ordena, asigna posiciones y guarda los resultados.
    List<ResultClasificacion> ejecutarClasificacion(List<Vehiculo> vehiculos, Circuito circuito);

    List<ResultClasificacion> obtenerHistorial();

    List<ResultClasificacion> obtenerHistorialPorCircuito(String nombreCircuito);
}
