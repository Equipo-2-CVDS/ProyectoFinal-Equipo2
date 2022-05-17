package edu.eci.cvds.view;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.google.inject.Inject;

import org.codehaus.plexus.util.StringUtils;
import org.primefaces.model.FilterMeta;
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
    private List<FilterMeta> filterBy;
    private Date dateFrom;
    private Date dateTo;

    public List<Table> consultarTable() {
        try {
            table = userServices.getTable();
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
        }
        barModel.addSeries(info);
    }

    public boolean filterByDate(Object value, Object filter, Locale locale) {
        // it fails before calling this method
        String filterText = (filter == null) ? null : filter.toString().trim();
        if (StringUtils.isEmpty(filterText)) {
            return true;
        }
        if (value == null) {
            return false;
        }
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date filterDate;
        try {
            filterDate = df.parse(filterText);
        } catch (ParseException e) {
            return false;
        }
        return filterDate.after(dateFrom) && filterDate.before(dateTo);
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
        System.out.println(tableFilter.get(0).getCantidad());
        this.tableFilter = tableFilter;
    }

    public List<FilterMeta> getFilterBy() {
        return filterBy;
    }

    public void setFilterBy(List<FilterMeta> filterBy) {
        this.filterBy = filterBy;
    }

}
