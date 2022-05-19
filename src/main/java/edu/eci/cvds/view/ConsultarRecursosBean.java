package edu.eci.cvds.view;

import com.google.inject.Inject;
import edu.eci.cvds.entities.Recurso;
import edu.eci.cvds.persistence.PersistenceException;
import edu.eci.cvds.services.ProyectoServices;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "consultarRecursosBean")
@SessionScoped
public class ConsultarRecursosBean extends BasePageBean {
    @Inject
    private ProyectoServices userServices;

    private ArrayList<Recurso> filtro = new ArrayList<>();
    private List<Recurso> recursos;



    public List<Recurso> getRecursosDisponibles() {
        try {
            recursos = userServices.getRecursosDisponibles();
        } catch (Exception e) {
            System.out.println(e);
        }
        return recursos;
    }

    public List<Recurso> getRecursos() {
        try {
            recursos = userServices.getRecursos();
        } catch (Exception e) {
            System.out.println(e);
        }
        return recursos;
    }

    public void cambiarEstadoRecurso(int id, boolean estado) throws IOException {
        try {
            userServices.cambiarEstadoRecurso(id, estado);
        } catch (PersistenceException e){
            FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/confirmacionCancelarRecurso.xhtml");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void cancelacionConfirmada(int id) throws IOException {
        try {
            userServices.cancelacionConfirmada(id);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public String getHabilitar(boolean habilitado){
        if(habilitado){
            return "Desabilitar";
        } else {
            return "Habilitar";
        }
    }

    public String getHabilitado(boolean estado){
        if(estado){
            return "Disponible";
        } else {
            return "No disponible";
        }
    }


    public void volver(int ingresado) throws IOException {
        if (ingresado > 0) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/home.xhtml");
        } else {
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
