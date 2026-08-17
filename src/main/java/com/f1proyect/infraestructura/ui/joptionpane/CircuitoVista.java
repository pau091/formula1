package com.f1proyect.infraestructura.ui.joptionpane;

import com.f1proyect.dominio.modelos.Circuito;
import com.f1proyect.dominio.puertos.in.GestionCircuitoUseCase;

import javax.swing.JOptionPane;
import java.util.List;

public class CircuitoVista {

    private final GestionCircuitoUseCase circuitoUseCase;

    public CircuitoVista(GestionCircuitoUseCase circuitoUseCase) {
        this.circuitoUseCase = circuitoUseCase;
    }

    public void mostrarMenu() {
        boolean salir = false;
        while (!salir) {
            String opcion = JOptionPane.showInputDialog(
                null,
                "--- GESTIÓN DE CIRCUITOS ---\n" +
                "1. Registrar Circuito\n" +
                "2. Listar Circuitos\n" +
                "3. Eliminar Circuito\n" +
                "4. Volver\n\n" +
                "Seleccione una opción:",
                "Menú Circuitos",
                JOptionPane.QUESTION_MESSAGE
            );

            if (opcion == null || opcion.equals("4")) {
                salir = true;
                continue;
            }

            switch (opcion) {
                case "1":
                    registrar();
                    break;
                case "2":
                    listar();
                    break;
                case "3":
                    eliminar();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida.");
            }
        }
    }

 private void registrar() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("ID del Circuito:"));
            String nombre = JOptionPane.showInputDialog("Nombre del Circuito:");
            String pais = JOptionPane.showInputDialog("País:");
            double longitudKm = Double.parseDouble(JOptionPane.showInputDialog("Longitud (km):"));
            int vueltas = Integer.parseInt(JOptionPane.showInputDialog("Número de vueltas:"));
            String descripcion = JOptionPane.showInputDialog("Descripción:");
            String recordTiempo = JOptionPane.showInputDialog("Récord de tiempo (ej. 1:21.046):");
            String recordPiloto = JOptionPane.showInputDialog("Piloto del récord:");

            // Aquí se pasan los 8 parámetros en el orden exacto de la clase Circuito.java
            Circuito circuito = new Circuito(id, nombre, pais, longitudKm, vueltas, descripcion, recordTiempo, recordPiloto);
            circuitoUseCase.registrarCircuito(circuito);
            JOptionPane.showMessageDialog(null, "¡Circuito registrado exitosamente!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al ingresar datos del circuito.");
        }
    }

    private void listar() {
        List<Circuito> lista = circuitoUseCase.listarCircuitos();
        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay circuitos registrados.");
            return;
        }
        StringBuilder sb = new StringBuilder("--- LISTA DE CIRCUITOS ---\n");
        for (Circuito c : lista) {
            sb.append(c.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    private void eliminar() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("ID del circuito a eliminar:"));
            circuitoUseCase.eliminarCircuito(id);
            JOptionPane.showMessageDialog(null, "Circuito eliminado.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ID no válido.");
        }
    }
}