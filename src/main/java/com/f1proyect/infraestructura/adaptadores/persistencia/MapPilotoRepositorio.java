package com.f1proyect.infraestructura.adaptadores.persistencia;

import com.f1proyect.dominio.modelos.Piloto;
import com.f1proyect.dominio.puertos.out.PilotoRepositorio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MapPilotoRepositorio implements PilotoRepositorio {

    private final Map<Integer, Piloto> baseDeDatos = new HashMap<>();

    @Override
    public Piloto guardar(Piloto piloto) {
        baseDeDatos.put(piloto.getId(), piloto);
        return piloto;
    }

    @Override
    public Optional<Piloto> buscarPorId(int id) {
        return Optional.ofNullable(baseDeDatos.get(id));
    }

    @Override
    public List<Piloto> obtenerTodos() {
        return new ArrayList<>(baseDeDatos.values());
    }

    @Override
    public void eliminar(int id) {
        baseDeDatos.remove(id);
    }
}