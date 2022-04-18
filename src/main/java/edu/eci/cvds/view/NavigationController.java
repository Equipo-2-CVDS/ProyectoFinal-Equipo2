package edu.eci.cvds.view;

import java.io.Serializable;

import javax.faces.bean.ManagedBean; 
import javax.faces.bean.RequestScoped;
/*
F
 */
@ManagedBean(name = "navigationController", eager = true)
@RequestScoped

public class NavigationController extends BasePageBean {
    public String paginaHome(){
        return "home";
    }
    public String paginaLogin(){
        return "login";
    }
    public String paginaInicio(){
        return "index";
    }
    public String paginaConsultaR(){return "consultaR";}
    public String paginaRegistroR(){
        return "registrarRecurso";
    }
}
