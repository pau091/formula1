package com.f1proyect.infraestructura.adaptadores.persistencia;

import com.f1proyect.dominio.modelos.Equipo;
import com.f1proyect.dominio.puertos.out.EquipoRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MapEquipoRepositorio implements EquipoRepository {

    private final Map<String, Equipo> baseDeDatos = new HashMap<>();

    @Override
    public Equipo guardar(Equipo equipo) {
        baseDeDatos.put(equipo.getNombre(), equipo);
        return equipo;
    }

    @Override
    public Optional<Equipo> buscarPorNombre(String nombre) {
        return Optional.ofNullable(baseDeDatos.get(nombre));
    }

    @Override
    public List<Equipo> obtenerTodos() {
        return new ArrayList<>(baseDeDatos.values());
    }

    @Override
    public void eliminar(String nombre) {
        baseDeDatos.remove(nombre);
    }
}