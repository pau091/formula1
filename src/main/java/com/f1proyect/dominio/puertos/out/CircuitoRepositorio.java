package com.f1proyect.dominio.puertos.out;
import com.f1proyect.dominio.modelos.Circuito;
import java.util.List;
import java.util.Optional;

public interface CircuitoRepositorio {
    Circuito guardar(Circuito circuito);
    Optional<Circuito> buscarPorId();
    List <Circuito> ObtenerTodos();
    void eliminar(int id);


}
