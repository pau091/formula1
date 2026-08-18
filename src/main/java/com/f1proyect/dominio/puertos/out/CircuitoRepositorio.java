package com.f1proyect.dominio.puertos.out;

import com.f1proyect.dominio.modelos.Circuito;
import java.util.List;
import java.util.Optional;

public interface CircuitoRepositorio {
    Circuito guardar(Circuito circuito);
    Optional<Circuito> buscarPorId(int id);
    List<Circuito> obtenerTodos(); // Asegúrate de que empiece con "o" minúscula
    void eliminar(int id);
}