package com.f1proyect.dominio.modelos;

import java.util.ArrayList;
import java.util.List;

public class Equipo {
    private String nombre;
    private String pais;
    private String motor;
    private final List<Piloto> pilotos;

    // constructor
    public Equipo(String nombre, String pais, String motor) {
        this.nombre = nombre;
        this.pais = pais;
        this.motor = motor;
        this.pilotos = new ArrayList<>();
    }
    
    // metodos getter y setter 
    public String getNombre() { 
        return nombre; 
    }
    public void setNombre(String nombre) { 
        this.nombre = nombre; 
    }
    public String getPais() { 
        return pais; 
    }
    public void setPais(String pais) { 
        this.pais = pais; 
    }
    public String getMotor() { 
        return motor; 
    }
    public void setMotor(String motor) { 
        this.motor = motor; 
    }
    public List<Piloto> getPilotos() { 
        return pilotos; 
    }
    public void agregarPiloto(Piloto piloto) { 
        this.pilotos.add(piloto); 
    }
    public void quitarPiloto(Piloto piloto) { 
        this.pilotos.remove(piloto); 
    }

    // metodo q sobreescribe el constructor 
    @Override
    public String toString() {
        return nombre + " (" + pais + ", motor " + motor + ")";
    }
}
