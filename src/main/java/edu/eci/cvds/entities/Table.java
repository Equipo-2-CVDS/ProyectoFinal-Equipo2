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
    private String estado;
    private long cantidad;
    private String recurrencia;

    public Table(String nombreRecurso, String tipoRecurso, String nombreEstudiante, String programaEstudiante,
            Date fecha, Time desde, Time hasta, int recurrencia, boolean estado, long cantidad) {
        this.nombreRecurso = nombreRecurso;
        this.tipoRecurso = tipoRecurso;
        this.nombreEstudiante = nombreEstudiante;
        this.programaEstudiante = programaEstudiante;
        this.fecha = fecha.toLocalDate();
        this.desde = desde.toLocalTime();
        this.hasta = hasta.toLocalTime();
        this.estado = estado ? "Habilitada" : "Deshabilitada";
        this.recurrencia = recurrencia == 0 ? "Sin recurrencia"
                : recurrencia == 1 ? "Diariamente" : recurrencia == 2 ? "Semanalmente" : "Mensualmente";
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado ? "Habilitada" : "Deshabilitada";
    }

    public String getRecurrencia() {
        return recurrencia;
    }

    public void setRecurrencia(int recurrencia) {
        this.recurrencia = recurrencia == 0 ? "Sin recurrencia"
                : recurrencia == 1 ? "Diariamente" : recurrencia == 2 ? "Semanalmente" : "Mensualmente";
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
                + estado + ", Recurrencia = "
                + recurrencia + ", Cantidad = " + cantidad + "]";
    }

}
