package com.f1proyect.dominio.modelos;

public class Piloto {
    private int id;
    private String nombre;
    private String equipo;
    private Rol rol;
    private int experiencia; // Escala de 1 a 100
    private int habilidad;   // Escala de 1 a 100

    // constructor
    public Piloto(int id, String nombre, String equipo, Rol rol, int experiencia, int habilidad) {
        this.id = id;
        this.nombre = nombre;
        this.equipo = equipo;
        this.rol = rol;
        this.experiencia = experiencia;
        this.habilidad = habilidad;
    }

    // metodos getter y setter
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
    public String getEquipo() {
        return equipo;
    }
    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }
    public Rol getRol() {
        return rol;
    }
    public void setRol(Rol rol) {
        this.rol = rol;
    }
    public int getExperiencia() {
        return experiencia;
    }
    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }
    public int getHabilidad() {
        return habilidad;
    }
    public void setHabilidad(int habilidad) {
        this.habilidad = habilidad;
    }

    // metodo toString
    @Override
    public String toString() {
        return nombre + " - " + equipo + " [" + rol + "] (Exp: " + experiencia + ", Hab: " + habilidad + ")";
    }
}