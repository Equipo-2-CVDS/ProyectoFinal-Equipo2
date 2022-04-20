package edu.eci.cvds.view;

import java.io.IOException;
import java.io.Serializable;

import javax.faces.bean.ManagedBean; 
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;


@ManagedBean(name = "navigationController", eager = true)
@RequestScoped

public class NavigationController implements Serializable {
    public void paginaHome() throws IOException{
        FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/home.xhtml");
    }
    public void paginaLogin() throws IOException{
        FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/login.xhtml");
    }
    public void paginaInicio() throws IOException{
        FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/index.xhtml");
    }
    public void paginaConsultaR() throws IOException{
        
    }
    public void paginaRegistroR() throws IOException{
        FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/registrarRecurso.xhtml");
    }
}