package com.f1proyect.dominio.puertos.in;

import java.util.List;

import com.f1proyect.dominio.modelos.ConfiVehiculo;
import com.f1proyect.dominio.modelos.Piloto;
import com.f1proyect.dominio.modelos.Vehiculo;

public interface GestionVehiculoUseCase {

    // Registrar un vehículo nuevo
    void registrarVehiculo(Vehiculo vehiculo);

    // Editar un vehículo existente, identificado por equipo + modelo
    void editarVehiculo(String equipoActual, String modeloActual, Vehiculo datosNuevos);

    // Eliminar un vehículo
    void eliminarVehiculo(Vehiculo vehiculo);

    // Asignar un piloto a un vehículo específico
    void asignarPiloto(Vehiculo vehiculo, Piloto piloto);

    // Listar todos los vehículos registrados
    List<Vehiculo> listarVehiculos();

    // Buscar vehículo(s) por modelo o equipo
    List<Vehiculo> buscarVehiculo(String criterio);

    // Comparar dos o más vehículos
    List<Vehiculo> compararVehiculos(List<Vehiculo> vehiculos);

    // Configurar (o reconfigurar) los parámetros de manejo de un vehículo
    void configurarVehiculo(Vehiculo vehiculo, ConfiVehiculo configuracion);
}
