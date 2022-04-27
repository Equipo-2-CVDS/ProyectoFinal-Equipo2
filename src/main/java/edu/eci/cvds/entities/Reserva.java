package edu.eci.cvds.entities;

import java.sql.Timestamp;

public class Reserva {
    private int id;
    private int idUsuario;
    private int idRecurso;
    private Timestamp desde;
    private Timestamp hasta;

    public Reserva(int id, int idUsuario, int idRecurso, Timestamp desde, Timestamp hasta) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idRecurso = idRecurso;
        this.desde = desde;
        this.hasta = hasta;
    }

    public Reserva(int idUsuario, int idRecurso, Timestamp desde, Timestamp hasta) {
        this.idUsuario = idUsuario;
        this.idRecurso = idRecurso;
        this.desde = desde;
        this.hasta = hasta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(int idRecurso) {
        this.idRecurso = idRecurso;
    }

    public Timestamp getDesde() {
        return desde;
    }

    public void setDesde(Timestamp desde) {
        this.desde = desde;
    }

    public Timestamp getHasta() {
        return hasta;
    }

    public void setHasta(Timestamp hasta) {
        this.hasta = hasta;
    }
    
    @Override
    public String toString() {
        return "Recurso: [id=" + id + ", idUsuario=" + idUsuario + 
        ", idRecurso=" + idRecurso +  ", desde=" + desde + ", hasta=" + hasta +"]";
    }
}
