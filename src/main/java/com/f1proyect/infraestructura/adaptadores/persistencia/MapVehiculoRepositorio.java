package com.f1proyect.infraestructura.adaptadores.persistencia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.f1proyect.dominio.modelos.Vehiculo;
import com.f1proyect.dominio.puertos.out.VehiculoRepositorio;

public class MapVehiculoRepositorio implements VehiculoRepositorio {

    private final Map<String, Vehiculo> vehiculos = new HashMap<>();

    // Combina equipo+modelo en una sola clave para el HashMap.
    // El "|" es solo un separador poco comun, para evitar que "Red Bull" + "RB 20"
    // choque por accidente con "Red BullRB" + "20".
    private String clave(String equipo, String modelo) {
        return equipo + "|" + modelo;
    }

    @Override
    public void guardar(Vehiculo vehiculo) {
        vehiculos.put(clave(vehiculo.getEquipo(), vehiculo.getModelo()), vehiculo);
    }

    @Override
    public Optional<Vehiculo> buscarPorEquipoYModelo(String equipo, String modelo) {
        Vehiculo encontrado = vehiculos.get(clave(equipo, modelo));
        return Optional.ofNullable(encontrado);
    }

    @Override
    public boolean eliminar(String equipo, String modelo) {
        return vehiculos.remove(clave(equipo, modelo)) != null;
    }

    @Override
    public List<Vehiculo> obtenerTodos() {
        return new ArrayList<>(vehiculos.values());
    }

    @Override
    public List<Vehiculo> buscarPorCriterio(String criterio) {
        List<Vehiculo> resultado = new ArrayList<>();
        for (Vehiculo v : vehiculos.values()) {
            if (v.getModelo().contains(criterio) || v.getEquipo().contains(criterio)) {
                resultado.add(v);
            }
        }
        return resultado;
    }
}