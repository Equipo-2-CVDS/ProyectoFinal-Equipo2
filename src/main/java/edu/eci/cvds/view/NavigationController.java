package edu.eci.cvds.view;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "navigationController", eager = true)
@RequestScoped

public class NavigationController implements Serializable {
    public void paginaHome() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/home.xhtml");
    }

    public void paginaLogin() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/login.xhtml");
    }

    public void paginaInicio() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/index.xhtml");
    }

    public void paginaConsultaR() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/consultarRecurso.xhtml");
    }

    public void paginaRegistroR() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/registrarRecurso.xhtml");
    }

    public void paginarecursoR() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/consultarReserva.xhtml");
    }

    public void paginaMisReservas() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/misReservas.xhtml");
    }

    public void paginaCalendario() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/calendario.xhtml");
    }

    public void modificarRecursos() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/modificarRecursos.xhtml");
    }

    public void paginaReporte() throws IOException {
        FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/estadisticas.xhtml");
    }
}