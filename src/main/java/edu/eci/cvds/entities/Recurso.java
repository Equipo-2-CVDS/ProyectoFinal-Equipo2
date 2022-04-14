package edu.eci.cvds.entities;

public class Recurso {
    private int id;
    private String nombre;
    private String tipo;
    private int capacidad;
    private String ubicacion;
    private String horario;
    private boolean estado;

    public Recurso() {
        super();
    }

    public Recurso(int id, String nombre, String tipo, int capacidad, String ubicacion, String horario, boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.ubicacion = ubicacion;
        this.horario = horario;
        this.estado = estado;
    }

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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCapacidad() {return capacidad;}

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getUbicacion() {return ubicacion;}

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getHorario() {return horario;}

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public boolean getEstado() {return estado;}

    public void setEstado(boolean estado) {
        this.estado = estado;
    }


    @Override
    public String toString() {
        return "Recurso: [id=" + id + ", nombre=" + nombre + ", ubicacion=" + ubicacion + "]";
    }
}

