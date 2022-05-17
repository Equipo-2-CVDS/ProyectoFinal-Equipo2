package edu.eci.cvds.entities;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class Table {
    private String nombreRecurso;
    private String tipoRecurso;
    private String nombreEstudiante;
    private String programaEstudiante;
    private LocalDate fecha;
    private LocalTime desde;
    private LocalTime hasta;
    private boolean estado;
    private long cantidad;

    public Table(String nombreRecurso, String tipoRecurso, String nombreEstudiante, String programaEstudiante,
            Date fecha, Time desde, Time hasta, boolean estado, long cantidad) {
        this.nombreRecurso = nombreRecurso;
        this.tipoRecurso = tipoRecurso;
        this.nombreEstudiante = nombreEstudiante;
        this.programaEstudiante = programaEstudiante;
        this.fecha = fecha.toLocalDate();
        this.desde = desde.toLocalTime();
        this.hasta = hasta.toLocalTime();
        this.cantidad = cantidad;
    }

    public String getNombreRecurso() {
        return nombreRecurso;
    }

    public void setNombreRecurso(String nombreRecurso) {
        this.nombreRecurso = nombreRecurso;
    }

    public String getTipoRecurso() {
        return tipoRecurso;
    }

    public void setTipoRecurso(String tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }

    public String getNombreEstudiante() {
        return nombreEstudiante;
    }

    public void setNombreEstudiante(String nombreEstudiante) {
        this.nombreEstudiante = nombreEstudiante;
    }

    public String getProgramaEstudiante() {
        return programaEstudiante;
    }

    public void setProgramaEstudiante(String programaEstudiante) {
        this.programaEstudiante = programaEstudiante;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getDesde() {
        return desde;
    }

    public void setDesde(LocalTime desde) {
        this.desde = desde;
    }

    public LocalTime getHasta() {
        return hasta;
    }

    public void setHasta(LocalTime hasta) {
        this.hasta = hasta;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public long getCantidad() {
        return cantidad;
    }

    public void setCantidad(long cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Table: Recurso = " + nombreRecurso + ", tipo = " + tipoRecurso + ", estudiante = " + nombreEstudiante
                + ", programa = " + programaEstudiante + ", desde = " + desde + ", hasta = " + hasta + ", Estado = "
                + estado + ", Cantidad = " + cantidad + "]";
    }

}
