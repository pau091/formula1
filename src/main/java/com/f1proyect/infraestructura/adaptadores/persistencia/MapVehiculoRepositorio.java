package com.f1proyect.infraestructura.adaptadores.persistencia;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.f1proyect.dominio.modelos.Vehiculo;
import com.f1proyect.dominio.puertos.out.VehiculoRepositorio;

public class MapVehiculoRepositorio implements VehiculoRepositorio{
    private final List<Vehiculo> vehiculos = new ArrayList<>();

    @Override
    public void guardar(Vehiculo vehiculo) {
        vehiculos.add(vehiculo);
    }

    @Override
    public Optional<Vehiculo> buscarPorEquipoYModelo(String equipo, String modelo) {
        for (Vehiculo v : vehiculos) {
            if (v.getEquipo().equals(equipo) && v.getModelo().equals(modelo)) {
                return Optional.of(v);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean eliminar(String equipo, String modelo) {
        Optional<Vehiculo> encontrado = buscarPorEquipoYModelo(equipo, modelo);
        if (encontrado.isPresent()) {
            vehiculos.remove(encontrado.get());
            return true;
        }
        return false;
    }

    @Override
    public List<Vehiculo> obtenerTodos() {
        return vehiculos;
    }

    @Override
    public List<Vehiculo> buscarPorCriterio(String criterio) {
        List<Vehiculo> resultado = new ArrayList<>();
        for (Vehiculo v : vehiculos) {
            if (v.getModelo().contains(criterio) || v.getEquipo().contains(criterio)) {
                resultado.add(v);
            }
        }
        return resultado;
    }
}
