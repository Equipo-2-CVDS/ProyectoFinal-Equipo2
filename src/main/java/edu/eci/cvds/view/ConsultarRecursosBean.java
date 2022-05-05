package edu.eci.cvds.view;

import com.google.inject.Inject;
import edu.eci.cvds.entities.Horario;
import edu.eci.cvds.entities.Recurso;
import edu.eci.cvds.security.iniciarSesion;
import edu.eci.cvds.security.iniciarSesionImpl;
import edu.eci.cvds.services.ProyectoServices;
import edu.eci.cvds.services.ServicesException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "consultarRecursosBean")
@SessionScoped
public class ConsultarRecursosBean extends BasePageBean{
    @Inject
    private ProyectoServices userServices;

    private iniciarSesion iniciar;
    private NavigationController navigationController;

    private ArrayList<Recurso> filtro = new ArrayList<>();
    private List<Recurso> recursos;

    public void inicializar(){
        try{
            recursos = userServices.getRecursosDisponibles();
        } catch(Exception e){
            System.out.println(e);
        }
    }

    public List<Recurso> getRecursos() {
        inicializar();
        return recursos;
    }

    public void volver(int ingresado) throws IOException {
        if (ingresado > 0){
            FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/home.xhtml");
        }
        else{
            FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/index.xhtml");
        }
    }
    public void setRecursos(List<Recurso> recursos) {
        this.recursos = recursos;
    }

    public ArrayList<Recurso> getFiltro() {
        return filtro;
    }

    public void setFiltro(ArrayList<Recurso> filtro) {
        this.filtro = filtro;
    }
}
