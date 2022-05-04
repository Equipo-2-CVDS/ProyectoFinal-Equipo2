package edu.eci.cvds.view;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.google.inject.Inject;
import edu.eci.cvds.entities.Horario;
import edu.eci.cvds.entities.Reserva;
import edu.eci.cvds.services.ProyectoServices;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

@ManagedBean(name = "scheduleView")
@SessionScoped
public class ScheduleView extends BasePageBean {

    @Inject
    private ProyectoServices userServices;

    private int idRecurso;
    private ScheduleModel eventModel;
    private ScheduleEvent<?> event = new DefaultScheduleEvent<>();
    private LocalDateTime desde;
    private LocalDateTime hasta;

    public void inicializar(int id) throws IOException{
        this.idRecurso = id;
        eventModel = new DefaultScheduleModel();
        loadEventos();
        FacesContext.getCurrentInstance().getExternalContext().redirect("/faces/calendario.xhtml");
    }

    private void loadEventos() {
        try {
            List<Horario> horarios = userServices.getHorariosDisponibles(idRecurso);
            LocalDateTime hoy = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
            for (int i = 0; i < 30; i++) {
                for (Horario horario : horarios) {
                    LocalDateTime dia = hoy.plusDays(i);
                    if (horario.getIdDia() == dia.getDayOfWeek().getValue()) {
                        LocalDateTime desde = dia.plusHours(horario.getDesde().getHours());
                        LocalDateTime hasta = dia.plusHours(horario.getHasta().getHours());
                        DefaultScheduleEvent<?> event1 = DefaultScheduleEvent.builder()
                                .title(dia.getDayOfWeek().name())
                                .startDate(desde)
                                .endDate(hasta)
                                .borderColor("orange")
                                .overlapAllowed(true)
                                .id(dia.getDayOfWeek().name())
                                .build();
                        eventModel.addEvent(event1);
                    }
                }
            }
            List<Reserva> reservas = userServices.getReservasRecurso(idRecurso);
            for (Reserva reserva : reservas) {
                LocalDateTime desde = reserva.getDesde().toLocalDateTime();
                LocalDateTime hasta = reserva.getHasta().toLocalDateTime();
                if (desde.isAfter(hoy)) {
                    DefaultScheduleEvent<?> event1 = DefaultScheduleEvent.builder()
                            .title("Reserva")
                            .startDate(desde)
                            .endDate(hasta)
                            .borderColor("blue")
                            .overlapAllowed(true)
                            .build();
                    eventModel.addEvent(event1);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public void setEventModel(ScheduleModel eventModel) {
        this.eventModel = eventModel;
    }

    public ScheduleEvent<?> getEvent() {
        return event;
    }

    public void setEvent(ScheduleEvent<?> event) {
        this.event = event;
    }

    public void onEventSelect(SelectEvent selectEvent) {
        this.event = (ScheduleEvent<?>) selectEvent.getObject();
        this.desde = event.getStartDate();
        this.hasta = event.getStartDate();
    }

    public int getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(int idRecurso) {
        this.idRecurso = idRecurso;
    }

    public LocalDateTime getDesde() {
        return desde;
    }

    public void setDesde(LocalDateTime desde) {
        this.desde = desde;
    }

    public LocalDateTime getHasta() {
        return hasta;
    }

    public void setHasta(LocalDateTime hasta) {
        this.hasta = hasta;
    }

}