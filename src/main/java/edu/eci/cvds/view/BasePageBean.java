package edu.eci.cvds.view;

import java.io.Serializable;
import javax.annotation.PostConstruct;

import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;
import com.google.inject.Injector;
import edu.eci.cvds.services.ServicesException;

@Named

public abstract class BasePageBean implements Serializable {

    private Injector injector;
    private static final long serialVersionUID = 1L;

    public Injector getInjector() {
        if (injector == null) {
            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            injector = (Injector) servletContext.getAttribute(Injector.class.getName());
        }
        return injector;
    }

    public void setInjector(Injector injector) {
        this.injector = injector;
    }

    @PostConstruct
    public void init() throws ServicesException { getInjector().injectMembers(this);}
}