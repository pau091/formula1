package com.f1proyect.infraestructura.ui.joptionpane;

import com.f1proyect.dominio.modelos.Piloto;
import com.f1proyect.dominio.modelos.Rol;
import com.f1proyect.dominio.puertos.in.GestionPilotoUseCase;

import javax.swing.JOptionPane;
import java.util.List;

public class PilotoEquipoVista {

    private final GestionPilotoUseCase pilotoUseCase;

    public PilotoEquipoVista(GestionPilotoUseCase pilotoUseCase) {
        this.pilotoUseCase = pilotoUseCase;
    }

    public void mostrarMenu() {
        boolean salir = false;
        while (!salir) {
            String opcion = JOptionPane.showInputDialog(
                null,
                "--- GESTIÓN DE PILOTOS Y EQUIPOS ---\n" +
                "1. Registrar Piloto\n" +
                "2. Listar Pilotos\n" +
                "3. Eliminar Piloto\n" +
                "4. Volver\n\n" +
                "Seleccione una opción:",
                "Menú Pilotos",
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
            int id = Integer.parseInt(JOptionPane.showInputDialog("ID del Piloto:"));
            String nombre = JOptionPane.showInputDialog("Nombre del Piloto:");
            String equipo = JOptionPane.showInputDialog("Escudería / Equipo:");
            Rol rol = Rol.valueOf(JOptionPane.showInputDialog("Rol (LIDER o ESCUDERO):").toUpperCase());
            int exp = Integer.parseInt(JOptionPane.showInputDialog("Experiencia (1-100):"));
            int hab = Integer.parseInt(JOptionPane.showInputDialog("Habilidad (1-100):"));

            Piloto piloto = new Piloto(id, nombre, equipo, rol, exp, hab);
            pilotoUseCase.registrarPiloto(piloto);
            JOptionPane.showMessageDialog(null, "¡Piloto registrado exitosamente!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en los datos ingresados.");
        }
    }

    private void listar() {
        List<Piloto> lista = pilotoUseCase.listarPilotos();
        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay pilotos registrados.");
            return;
        }
        StringBuilder sb = new StringBuilder("--- LISTA DE PILOTOS ---\n");
        for (Piloto p : lista) {
            sb.append(p.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    private void eliminar() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("ID del piloto a eliminar:"));
            pilotoUseCase.eliminarPiloto(id);
            JOptionPane.showMessageDialog(null, "Piloto eliminado.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ID no válido.");
        }
    }
}