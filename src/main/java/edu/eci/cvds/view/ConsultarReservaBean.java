package edu.eci.cvds.view;

import com.google.inject.Inject;
import edu.eci.cvds.entities.Horario;
import edu.eci.cvds.entities.Recurso;
import edu.eci.cvds.entities.Reserva;
import edu.eci.cvds.entities.Usuario;
import edu.eci.cvds.services.ProyectoServices;
import edu.eci.cvds.services.ServicesException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "consultarReservaBean")
@SessionScoped
public class ConsultarReservaBean extends BasePageBean{
    @Inject
    private ProyectoServices userServices;
    private List<Reserva> reserva;


    public void consultarReservas(){
        try{
            reserva = userServices.getReservas();
        } catch(Exception e){
            System.out.println(e);
        }
    }

    public void consultarReservasUsuario(String usuario){
        try{
            Usuario user = userServices.buscarUsuario(usuario);
            if(user.getRol() == 1){
                consultarReservas();
            }else{
                reserva = userServices.getReservasUsuario(user.getId());
            }
            
        } catch(Exception e){
            System.out.println(e);
        }
    }

    public List<Reserva> getReserva() {
        return reserva;
    }

    public void setReserva(List<Reserva> reserva) {
        this.reserva = reserva;
    }
}
