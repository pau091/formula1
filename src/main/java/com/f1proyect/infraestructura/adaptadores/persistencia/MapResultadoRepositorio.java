package com.f1proyect.infraestructura.adaptadores.persistencia;

import java.util.ArrayList;
import java.util.List;

import com.f1proyect.dominio.modelos.ResultClasificacion;
import com.f1proyect.dominio.puertos.out.ResultadoRepositorio;

public class MapResultadoRepositorio implements ResultadoRepositorio {

    private final List<ResultClasificacion> resultados = new ArrayList<>();

    @Override
    public void guardar(ResultClasificacion resultado) {
        resultados.add(resultado);
    }

    @Override
    public List<ResultClasificacion> obtenerTodos() {
        return resultados;
    }

    @Override
    public List<ResultClasificacion> obtenerPorCircuito(String nombreCircuito) {
        List<ResultClasificacion> filtrados = new ArrayList<>();
        for (ResultClasificacion r : resultados) {
            if (r.getCircuito().getNombre().equals(nombreCircuito)) {
                filtrados.add(r);
            }
        }
        return filtrados;
    }
}
