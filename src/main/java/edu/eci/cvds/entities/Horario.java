package edu.eci.cvds.entities;

import java.sql.Time;

public class Horario {
    private int idRecurso;
    private int idDia;
    private Time desde;
    private Time hasta; 

    public Horario (int idRecurso, int idDia, Time desde, Time hasta){
        this.idRecurso = idRecurso;
        this.idDia = idDia;
        this.desde = desde;
        this.hasta = hasta;
    }

    public int getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(int idRecurso) {
        this.idRecurso = idRecurso;
    }

    public int getIdDia() {
        return idDia;
    }

    public void setIdDia(int idDia) {
        this.idDia = idDia;
    }

    public Time getDesde() {
        return desde;
    }

    public void setDesde(Time desde) {
        this.desde = desde;
    }

    public Time getHasta() {
        return hasta;
    }

    public void setHasta(Time hasta) {
        this.hasta = hasta;
    }

    @Override
    public String toString() {
        return "Horario: [idRecurso=" + idRecurso + ", idDia=" + idDia + ", desde=" + desde +  ", hasta=" + hasta +"]";
    }
}
