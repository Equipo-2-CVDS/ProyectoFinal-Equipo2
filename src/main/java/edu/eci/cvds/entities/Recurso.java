package edu.eci.cvds.entities;

public class Recurso {
    private int id;
    private String nombre;
    private String tipo;
    private int capacidad;
    private String ubicacion;
    private String estado;

    public Recurso() {
        super();
    }

    public Recurso(int id, String nombre, String tipo, int capacidad, String ubicacion, boolean estado) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.ubicacion = ubicacion;
        this.estado = estado ? "Habilitada" : "Deshabilitada";
    }

    public Recurso(String nombre, String tipo, int capacidad, String ubicacion, boolean estado) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.ubicacion = ubicacion;
        this.estado = estado ? "Habilitada" : "Deshabilitada";
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

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado ? "Habilitado" : "Deshabilitado";
    }

    @Override
    public String toString() {
        return "Recurso: [id=" + id + ", nombre=" + nombre + ", ubicacion=" + ubicacion + ", Estado=" + estado + "]";
    }
}
