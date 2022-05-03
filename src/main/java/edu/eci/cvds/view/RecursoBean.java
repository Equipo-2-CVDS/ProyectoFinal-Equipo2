package edu.eci.cvds.view;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

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

    private int nose;
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
                messageError("Ningun campo puede ser vacio"); 
            }else if(!nombre.matches("[a-zA-Z].*")){
                messageError("El nombre debe iniciar con una letra"); 
            }else if(Integer.parseInt(desde) > Integer.parseInt(hasta)){
                messageError("La hora hasta debe ser mayor a la hora desde");       
            }else if(!(capacidad.matches("[1-9][0-9]*"))){
                messageError("La capacidad debe ser un numero entero");  
            }else if(Integer.parseInt(capacidad) >= 10000){
                messageError("La capacidad debe ser un numero menor a 10000");               
            }else if((userServices.getRecurso(nombre) != null)){
                messageError("El nombre del recurso ya existe");
            }else{
                int inicio = Integer.parseInt(desde);
                int fin = Integer.parseInt(hasta);
                int capacity = Integer.parseInt(capacidad);
                userServices.insertarRecurso(new Recurso(nombre,tipo,capacity,ubicacion,estado));
                int idRecurso = userServices.getRecurso(nombre).getId();
                for(String i : dias){
                    userServices.insertarHorario(new Horario(idRecurso, Integer.parseInt(i), new Time(inicio, 0, 0), new Time(fin, 0, 0)));
                }
                messageError("Se Agrego de forma exitosa la Oferta");
            }        
        }catch(ServicesException e){
            messageError("Verifique los datos ingresados");
        }
        clear();      
    }

    /**
     * Lanza el mensaje de error
     * @param message
     */
    private void messageError(String message) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message,""));
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
