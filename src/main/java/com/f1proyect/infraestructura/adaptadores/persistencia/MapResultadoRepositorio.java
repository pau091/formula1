package com.f1proyect.infraestructura.adaptadores.persistencia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.f1proyect.dominio.modelos.ResultClasificacion;
import com.f1proyect.dominio.puertos.out.ResultadoRepositorio;

public class MapResultadoRepositorio implements ResultadoRepositorio {

    private final Map<Integer, ResultClasificacion> resultados = new HashMap<>();
    private int siguienteId = 1;

    @Override
    public void guardar(ResultClasificacion resultado) {
        resultados.put(siguienteId, resultado);
        siguienteId++;
    }

    @Override
    public List<ResultClasificacion> obtenerTodos() {
        return new ArrayList<>(resultados.values());
    }

    @Override
    public List<ResultClasificacion> obtenerPorCircuito(String nombreCircuito) {
        List<ResultClasificacion> filtrados = new ArrayList<>();
        for (ResultClasificacion r : resultados.values()) {
            if (r.getCircuito().getNombre().equals(nombreCircuito)) {
                filtrados.add(r);
            }
        }
        return filtrados;
    }
}