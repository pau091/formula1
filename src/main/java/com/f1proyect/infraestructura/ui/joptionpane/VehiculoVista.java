package com.f1proyect.infraestructura.ui.joptionpane;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

import com.f1proyect.aplicacion.servicios.VehiculoServicio;
import com.f1proyect.aplicacion.servicios.PilotoServicio;
import com.f1proyect.dominio.modelos.CargaAerodinamica;
import com.f1proyect.dominio.modelos.ConfiVehiculo;
import com.f1proyect.dominio.modelos.EstrategiaCombustible;
import com.f1proyect.dominio.modelos.ModoConduccion;
import com.f1proyect.dominio.modelos.Piloto;
import com.f1proyect.dominio.modelos.PresionNeumaticos;
import com.f1proyect.dominio.modelos.Rol;
import com.f1proyect.dominio.modelos.Vehiculo;

public class VehiculoVista {

    private final VehiculoServicio servicio;
    private final PilotoServicio pilotoServicio;

    public VehiculoVista(VehiculoServicio servicio, PilotoServicio pilotoServicio) {
        this.servicio = servicio;
        this.pilotoServicio = pilotoServicio;
    }

    public void mostrarMenu() {
        String[] opciones = {
            "Registrar vehiculo", "Editar vehiculo", "Eliminar vehiculo",
            "Asignar piloto", "Listar vehiculos", "Buscar vehiculo",
            "Comparar vehiculos", "Configurar vehiculo", "Volver"
        };

        int seleccion;
        do {
            seleccion = JOptionPane.showOptionDialog(null, "Gestion de Vehiculos", "Menu Vehiculos",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);

            switch (seleccion) {
                case 0 -> registrarVehiculo();
                case 1 -> editarVehiculo();
                case 2 -> eliminarVehiculo();
                case 3 -> asignarPiloto();
                case 4 -> listarVehiculos();
                case 5 -> buscarVehiculo();
                case 6 -> compararVehiculos();
                case 7 -> configurarVehiculo();
                default -> { }
            }
        } while (seleccion != 8 && seleccion != -1);
    }

    private void registrarVehiculo() {
        String equipo = JOptionPane.showInputDialog("Equipo:");
        String modelo = JOptionPane.showInputDialog("Modelo:");
        String motor = JOptionPane.showInputDialog("Motor:");
        double velocidad = leerDouble("Velocidad maxima (km/h):");
        double aceleracion = leerDouble("Aceleracion 0-100 (segundos):");
        double consumo = leerDouble("Consumo base (litros/vuelta):");
        double desgaste = leerDouble("Desgaste base (%/vuelta):");

        Vehiculo vehiculo = new Vehiculo(equipo, modelo, motor, velocidad, aceleracion, consumo, desgaste);
        servicio.registrarVehiculo(vehiculo);
        JOptionPane.showMessageDialog(null, "Vehiculo registrado.");
    }

    private void editarVehiculo() {
        String equipoActual = JOptionPane.showInputDialog("Equipo del vehiculo a editar:");
        String modeloActual = JOptionPane.showInputDialog("Modelo del vehiculo a editar:");

        String equipoNuevo = JOptionPane.showInputDialog("Nuevo equipo:");
        String modeloNuevo = JOptionPane.showInputDialog("Nuevo modelo:");
        String motorNuevo = JOptionPane.showInputDialog("Nuevo motor:");
        double velocidad = leerDouble("Nueva velocidad maxima (km/h):");
        double aceleracion = leerDouble("Nueva aceleracion 0-100 (segundos):");
        double consumo = leerDouble("Nuevo consumo base (litros/vuelta):");
        double desgaste = leerDouble("Nuevo desgaste base (%/vuelta):");

        Vehiculo datosNuevos = new Vehiculo(equipoNuevo, modeloNuevo, motorNuevo, velocidad, aceleracion, consumo, desgaste);
        servicio.editarVehiculo(equipoActual, modeloActual, datosNuevos);
        JOptionPane.showMessageDialog(null, "Vehiculo actualizado (si existia).");
    }

    private void eliminarVehiculo() {
        String equipo = JOptionPane.showInputDialog("Equipo del vehiculo a eliminar:");
        String modelo = JOptionPane.showInputDialog("Modelo del vehiculo a eliminar:");
        Vehiculo objetivo = buscarExacto(equipo, modelo);

        if (objetivo != null) {
            servicio.eliminarVehiculo(objetivo);
            JOptionPane.showMessageDialog(null, "Vehiculo eliminado.");
        } else {
            JOptionPane.showMessageDialog(null, "No se encontro ese vehiculo.");
        }
    }

    private void asignarPiloto() {
        String equipo = JOptionPane.showInputDialog("Equipo del vehiculo:");
        String modelo = JOptionPane.showInputDialog("Modelo del vehiculo:");
        Vehiculo objetivo = buscarExacto(equipo, modelo);

        if (objetivo == null) {
            JOptionPane.showMessageDialog(null, "No se encontro ese vehiculo.");
            return;
        }

        List<Piloto> pilotosDelEquipo = new ArrayList<>();
        for (Piloto p : pilotoServicio.listarPilotos()) {
            if (p.getEquipo().equals(equipo)) {
                pilotosDelEquipo.add(p);
            }
        }

        String[] opciones = pilotosDelEquipo.isEmpty()
                ? new String[]{"Crear piloto nuevo"}
                : new String[]{"Crear piloto nuevo", "Elegir piloto existente"};

        int eleccion = JOptionPane.showOptionDialog(null, "¿Que piloto quieres asignar?", "Asignar piloto",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[0]);

        Piloto piloto;
        if (eleccion == 1) {
            piloto = (Piloto) JOptionPane.showInputDialog(null, "Elige un piloto:", "Asignar piloto",
                    JOptionPane.QUESTION_MESSAGE, null, pilotosDelEquipo.toArray(), pilotosDelEquipo.get(0));
            if (piloto == null) return;
        } else if (eleccion == 0) {
            piloto = crearPilotoNuevo(equipo);
        } else {
            return;
        }

        servicio.asignarPiloto(objetivo, piloto);
        JOptionPane.showMessageDialog(null, "Piloto asignado.");
    }

    private Piloto crearPilotoNuevo(String equipo) {
        String nombre = JOptionPane.showInputDialog("Nombre del piloto:");
        Rol rol = (Rol) JOptionPane.showInputDialog(null, "Rol:", "Nuevo piloto",
                JOptionPane.QUESTION_MESSAGE, null, Rol.values(), Rol.ESCUDERO);
        int experiencia = leerInt("Años de experiencia:");
        int habilidad = leerIntEnRango("Habilidad (1-100):", 1, 100);

        // El id lo generamos aca porque PilotoServicio.registrarPiloto no lo autogenera,
        // solo guarda lo que le pasemos. Usamos la cantidad actual de pilotos + 1.
        int id = pilotoServicio.listarPilotos().size() + 1;

        Piloto piloto = new Piloto(id, nombre, equipo, rol, experiencia, habilidad);
        return pilotoServicio.registrarPiloto(piloto);
    }

    private void listarVehiculos() {
        mostrarLista(servicio.listarVehiculos(), "No hay vehiculos registrados.");
    }

    private void buscarVehiculo() {
        String criterio = JOptionPane.showInputDialog("Buscar por modelo o equipo:");
        mostrarLista(servicio.buscarVehiculo(criterio), "No se encontro ningun vehiculo con ese criterio.");
    }

    private void compararVehiculos() {
        String criterio1 = JOptionPane.showInputDialog("Modelo o equipo del primer vehiculo:");
        String criterio2 = JOptionPane.showInputDialog("Modelo o equipo del segundo vehiculo:");

        List<Vehiculo> resultado1 = servicio.buscarVehiculo(criterio1);
        List<Vehiculo> resultado2 = servicio.buscarVehiculo(criterio2);

        if (resultado1.isEmpty() || resultado2.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No se encontraron ambos vehiculos.");
            return;
        }

        List<Vehiculo> comparacion = servicio.compararVehiculos(List.of(resultado1.get(0), resultado2.get(0)));
        StringBuilder texto = new StringBuilder();
        for (Vehiculo v : comparacion) {
            texto.append(v).append("\n");
        }
        JOptionPane.showMessageDialog(null, texto.toString());
    }

    private void configurarVehiculo() {
        String equipo = JOptionPane.showInputDialog("Equipo del vehiculo:");
        String modelo = JOptionPane.showInputDialog("Modelo del vehiculo:");
        Vehiculo objetivo = buscarExacto(equipo, modelo);

        if (objetivo == null) {
            JOptionPane.showMessageDialog(null, "No se encontro ese vehiculo.");
            return;
        }

        ModoConduccion modo = (ModoConduccion) JOptionPane.showInputDialog(null, "Modo de conduccion:",
                "Configurar", JOptionPane.QUESTION_MESSAGE, null, ModoConduccion.values(), ModoConduccion.NORMAL);
        CargaAerodinamica carga = (CargaAerodinamica) JOptionPane.showInputDialog(null, "Carga aerodinamica:",
                "Configurar", JOptionPane.QUESTION_MESSAGE, null, CargaAerodinamica.values(), CargaAerodinamica.MEDIA);
        PresionNeumaticos presion = (PresionNeumaticos) JOptionPane.showInputDialog(null, "Presion de neumaticos:",
                "Configurar", JOptionPane.QUESTION_MESSAGE, null, PresionNeumaticos.values(), PresionNeumaticos.ESTANDAR);
        EstrategiaCombustible estrategia = (EstrategiaCombustible) JOptionPane.showInputDialog(null, "Estrategia de combustible:",
                "Configurar", JOptionPane.QUESTION_MESSAGE, null, EstrategiaCombustible.values(), EstrategiaCombustible.BALANCEADA);

        ConfiVehiculo configuracion = new ConfiVehiculo(modo, carga, presion, estrategia);
        servicio.configurarVehiculo(objetivo, configuracion);
        JOptionPane.showMessageDialog(null, "Configuracion guardada.");
    }

    private Vehiculo buscarExacto(String equipo, String modelo) {
        for (Vehiculo v : servicio.buscarVehiculo(modelo)) {
            if (v.getEquipo().equals(equipo) && v.getModelo().equals(modelo)) {
                return v;
            }
        }
        return null;
    }

    private void mostrarLista(List<Vehiculo> vehiculos, String mensajeVacio) {
        if (vehiculos.isEmpty()) {
            JOptionPane.showMessageDialog(null, mensajeVacio);
            return;
        }
        StringBuilder texto = new StringBuilder();
        for (Vehiculo v : vehiculos) {
            texto.append(v).append("\n");
        }
        JOptionPane.showMessageDialog(null, texto.toString());
    }

    private double leerDouble(String mensaje) {
        while (true) {
            String texto = JOptionPane.showInputDialog(mensaje);
            try {
                return Double.parseDouble(texto);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ingresa un numero valido.");
            }
        }
    }

    private int leerInt(String mensaje) {
        while (true) {
            String texto = JOptionPane.showInputDialog(mensaje);
            try {
                return Integer.parseInt(texto);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Ingresa un numero entero valido.");
            }
        }
    }

    private int leerIntEnRango(String mensaje, int min, int max) {
        while (true) {
            int valor = leerInt(mensaje);
            if (valor >= min && valor <= max) {
                return valor;
            }
            JOptionPane.showMessageDialog(null, "Debe estar entre " + min + " y " + max + ".");
        }
    }
}