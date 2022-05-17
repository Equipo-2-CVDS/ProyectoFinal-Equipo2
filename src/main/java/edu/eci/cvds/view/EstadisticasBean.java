package edu.eci.cvds.view;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.google.inject.Inject;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import edu.eci.cvds.entities.Reserva;
import edu.eci.cvds.entities.Table;
import edu.eci.cvds.persistence.PersistenceException;
import edu.eci.cvds.services.ProyectoServices;

@ManagedBean(name = "estadisticasBean")
@SessionScoped

public class EstadisticasBean extends BasePageBean {

    @Inject
    private ProyectoServices userServices;

    private int opcion = 0;
    private BarChartModel barModel;
    private List<Table> recursosTable;
    private List<Reserva> reservasTable;

    private void inicializarBarModel() {
        try {
            barModel = new BarChartModel();
            traerInfo();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void traerInfo() throws PersistenceException {
        ChartSeries info = new ChartSeries();
        if (opcion < 2) {
            List<String> recursos = opcion == 0 ? userServices.getRecursoMasUsado()
                    : userServices.getRecursoMenosUsado();
            for (String r : recursos) {
                String[] item = r.split(",");
                info.setLabel("Recursos");
                info.set(item[0], Integer.parseInt(item[1]));
            }
        } else if (opcion < 4) {
            List<String> horarios = opcion == 2 ? userServices.getHorariosMasUsados()
                    : userServices.getHorariosMenosUsados();
            for (String h : horarios) {
                String[] item = h.split(" ");
                info.setLabel("Horarios");
                info.set(item[0] + " " + item[1], Integer.parseInt(item[2]));
            }
        } else {
            reservasTable = opcion == 4 ? userServices.getReservasRecurentes()
                    : userServices.getReservasCanceladas();
        }
        barModel.addSeries(info);
    }

    public int getOpcion() {
        return opcion;
    }

    public void setOpcion(int opcion) {
        this.opcion = opcion;
    }

    public BarChartModel getBarModel() {
        inicializarBarModel();
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

}
