package com.f1proyect.infraestructura.ui.joptionpane;

import javax.swing.JOptionPane;

import com.f1proyect.aplicacion.servicios.CircuitoServicio;
import com.f1proyect.aplicacion.servicios.PilotoServicio;
import com.f1proyect.aplicacion.servicios.SimulacionServicio;
import com.f1proyect.aplicacion.servicios.VehiculoServicio;
import com.f1proyect.dominio.puertos.out.CircuitoRepositorio;
import com.f1proyect.dominio.puertos.out.PilotoRepositorio;
import com.f1proyect.dominio.puertos.out.ResultadoRepositorio;
import com.f1proyect.dominio.puertos.out.VehiculoRepositorio;
import com.f1proyect.infraestructura.adaptadores.persistencia.MapCircuitoRepositorio;
import com.f1proyect.infraestructura.adaptadores.persistencia.MapPilotoRepositorio;
import com.f1proyect.infraestructura.adaptadores.persistencia.MapResultadoRepositorio;
import com.f1proyect.infraestructura.adaptadores.persistencia.MapVehiculoRepositorio;

public class MainMenu {

    public static void main(String[] args) {
        // 1. Repositorios: una sola instancia de cada uno, en memoria, para toda la ejecucion.
        PilotoRepositorio pilotoRepositorio = new MapPilotoRepositorio();
        CircuitoRepositorio circuitoRepositorio = new MapCircuitoRepositorio();
        VehiculoRepositorio vehiculoRepositorio = new MapVehiculoRepositorio();
        ResultadoRepositorio resultadoRepositorio = new MapResultadoRepositorio();

        // 2. Servicios: cada uno recibe su repositorio correspondiente por constructor.
        PilotoServicio pilotoServicio = new PilotoServicio(pilotoRepositorio);
        CircuitoServicio circuitoServicio = new CircuitoServicio(circuitoRepositorio);
        VehiculoServicio vehiculoServicio = new VehiculoServicio(vehiculoRepositorio);
        SimulacionServicio simulacionServicio = new SimulacionServicio(resultadoRepositorio);

        // 3. Vistas: reciben los servicios que necesitan. Las mismas instancias de arriba
        // se reutilizan en todas las vistas que las necesiten (por ejemplo, vehiculoServicio
        // se usa tanto en VehiculoVista como en SimulacionVista), para que todos vean los
        // mismos datos.
        PilotoEquipoVista pilotoVista = new PilotoEquipoVista(pilotoServicio);
        CircuitoVista circuitoVista = new CircuitoVista(circuitoServicio);
        VehiculoVista vehiculoVista = new VehiculoVista(vehiculoServicio, pilotoServicio);
        SimulacionVista simulacionVista = new SimulacionVista(simulacionServicio, vehiculoServicio, circuitoServicio);

        // 4. Menu principal: navega hacia cada sub-menu segun la eleccion.
        String[] opciones = {
            "Pilotos y Equipos",
            "Circuitos",
            "Vehiculos",
            "Simulacion de Clasificacion",
            "Salir"
        };

        int seleccion;
        do {
            seleccion = JOptionPane.showOptionDialog(null,
                    "Bienvenido al Simulador de Formula 1",
                    "Menu Principal",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opciones,
                    opciones[0]);

            switch (seleccion) {
                case 0 -> pilotoVista.mostrarMenu();
                case 1 -> circuitoVista.mostrarMenu();
                case 2 -> vehiculoVista.mostrarMenu();
                case 3 -> simulacionVista.mostrarMenu();
                default -> { } // "Salir" o se cerro la ventana
            }
        } while (seleccion != 4 && seleccion != -1);

        JOptionPane.showMessageDialog(null, "Gracias por usar el simulador. ¡Hasta pronto!");
    }
}