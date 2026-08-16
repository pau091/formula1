package com.f1proyect.aplicacion.servicios;

import java.util.List;
import java.util.Optional;

import com.f1proyect.dominio.modelos.ConfiVehiculo;
import com.f1proyect.dominio.modelos.Piloto;
import com.f1proyect.dominio.modelos.Vehiculo;
import com.f1proyect.dominio.puertos.in.GestionVehiculoUseCase;
import com.f1proyect.dominio.puertos.out.VehiculoRepositorio;

public class VehiculoServicio implements GestionVehiculoUseCase {

    private final VehiculoRepositorio repositorio;

    public VehiculoServicio(VehiculoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public void registrarVehiculo(Vehiculo vehiculo) {
        Optional<Vehiculo> existente = repositorio.buscarPorEquipoYModelo(vehiculo.getEquipo(), vehiculo.getModelo());
        if (existente.isEmpty()) {
            repositorio.guardar(vehiculo);
        } else {
            System.out.println("Ya existe un vehiculo " + vehiculo.getModelo() + " para " + vehiculo.getEquipo());
        }
    }

    @Override
    public void editarVehiculo(String equipoActual, String modeloActual, Vehiculo datosNuevos) {
        Optional<Vehiculo> existente = repositorio.buscarPorEquipoYModelo(equipoActual, modeloActual);
        if (existente.isPresent()) {
            Vehiculo vehiculo = existente.get();
            vehiculo.setEquipo(datosNuevos.getEquipo());
            vehiculo.setModelo(datosNuevos.getModelo());
            vehiculo.setMotor(datosNuevos.getMotor());
            vehiculo.setVelocidadMaximaKmh(datosNuevos.getVelocidadMaximaKmh());
            vehiculo.setAceleracion0100(datosNuevos.getAceleracion0100());
            vehiculo.setConsumoBase(datosNuevos.getConsumoBase());
            vehiculo.setDesgasteBase(datosNuevos.getDesgasteBase());
        } else {
            System.out.println("No se encontro el vehiculo a editar");
        }
    }

    @Override
    public void eliminarVehiculo(Vehiculo vehiculo) {
        repositorio.eliminar(vehiculo.getEquipo(), vehiculo.getModelo());
    }

    @Override
    public void asignarPiloto(Vehiculo vehiculo, Piloto piloto) {
        if (!vehiculo.getEquipo().equals(piloto.getEquipo())) {
            System.out.println("El piloto no pertenece al mismo equipo que el vehiculo");
            return;
        }
        vehiculo.agregarPiloto(piloto);
    }

    @Override
    public List<Vehiculo> listarVehiculos() {
        return repositorio.obtenerTodos();
    }

    @Override
    public List<Vehiculo> buscarVehiculo(String criterio) {
        return repositorio.buscarPorCriterio(criterio);
    }

    @Override
    public List<Vehiculo> compararVehiculos(List<Vehiculo> vehiculos) {
        return vehiculos;
    }

    @Override
    public void configurarVehiculo(Vehiculo vehiculo, ConfiVehiculo configuracion) {
    vehiculo.setConfiguracion(configuracion);
    }
}
