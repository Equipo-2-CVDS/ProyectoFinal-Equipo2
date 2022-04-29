package edu.eci.cvds.entities;

import java.sql.Timestamp;

public class Reserva {
    private int id;
    private int idUsuario;
    private int idRecurso;
    private Timestamp desde;
    private Timestamp hasta;
    private int recurrencia;

    public Reserva(int id, int idUsuario, int idRecurso, Timestamp desde, Timestamp hasta, int recurrencia) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idRecurso = idRecurso;
        this.desde = desde;
        this.hasta = hasta;
        this.recurrencia = recurrencia;
    }

    public Reserva(int idUsuario, int idRecurso, Timestamp desde, Timestamp hasta, int recurrencia) {
        this.idUsuario = idUsuario;
        this.idRecurso = idRecurso;
        this.desde = desde;
        this.hasta = hasta;
        this.recurrencia = recurrencia;
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
    
    public int getRecurrencia() {
        return recurrencia;
    }

    public void setRecurrencia(int recurrencia) {
        this.recurrencia = recurrencia;
    }

    @Override
    public String toString() {
        return "Recurso: [id=" + id + ", idUsuario=" + idUsuario + 
        ", idRecurso=" + idRecurso +  ", desde=" + desde + ", hasta=" + hasta + ", recurrencia=" + recurrencia +"]";
    }
}
