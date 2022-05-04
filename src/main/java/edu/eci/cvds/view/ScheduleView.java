package edu.eci.cvds.view;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.google.inject.Inject;
import edu.eci.cvds.entities.Horario;
import edu.eci.cvds.services.ProyectoServices;
import edu.eci.cvds.services.ServicesException;
import org.primefaces.PrimeFaces;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;

@ManagedBean
@SessionScoped
public class ScheduleView extends BasePageBean {

    @Inject
    private ProyectoServices userServices;

    private ScheduleModel eventModel;

    private ScheduleEvent event = new DefaultScheduleEvent();

    private ScheduleEvent eventAux = new DefaultScheduleEvent();

    private int eventId = 0;

    public void loadEvents() throws ServicesException {
        eventModel = new DefaultScheduleModel();

        Object serviciosBiblioteca;
        List<Horario> horarios = userServices.getHorariosDisponibles(1);
        for (Horario r : horarios){
            event = DefaultScheduleEvent.builder()
                    .title(userServices.getRecursoPorId(r.getIdRecurso()).getTipo() + " - " + userServices.getRecursoPorId(r.getIdRecurso()).getNombre())
                    .startDate(r.getDesde())
                    .endDate(r.getHasta())
                    .description("Trees, flowers, ...")
                    .draggable(true)
                    .borderColor("#27AE60")
                    .build();
            eventModel.addEvent(event);
            event.setId(String.valueOf(r.getIdRecurso()));
        }

    }

    public ScheduleModel getEventModel() {
        return eventModel;
    }

    public void setEventModel(ScheduleModel eventModel) {
        this.eventModel = eventModel;
    }

    public void onEventSelect(SelectEvent selectEvent) {
        this.event = (ScheduleEvent) selectEvent.getObject();
        this.eventId = Integer.parseInt(event.getId());
    }

    public void onEventMove(ScheduleEntryMoveEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
        PrimeFaces.current().dialog().showMessageDynamic(message);
        addMessage(message);
    }

    private void addMessage(FacesMessage message) {
        PrimeFaces.current().dialog().showMessageDynamic(message);
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }
}