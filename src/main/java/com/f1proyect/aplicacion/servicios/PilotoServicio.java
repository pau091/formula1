package com.f1proyect.aplicacion.servicios;

// Imports de la capa de dominio
import com.f1proyect.dominio.modelos.Piloto;
import com.f1proyect.dominio.puertos.in.GestionPilotoUseCase;
import com.f1proyect.dominio.puertos.out.PilotoRepositorio;

// Imports de Java
import java.util.List;

public class PilotoServicio implements GestionPilotoUseCase {

    private final PilotoRepositorio pilotoRepositorio;

    // constructor con inyeccion del repositorio
    public PilotoServicio(PilotoRepositorio pilotoRepositorio) {
        this.pilotoRepositorio = pilotoRepositorio;
    }

    @Override
    public Piloto registrarPiloto(Piloto piloto) {
        return pilotoRepositorio.guardar(piloto);
    }

    @Override
    public Piloto actualizarPiloto(Piloto piloto) {
        return pilotoRepositorio.guardar(piloto);
    }

    @Override
    public void eliminarPiloto(int id) {
        pilotoRepositorio.eliminar(id);
    }

    @Override
    public Piloto obtenerPilotoPorId(int id) {
        return pilotoRepositorio.buscarPorId(id).orElse(null);
    }

    @Override
    public List<Piloto> listarPilotos() {
        return pilotoRepositorio.obtenerTodos();
    }
}

/*Permite transformar la respuesta funcional de un repositorio en un valor
 tradicional para simplificar la respuesta del caso de uso. */