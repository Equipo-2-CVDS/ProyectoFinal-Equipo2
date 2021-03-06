package edu.eci.cvds.view;

import java.io.Serializable;
import javax.annotation.PostConstruct;

import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;
import com.google.inject.Injector;

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
    public void init() { getInjector().injectMembers(this);}
}