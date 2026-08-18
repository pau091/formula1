package com.f1proyect.infraestructura.adaptadores.persistencia;

import com.f1proyect.dominio.modelos.Circuito;
import com.f1proyect.dominio.puertos.out.CircuitoRepositorio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MapCircuitoRepositorio implements CircuitoRepositorio {

    private final Map<Integer, Circuito> baseDeDatos = new HashMap<>();

    @Override
    public Circuito guardar(Circuito circuito) {
        baseDeDatos.put(circuito.getId(), circuito);
        return circuito;
    }

    @Override
    public Optional<Circuito> buscarPorId(int id) {
        return Optional.ofNullable(baseDeDatos.get(id));
    }

    @Override
    public List<Circuito> obtenerTodos() {
        return new ArrayList<>(baseDeDatos.values());
    }

    @Override
    public void eliminar(int id) {
        baseDeDatos.remove(id);
    }
}