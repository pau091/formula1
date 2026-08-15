package com.f1proyect.dominio.modelos;

public class Circuito {
    private String nombre;
    private String pais;
    private double longitudKm;
    private int vueltas;
    private String descripcion;
    private Clima climaTipico;

    // Constructor
    public Circuito(String nombre, String pais, double longitudKm, int vueltas, String descripcion, Clima climaTipico) {
        this.nombre = nombre;
        this.pais = pais;
        this.longitudKm = longitudKm;
        this.vueltas = vueltas;
        this.descripcion = descripcion;
        this.climaTipico = climaTipico;
    }

    // Metodos Getter y Setter
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
    public double getLongitudKm() { 
        return longitudKm; 
    }
    public void setLongitudKm(double longitudKm) { 
        this.longitudKm = longitudKm; 
    }
    public int getVueltas() { 
        return vueltas; 
    }
    public void setVueltas(int vueltas) { 
        this.vueltas = vueltas; 
    }
    public String getDescripcion() { 
        return descripcion; 
    }
    public void setDescripcion(String descripcion) { 
        this.descripcion = descripcion; 
    }
    public Clima getClimaTipico() { 
        return climaTipico; 
    }
    public void setClimaTipico(Clima climaTipico) { 
        this.climaTipico = climaTipico; 
    }

    // metodo que sobreescribe el constructor para que en un futuro sea mas facil llamarlo 
    @Override
    public String toString() {
        return nombre + " - " + pais + " (" + longitudKm + " km, " + vueltas + " vueltas)";
    }
}
