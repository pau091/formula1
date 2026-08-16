package com.f1proyect.dominio.puertos.out;

import java.util.List;

import com.f1proyect.dominio.modelos.ResultClasificacion;

public interface ResultadoRepositorio {
    // guarda los resultados de la clasificacion
    void guardar(ResultClasificacion resultado);

    // Obtiene todos los resultados de la clasificacion
    List<ResultClasificacion> obtenerTodos();
    
    // obtiene solo los resultados de un circuito
    List<ResultClasificacion> obtenerPorCircuito(String nombreCircuito);
}