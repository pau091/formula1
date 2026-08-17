package com.f1proyect.dominio.modelos;

public class Circuito {
    private int id;
    private String nombre;
    private String pais;
    private double longitudKm;
    private int vueltas;
    private String descripcion;
    private String recordTiempo;
    private String recordPiloto;

    // Constructor con los 8 parámetros
    public Circuito(int id, String nombre, String pais, double longitudKm, int vueltas, String descripcion, String recordTiempo, String recordPiloto) {
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
        this.longitudKm = longitudKm;
        this.vueltas = vueltas;
        this.descripcion = descripcion;
        this.recordTiempo = recordTiempo;
        this.recordPiloto = recordPiloto;
    }

    // Métodos Getter y Setter
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
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
    public String getRecordTiempo() {
        return recordTiempo;
    }
    public void setRecordTiempo(String recordTiempo) {
        this.recordTiempo = recordTiempo;
    }
    public String getRecordPiloto() {
        return recordPiloto;
    }
    public void setRecordPiloto(String recordPiloto) {
        this.recordPiloto = recordPiloto;
    }

    // Método toString
    @Override
    public String toString() {
        return nombre + " - " + pais + " (" + longitudKm + " km, " + vueltas + " vueltas)";
    }
}