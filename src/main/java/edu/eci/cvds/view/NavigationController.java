package edu.eci.cvds.view;

import java.io.Serializable;

import javax.faces.bean.ManagedBean; 
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "navigationController", eager = true)
@RequestScoped

public class NavigationController implements Serializable {
    public String paginaHome(){
        return "home";
    }
    public String paginaLogin(){
        return "login";
    }
    public String paginaInicio(){
        return "index";
    }
    public String paginaConsultaR(){
        return null;
    }
    public String paginaRegistroR(){
        return null;
    }
}
