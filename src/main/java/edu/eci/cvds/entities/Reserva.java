package edu.eci.cvds.entities;

import java.sql.Timestamp;

public class Reserva {
    private int id;
    private int idUsuario;
    private int idRecurso;
    private Timestamp desde;
    private Timestamp hasta;
    private Timestamp fechaSolicitado;
    private int recurrencia;
    private boolean estado;

    public Reserva(int id, int idUsuario, int idRecurso, Timestamp desde, Timestamp hasta, Timestamp fechaSolicitado,
            int recurrencia, boolean estado) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idRecurso = idRecurso;
        this.desde = desde;
        this.hasta = hasta;
        this.recurrencia = recurrencia;
        this.fechaSolicitado = fechaSolicitado;
        this.estado = estado;
    }

    public Reserva(int idUsuario, int idRecurso, Timestamp desde, Timestamp hasta, int recurrencia, boolean estado) {
        this.idUsuario = idUsuario;
        this.idRecurso = idRecurso;
        this.desde = desde;
        this.hasta = hasta;
        this.recurrencia = recurrencia;
        this.estado = estado;
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

    public Timestamp getFechaSolicitado() {
        return fechaSolicitado;
    }

    public void setFechaSolicitado(Timestamp fechaSolicitado) {
        this.fechaSolicitado = fechaSolicitado;
    }

    public int getRecurrencia() {
        return recurrencia;
    }

    public void setRecurrencia(int recurrencia) {
        this.recurrencia = recurrencia;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Reserva: [id=" + id + ", idUsuario=" + idUsuario +
                ", idReserva=" + idRecurso + ", desde=" + desde + ", hasta=" + hasta + ", estado=" + estado + "]";
    }
}
