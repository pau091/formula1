package com.f1proyect.dominio.modelos;

public class ResultClasificacion {
    private Piloto piloto;
    private Vehiculo vehiculo;
    private Circuito circuito;
    private double tiempoVueltaSegundos;
    private Clima clima;
    private int posicion;

    // constructor 
    public ResultClasificacion(Piloto piloto, Vehiculo vehiculo, Circuito circuito, double tiempoVueltaSegundos, Clima clima, int posicion) {
        this.piloto = piloto;
        this.vehiculo = vehiculo;
        this.circuito = circuito;
        this.tiempoVueltaSegundos = tiempoVueltaSegundos;
        this.clima = clima;
        this.posicion = posicion;
    }

    // metodos getter y setter
    public Piloto getPiloto() { 
        return piloto; 
    }
    public void setPiloto(Piloto piloto) { 
        this.piloto = piloto; 
    }
    public Vehiculo getVehiculo() { 
        return vehiculo; 
    }
    public void setVehiculo(Vehiculo vehiculo) { 
        this.vehiculo = vehiculo; 
    }
    public Circuito getCircuito() { 
        return circuito; 
    }
    public void setCircuito(Circuito circuito) { 
        this.circuito = circuito; 
    }
    public double getTiempoVueltaSegundos() { 
        return tiempoVueltaSegundos; 
    }
    public void setTiempoVueltaSegundos(double tiempoVueltaSegundos) { 
        this.tiempoVueltaSegundos = tiempoVueltaSegundos; 
    }
    public Clima getClima() { 
        return clima; 
    }
    public void setClima(Clima clima) { 
        this.clima = clima; 
    }
    public int getPosicion() { 
        return posicion; 
    }
    public void setPosicion(int posicion) {
        this.posicion = posicion; 
    }

    // metodo que sobreescribe el constructor 
    @Override
    public String toString() {
        return posicion + ". " + piloto.getNombre() + " - " + tiempoVueltaSegundos + "s (" + clima + ")";
    }
}