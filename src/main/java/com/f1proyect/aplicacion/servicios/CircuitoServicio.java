package com.f1proyect.aplicacion.servicios;

import com.f1proyect.dominio.modelos.Circuito;
import com.f1proyect.dominio.puertos.in.GestionCircuitoUseCase;
import com.f1proyect.dominio.puertos.out.CircuitoRepositorio;

import java.util.List;

public class CircuitoServicio implements GestionCircuitoUseCase {

    private final CircuitoRepositorio circuitoRepositorio;

    public CircuitoServicio(CircuitoRepositorio circuitoRepositorio) {
        this.circuitoRepositorio = circuitoRepositorio;
    }

    @Override
    public Circuito registrarCircuito(Circuito circuito) {
        return circuitoRepositorio.guardar(circuito);
    }

    @Override
    public Circuito actualizarCircuito(Circuito circuito) {
        return circuitoRepositorio.guardar(circuito);
    }

    @Override
    public void eliminarCircuito(int id) {
        circuitoRepositorio.eliminar(id);
    }

    @Override
    public Circuito obtenerCircuitoPorId(int id) {
        return circuitoRepositorio.buscarPorId(id).orElse(null);
    }

    @Override
    public List<Circuito> listarCircuitos() {
        return circuitoRepositorio.obtenerTodos();
    }
}