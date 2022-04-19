package edu.eci.cvds.view;

import com.google.inject.Inject;
import edu.eci.cvds.entities.Horario;
import edu.eci.cvds.entities.Recurso;
import edu.eci.cvds.services.ProyectoServices;
import edu.eci.cvds.services.ServicesException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.sql.Time;
import java.util.List;

@ManagedBean(name = "consultarRecursosBean")
@SessionScoped
public class ConsultarRecursosBean extends BasePageBean{
    @Inject
    private ProyectoServices userServices;

    public List<Recurso> consultarRecursos() throws ServicesException {
            return userServices.getRecursosDisponibles();
    }


}
