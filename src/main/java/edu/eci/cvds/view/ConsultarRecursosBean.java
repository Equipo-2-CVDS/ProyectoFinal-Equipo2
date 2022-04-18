package edu.eci.cvds.view;

import com.google.inject.Inject;
import edu.eci.cvds.services.ProyectoServices;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "consultarRecursosBean")
@SessionScoped
public class ConsultarRecursosBean extends BasePageBean{
    @Inject
    private ProyectoServices userServices;


}
