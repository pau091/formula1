package com.f1proyect.dominio.puertos.in;
import com.f1proyect.dominio.modelos.Circuito;
import java.util.List;
public interface GestionCircuitoUseCase {
    Circuito registrarCircuito(Circuito circuito);
    Circuito actualizarCircuito(Circuito circuito);
    void eliminarCircuito(int id);
    Circuito obtenerCircuitoPorId();
    List<Circuito> listarCircuito();

}
