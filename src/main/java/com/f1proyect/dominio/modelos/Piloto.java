package com.f1proyect.dominio.modelos;

public class Piloto {
    private int id;
    private String nombre;
    private String equipo;
    private Rol rol;
    private int experienciaAnios;
    private int habilidad; // 1 a 100, se usa luego en el cálculo de tiempos de vuelta

    // metodos de constructor
    public Piloto(int id, String nombre, String equipo, Rol rol, int experienciaAnios, int habilidad) {
        this.id = id;
        this.nombre = nombre;
        this.equipo = equipo;
        this.rol = rol;
        this.experienciaAnios = experienciaAnios;
        this.habilidad = habilidad;
    }

    // metodos Getter y Setter
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
    public int getExperienciaAnios() { 
        return experienciaAnios; 
    }
    public void setExperienciaAnios(int experienciaAnios) { 
        this.experienciaAnios = experienciaAnios; 
    }
    public int getHabilidad() { 
        return habilidad; 
    }
    public void setHabilidad(int habilidad) { 
        this.habilidad = habilidad; 
    }

    // metodo q sobreescribe el constructor 
    @Override
    public String toString() {
        return nombre + " - " + equipo + " (" + rol + ")";
    }
}
