package edu.eci.cvds.view;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import com.google.inject.Inject;

import edu.eci.cvds.entities.Horario;
import edu.eci.cvds.entities.Recurso;
import edu.eci.cvds.services.ProyectoServices;
import edu.eci.cvds.services.ServicesException;

import java.sql.Time;

@ManagedBean(name = "recursoBean")
@SessionScoped
public class RecursoBean extends BasePageBean {

    @Inject
    private ProyectoServices userServices;

    private String[] dias;
    private int id;
    private String nombre;
    private String tipo;
    private String capacidad;
    private String ubicacion;
    private boolean estado = true;
    private String desde;
    private String hasta;

    public void insertarRecurso() throws ServicesException{
        try {

            if(nombre.isEmpty() || tipo.isEmpty() || capacidad.isEmpty() || ubicacion.isEmpty() || desde.isEmpty() || hasta.isEmpty() || dias.length < 1){
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Ningun campo puede ser vacio","");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }else if(!nombre.matches("[a-zA-Z].*")){
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "El nombre debe iniciar con una letra","");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }else if(Integer.parseInt(desde) > Integer.parseInt(hasta)){
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "La hora hasta debe ser mayor a la hora desde","");
                FacesContext.getCurrentInstance().addMessage(null, message);      
            }else if(!(capacidad.matches("[1-9][0-9]*"))){
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "La capacidad debe ser un numero entero ","");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }else if(Integer.parseInt(capacidad) >= 10000){
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "La capacidad debe ser un numero menor a 10000 ","");
                FacesContext.getCurrentInstance().addMessage(null, message);
                
            }else if((userServices.getRecurso(nombre) != null)){
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "El nombre del recurso ya existe ","");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }else{
                int inicio = Integer.parseInt(desde);
                int fin = Integer.parseInt(hasta);
                int capacity = Integer.parseInt(capacidad);
                userServices.insertarRecurso(new Recurso(nombre,tipo,capacity,ubicacion,estado));
                int idRecurso = userServices.getRecurso(nombre).getId();
                for(String i : dias){
                    userServices.insertarHorario(new Horario(idRecurso, Integer.parseInt(i), new Time(inicio, 0, 0), new Time(fin, 0, 0)));
                }
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Se Agrego de forma exitosa la Oferta","");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }        
        }catch(ServicesException e){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Verifique los datos ingresados", "");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        clear();
        
    }


    public void clear() {
        dias = new String[5];
        nombre = "";
        tipo = "";
        capacidad = "";
        ubicacion = "";
        desde = "";
        hasta = "";
    }

    public String getDesde() {
        return desde;
    }

    public void setDesde(String desde) {
        this.desde = desde;
    }

    public String getHasta() {
        return hasta;
    }

    public void setHasta(String hasta) {
        this.hasta = hasta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(String capacidad) {
        this.capacidad = capacidad;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String[] getDias() {
        return dias;
    }

    public void setDias(String[] dias) {
        this.dias = dias;
    }

}
