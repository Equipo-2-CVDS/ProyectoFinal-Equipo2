package edu.eci.cvds.view;

import com.google.inject.Inject;
import edu.eci.cvds.entities.Recurso;
import edu.eci.cvds.entities.Reserva;
import edu.eci.cvds.entities.UsuRecuRese;
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

    private ArrayList<UsuRecuRese> filtro = new ArrayList<>();
    private List<UsuRecuRese> usuRecuRese;


    public void consultarReservas(){
        try{
            usuRecuRese = userServices.getUsuRecuRese();
        } catch(Exception e){
            System.out.println(e);
        }
    }

    public void consultarReservasUsuario(String usuario){
        try{
            Usuario user = userServices.buscarUsuario(usuario);
            if(user.getRol() == 1){
                consultarReservas();
            }//else{
                //usuRecuRese = userServices.getReservasUsuario(user.getId());
           // }
            
        } catch(Exception e){
            System.out.println(e);
        }
    }

    public ArrayList<UsuRecuRese> getFiltro() {
        return filtro;
    }

    public void setFiltro(ArrayList<UsuRecuRese> filtro) {
        this.filtro = filtro;
    }

    public List<UsuRecuRese> getUsuRecuRese() {
        consultarReservas();
        return usuRecuRese;
    }

    public void setUsuRecuRese(List<UsuRecuRese> usuRecuRese) {
        this.usuRecuRese = usuRecuRese;
    }
}
