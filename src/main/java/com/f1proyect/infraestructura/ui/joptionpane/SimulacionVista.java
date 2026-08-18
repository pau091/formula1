package com.f1proyect.infraestructura.ui.joptionpane;

import javax.swing.JOptionPane;
import java.util.List;

import com.f1proyect.aplicacion.servicios.CircuitoServicio;
import com.f1proyect.aplicacion.servicios.SimulacionServicio;
import com.f1proyect.aplicacion.servicios.VehiculoServicio;
import com.f1proyect.dominio.modelos.Circuito;
import com.f1proyect.dominio.modelos.ResultClasificacion;
import com.f1proyect.dominio.modelos.Vehiculo;

public class SimulacionVista {

    private final SimulacionServicio simulacionServicio;
    private final VehiculoServicio vehiculoServicio;
    private final CircuitoServicio circuitoServicio;

    public SimulacionVista(SimulacionServicio simulacionServicio, VehiculoServicio vehiculoServicio,
                            CircuitoServicio circuitoServicio) {
        this.simulacionServicio = simulacionServicio;
        this.vehiculoServicio = vehiculoServicio;
        this.circuitoServicio = circuitoServicio;
    }

    public void mostrarMenu() {
        String[] opciones = { "Ejecutar clasificacion", "Ver historial completo", "Ver historial por circuito", "Volver" };

        int seleccion;
        do {
            seleccion = JOptionPane.showOptionDialog(null, "Simulacion de Clasificacion", "Menu Simulacion",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);

            switch (seleccion) {
                case 0 -> ejecutarClasificacion();
                case 1 -> verHistorialCompleto();
                case 2 -> verHistorialPorCircuito();
                default -> { }
            }
        } while (seleccion != 3 && seleccion != -1);
    }

    private void ejecutarClasificacion() {
        List<Vehiculo> vehiculos = vehiculoServicio.listarVehiculos();
        if (vehiculos.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay vehiculos registrados todavia.");
            return;
        }

        List<Circuito> circuitos = circuitoServicio.listarCircuitos();
        if (circuitos.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "No hay circuitos registrados todavia. Pidele a tu compañera que registre uno primero.");
            return;
        }

        Circuito circuito = (Circuito) JOptionPane.showInputDialog(null, "Elige un circuito:", "Simulacion",
                JOptionPane.QUESTION_MESSAGE, null, circuitos.toArray(), circuitos.get(0));
        if (circuito == null) return;

        List<ResultClasificacion> resultados = simulacionServicio.ejecutarClasificacion(vehiculos, circuito);

        if (resultados.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "No se genero ningun resultado. Revisa que al menos un vehiculo tenga pilotos asignados.");
            return;
        }

        mostrarTabla(resultados, "Clasificacion");
    }

    private void verHistorialCompleto() {
        mostrarTabla(simulacionServicio.obtenerHistorial(), "No hay resultados guardados todavia.");
    }

    private void verHistorialPorCircuito() {
        String nombreCircuito = JOptionPane.showInputDialog("Nombre del circuito:");
        mostrarTabla(simulacionServicio.obtenerHistorialPorCircuito(nombreCircuito),
                "No hay resultados para ese circuito.");
    }

    private void mostrarTabla(List<ResultClasificacion> resultados, String tituloOMensajeVacio) {
        if (resultados.isEmpty()) {
            JOptionPane.showMessageDialog(null, tituloOMensajeVacio);
            return;
        }
        StringBuilder texto = new StringBuilder();
        for (ResultClasificacion r : resultados) {
            texto.append(r).append("\n");
        }
        JOptionPane.showMessageDialog(null, texto.toString());
    }
}