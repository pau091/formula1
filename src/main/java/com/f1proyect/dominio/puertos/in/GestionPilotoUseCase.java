package com.f1proyect.dominio.puertos.in;

import com.f1proyect.dominio.modelos.Piloto;
import java.util.List;

public interface GestionPilotoUseCase {
    Piloto registrarPiloto(Piloto piloto);
    Piloto actualizarPiloto(Piloto piloto); // Verificar que tenga la "i" (actualizarPiloto)
    void eliminarPiloto(int id);
    Piloto obtenerPilotoPorId(int id);
    List<Piloto> listarPilotos();
}