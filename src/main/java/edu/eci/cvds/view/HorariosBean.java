package edu.eci.cvds.view;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.text.SimpleDateFormat;
import java.util.Date;

@ManagedBean(name = "horarioBean")
@SessionScoped

public class HorariosBean {
    private int idRecurso;
    private int idDia;
    private int desde;
    private int hasta;

    public void consultarHorario(Date fecha){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
        System.out.println(dateFormat.format(fecha));
    }
}
