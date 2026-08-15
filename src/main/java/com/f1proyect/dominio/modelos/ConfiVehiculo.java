package com.f1proyect.dominio.modelos;

public class ConfiVehiculo {
    private ModoConduccion modo;
    private CargaAerodinamica carga;
    private PresionNeumaticos presion;
    private EstrategiaCombustible estrategia;

    // Constructor
    public ConfiVehiculo(ModoConduccion modo, CargaAerodinamica carga,PresionNeumaticos presion, EstrategiaCombustible estrategia) {
        this.modo = modo;
        this.carga = carga;
        this.presion = presion;
        this.estrategia = estrategia;
    }

    // metodos getter y setter
    public ModoConduccion getModo() { 
        return modo; 
    }
    public void setModo(ModoConduccion modo) { 
        this.modo = modo; 
    }
    public CargaAerodinamica getCarga() { 
        return carga; 
    }
    public void setCarga(CargaAerodinamica carga) { 
        this.carga = carga; 
    }
    public PresionNeumaticos getPresion() { 
        return presion; 
    }
    public void setPresion(PresionNeumaticos presion) { 
        this.presion = presion; 
    }
    public EstrategiaCombustible getEstrategia() { 
        return estrategia; 
    }
    public void setEstrategia(EstrategiaCombustible estrategia) { 
        this.estrategia = estrategia; 
    }

    // metodo que sobreescribe el constructor para q sea mas facil llamarlo
    @Override
    public String toString() {
        return "Modo: " + modo + ", Carga: " + carga + ", Presion: " + presion + ", Estrategia: " + estrategia;
    }
}