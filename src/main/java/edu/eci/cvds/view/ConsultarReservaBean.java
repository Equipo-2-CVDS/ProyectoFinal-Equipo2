package edu.eci.cvds.view;

import com.google.inject.Inject;
import edu.eci.cvds.entities.Recurso;
import edu.eci.cvds.entities.Reserva;
import edu.eci.cvds.entities.UsuRecuRese;
import edu.eci.cvds.entities.Usuario;
import edu.eci.cvds.services.ProyectoServices;
import edu.eci.cvds.services.ServicesException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "consultarReservaBean")
@SessionScoped
public class ConsultarReservaBean extends BasePageBean{
    @Inject
    private ProyectoServices userServices;

    private ArrayList<UsuRecuRese> filtro = new ArrayList<>();
    private UsuRecuRese usuRecuRese;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void consultarReservas(){
        try{
            usuRecuRese = userServices.getUsuRecuRese(id);
        } catch(Exception e){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Hubo un error, vuelve a intentarlo",""));
        }
    }
    
    public void load(int id) throws IOException{
        setId(id);
        consultarReservas();
        FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/consultarReserva.xhtml");
    }


    public ArrayList<UsuRecuRese> getFiltro() {
        return filtro;
    }

    public void setFiltro(ArrayList<UsuRecuRese> filtro) {
        this.filtro = filtro;
    }

    public UsuRecuRese getUsuRecuRese() {
        consultarReservas();
        return usuRecuRese;
    }

    public void setUsuRecuRese(UsuRecuRese usuRecuRese) {
        this.usuRecuRese = usuRecuRese;
    }
}
