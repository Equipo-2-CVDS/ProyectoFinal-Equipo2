package edu.eci.cvds.view;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map.Entry;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.google.inject.Inject;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

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
    private List<Table> table = new ArrayList<Table>();
    private List<Table> tableFilter = new ArrayList<Table>();
    private String nomDic;
    private int cantDic;

    public List<Table> consultarTable() {
        try {
            table = userServices.getTable();
            if (tableFilter.isEmpty()) {
                tableFilter = table;
            }
            inicializarBarModel();
        } catch (PersistenceException e) {
            System.out.println(e);
        }
        return table;
    }

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
        Hashtable<String, Integer> recursosXCantidad = new Hashtable<String, Integer>();
        List<Table> uso = tableFilter.isEmpty() ? table : tableFilter;
        for (Table t : uso) {
            nombres(t);
            if (recursosXCantidad.containsKey(nomDic)) {
                recursosXCantidad.replace(nomDic, recursosXCantidad.get(nomDic) + (cantDic));
            } else {
                recursosXCantidad.put(nomDic, cantDic);
            }
        }
        for (Entry<String, Integer> entry : recursosXCantidad.entrySet()) {
            info.set(entry.getKey(), entry.getValue());
        }
        barModel.addSeries(info);
        tableFilter = new ArrayList<Table>();
    }

    private void nombres(Table t) {
        switch (opcion) {
            case 0:
                this.nomDic = t.getNombreRecurso();
                this.cantDic = t.getCantidad();
                break;
            case 1:
                this.nomDic = t.getDesde().toString() + " - " + t.getHasta().toString();
                this.cantDic = t.getCantidad();
                break;
            case 2:
                this.nomDic = t.getProgramaEstudiante();
                this.cantDic = t.getCantidad();
                break;
            case 3:
                this.nomDic = t.getTipoRecurso();
                this.cantDic = t.getCantidad();
                break;
            case 4:
                this.nomDic = t.getNombreEstudiante();
                this.cantDic = t.getCantidad();
                break;
            case 5:
                this.nomDic = t.getRecurrencia();
                this.cantDic = t.getCantidad();
                break;
            case 6:
                this.nomDic = t.getEstado();
                this.cantDic = t.getCantidad();
                break;
            default:
                break;
        }

    }

    public int getOpcion() {
        return opcion;
    }

    public void setOpcion(int opcion) {
        this.opcion = opcion;
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }

    public List<Table> getTable() {
        return table;
    }

    public void setTable(List<Table> table) {
        this.table = table;
    }

    public List<Table> getTableFilter() {
        return tableFilter;
    }

    public void setTableFilter(List<Table> tableFilter) {
        this.tableFilter = tableFilter;
    }

}
