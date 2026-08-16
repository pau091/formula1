package com.f1proyect.dominio.modelos;

import java.util.ArrayList;
import java.util.List;

public class Vehiculo {
    private String equipo;
    private String modelo;
    private String motor;
    private double velocidadMaximaKmh;
    private double aceleracion0100;
    private double consumoBase;    // litros por vuelta, condicion seca / modo normal
    private double desgasteBase;   // % de desgaste de neumaticos por vuelta, condicion seca / modo normal
    private ConfiVehiculo configuracion; // puede quedar en null hasta que se configure
    private final List<Piloto> pilotosAsignados;

    // constructor 
    public Vehiculo(String equipo, String modelo, String motor, double velocidadMaximaKmh,double aceleracion0100, double consumoBase, double desgasteBase) {
        this.equipo = equipo;
        this.modelo = modelo;
        this.motor = motor;
        this.velocidadMaximaKmh = velocidadMaximaKmh;
        this.aceleracion0100 = aceleracion0100;
        this.consumoBase = consumoBase;
        this.desgasteBase = desgasteBase;
        this.pilotosAsignados = new ArrayList<>();
    }

    // metodos getter y setter
    public String getEquipo() { 
        return equipo; 
    }
    public void setEquipo(String equipo) { 
        this.equipo = equipo; 
    }
    public String getModelo() { 
        return modelo; 
    }
    public void setModelo(String modelo) { 
        this.modelo = modelo; 
    }
    public String getMotor() { 
        return motor; 
    }
    public void setMotor(String motor) { 
        this.motor = motor; 
    }
    public double getVelocidadMaximaKmh() { 
        return velocidadMaximaKmh; 
    }
    public void setVelocidadMaximaKmh(double velocidadMaximaKmh) { 
        this.velocidadMaximaKmh = velocidadMaximaKmh; 
    }
    public double getAceleracion0100() { 
        return aceleracion0100; 
    }
    public void setAceleracion0100(double aceleracion0100) { 
        this.aceleracion0100 = aceleracion0100; 
    }
    public double getConsumoBase() { 
        return consumoBase; 
    }
    public void setConsumoBase(double consumoBase) { 
        this.consumoBase = consumoBase; 
    }
    public double getDesgasteBase() { 
        return desgasteBase; 
    }
    public void setDesgasteBase(double desgasteBase) { 
        this.desgasteBase = desgasteBase; 
    }
    public ConfiVehiculo getConfiguracion() { 
        return configuracion; 
    }
    public void setConfiguracion(ConfiVehiculo configuracion) { 
        this.configuracion = configuracion; 
    }
    public List<Piloto> getPilotosAsignados() { return pilotosAsignados; }
    public void agregarPiloto(Piloto piloto) { this.pilotosAsignados.add(piloto); }

    // metodo que sobreescribe el constructor
    @Override
    public String toString() {
        return modelo + " - " + equipo + " (" + velocidadMaximaKmh + " km/h, 0-100 en " + aceleracion0100 + "s)";
    }
}