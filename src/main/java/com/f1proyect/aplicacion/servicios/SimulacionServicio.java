package com.f1proyect.aplicacion.servicios;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import com.f1proyect.dominio.modelos.Circuito;
import com.f1proyect.dominio.modelos.Clima;
import com.f1proyect.dominio.modelos.ConfiVehiculo;
import com.f1proyect.dominio.modelos.Piloto;
import com.f1proyect.dominio.modelos.ResultClasificacion;
import com.f1proyect.dominio.modelos.Vehiculo;
import com.f1proyect.dominio.puertos.in.SimulacionClasificacionUseCase;
import com.f1proyect.dominio.puertos.out.ResultadoRepositorio;

public class SimulacionServicio implements SimulacionClasificacionUseCase {

    private final ResultadoRepositorio repositorio;
    private final Random random = new Random();

    public SimulacionServicio(ResultadoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public List<ResultClasificacion> ejecutarClasificacion(List<Vehiculo> vehiculos, Circuito circuito) {
        // 1. Clima aleatorio, igual para todos en esta clasificacion
        Clima[] climas = Clima.values();
        Clima climaDelDia = climas[random.nextInt(climas.length)];

        List<ResultClasificacion> resultados = new ArrayList<>();

        // 2. Un tiempo de vuelta por cada piloto asignado a cada vehiculo
        for (Vehiculo vehiculo : vehiculos) {
            for (Piloto piloto : vehiculo.getPilotosAsignados()) {
                double tiempo = calcularTiempoVuelta(piloto, vehiculo, circuito, climaDelDia);
                resultados.add(new ResultClasificacion(piloto, vehiculo, circuito, tiempo, climaDelDia, 0));
            }
        }

        // 3. Ordenar de menor a mayor tiempo (el mas rapido queda primero)
        resultados.sort(Comparator.comparingDouble(ResultClasificacion::getTiempoVueltaSegundos));

        // 4. Asignar posicion segun el orden final y guardar cada resultado
        for (int i = 0; i < resultados.size(); i++) {
            ResultClasificacion resultado = resultados.get(i);
            resultado.setPosicion(i + 1);
            repositorio.guardar(resultado);
        }

        return resultados;
    }

    private double calcularTiempoVuelta(Piloto piloto, Vehiculo vehiculo, Circuito circuito, Clima clima) {
        // Tiempo base: formula simplificada (no es fisica real), pensada para la simulacion
        double tiempo = (circuito.getLongitudKm() / vehiculo.getVelocidadMaximaKmh()) * 3600;

        // A mas habilidad del piloto, menos tiempo (hasta 10% mas rapido con habilidad 100)
        tiempo *= 1 - (piloto.getHabilidad() / 1000.0);

        // Ajuste por clima
        switch (clima) {
            case LLUVIOSO -> tiempo *= 1.15;
            case EXTREMO -> tiempo *= 1.30;
            default -> tiempo *= 1.0; // SECO
        }

        // Ajuste por configuracion del vehiculo, si ya fue configurado
        ConfiVehiculo configuracion = vehiculo.getConfiguracion();
        if (configuracion != null && configuracion.getModo() != null) {
            switch (configuracion.getModo()) {
                case AGRESIVA -> tiempo *= 0.95;
                case AHORRO_COMBUSTIBLE -> tiempo *= 1.05;
                default -> tiempo *= 1.0; // NORMAL
            }
        }

        // Pequeña variacion aleatoria para que no den vueltas identicas
        double variacion = 0.98 + (random.nextDouble() * 0.04); // entre 0.98 y 1.02
        tiempo *= variacion;

        return Math.round(tiempo * 1000.0) / 1000.0; // redondeado a 3 decimales
    }

    @Override
    public List<ResultClasificacion> obtenerHistorial() {
        return repositorio.obtenerTodos();
    }

    @Override
    public List<ResultClasificacion> obtenerHistorialPorCircuito(String nombreCircuito) {
        return repositorio.obtenerPorCircuito(nombreCircuito);
    }
}