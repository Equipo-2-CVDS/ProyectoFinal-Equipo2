package edu.eci.cvds.entities;

import java.sql.Timestamp;


public class UsuRecuRese {
    private String nombreRecurso;
    private String infoRecurso;
    private Timestamp fechaSolicitado;
    private Timestamp fechaDesde;
    private Timestamp fechaHasta;
    private String nombreUsuario;
    private String programaUsuario;
    private int recurrencia;

    public UsuRecuRese(String nombreRecurso,String infoRecurso,Timestamp fechaSolicitado,Timestamp fechaDesde,Timestamp fechaHasta,
    String nombreUsuario,String programaUsuario,int recurrencia){

        this.nombreRecurso = nombreRecurso;
        this.infoRecurso = infoRecurso;
        this.fechaSolicitado = fechaSolicitado;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.nombreUsuario = nombreUsuario;
        this.programaUsuario = programaUsuario;
        this.recurrencia = recurrencia;
            
    }

    public String getNombreRecurso() {
        return nombreRecurso;
    }

    public void setNombreRecurso(String nombreRecurso) {
        this.nombreRecurso = nombreRecurso;
    }

    public String getInfoRecurso() {
        return infoRecurso;
    }

    public void setInfoRecurso(String infoRecurso) {
        this.infoRecurso = infoRecurso;
    }

    public Timestamp getFechaSolicitado() {
        return fechaSolicitado;
    }

    public void setFechaSolicitado(Timestamp fechaSolicitado) {
        this.fechaSolicitado = fechaSolicitado;
    }

    public Timestamp getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Timestamp fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Timestamp getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Timestamp fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getProgramaUsuario() {
        return programaUsuario;
    }

    public void setProgramaUsuario(String programaUsuario) {
        this.programaUsuario = programaUsuario;
    }

    public int getRecurrencia() {
        return recurrencia;
    }

    public void setRecurrencia(int recurrencia) {
        this.recurrencia = recurrencia;
    }


    @Override
    public String toString() {
        return "UsuRecuRese: [nombreRecurso= " + nombreRecurso + ", infoRecurso= " + infoRecurso + 
        ", fechaSolicitado= " + fechaSolicitado +  ", fechaDesde= " + fechaDesde + ", fechaHasta= " + fechaHasta + ", nombreUsuario= " + nombreUsuario + ", programaUsuario= " + programaUsuario + ", recurrencia= " + recurrencia +"]";
    }

}
