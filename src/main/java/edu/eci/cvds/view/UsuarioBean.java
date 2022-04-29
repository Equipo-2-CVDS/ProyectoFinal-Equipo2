package edu.eci.cvds.view;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.google.inject.Inject;

import org.slf4j.LoggerFactory;

import edu.eci.cvds.entities.Usuario;
import edu.eci.cvds.services.ProyectoServices;
import edu.eci.cvds.services.ServicesException;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import java.util.logging.Level;

import java.io.IOException;

@ManagedBean(name = "userBean")
@SessionScoped
public class UsuarioBean extends BasePageBean {

    @Inject
    private ProyectoServices userServices;

    private String password;
    public int userId;
    private String username;
    private int rol;
    Subject subject;
    private String redirectUrl = "/faces/login.xhtml";

    /**
     * Es usado para controlar la funcionalidad de iniciar sesion desde la interfaz
     * 
     * @return String
     * @throws ServicesException controlador de excepciones
     */
    public void ingresarSesion() throws Exception {
        Logger log = LoggerFactory.getLogger(ProyectoServices.class);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        subject = SecurityUtils.getSubject();
        try {
            Usuario usuario = userServices.buscarUsuario(username);
            if (usuario != null) {
                userId = usuario.getId();
                rol = usuario.getRol();
                subject.login(token);
                FacesContext.getCurrentInstance().getExternalContext().redirect("home.xhtml");
            } else {
                messageError("El usuario no existe");
            }
        } catch (UnknownAccountException ex) {
            messageError("Esta cuenta no existe");
            log.error(ex.getMessage(), ex);
        } catch (IncorrectCredentialsException ex) {
            messageError("Contraseña incorrecta");
            log.error(ex.getMessage(), ex);
        } catch (LockedAccountException ex) {
            messageError("La Cuenta esta Bloqueada");
            log.error(ex.getMessage(), ex);
        } catch (AuthenticationException ex) {
            messageError("Error desconocido: " + ex.getMessage());
            log.error(ex.getMessage(), ex);
        } finally {
            token.clear();
        }

    }

    /**
     * Cierra la sesion
     */
    public void cerrarSesion() {
        SecurityUtils.getSubject().logout();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect(redirectUrl);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(UsuarioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String visible() {
        String res = "";
        try {
            res = (userServices.buscarUsuario(username).getRol() == 1) ? "True" : "None";
        } catch (ServicesException e) {
            res = "None";
        }

        return res;
    }

    /**
     * Devuelve un valor si el estudiante
     * 
     * @return
     */
    public String isEstudiante() {
        String res = "";
        try {
            res = (userServices.buscarUsuario(username).getRol() == 2) ? "True" : "None";
        } catch (ServicesException e) {
            res = "None";
        }

        return res;
    }

    /**
     * Lanza el mensaje de error
     * 
     * @param message
     */
    private void messageError(String message) {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Error", message));
    }

    /**
     * Devuelve el customerId
     * 
     * @return
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Asigna el customerId
     * 
     * @param userId
     */
    public void setcustomerId(int userId) {
        this.userId = userId;
    }

    /**
     * Devuelve el username
     * 
     * @return
     */
    public String getUsername() {
        return username;
    }

    /***
     * Asigna el username
     * 
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Devuelve el rol
     * 
     * @return
     */
    public int getRol() {
        return rol;
    }

    /**
     * Asigna el rol
     * 
     * @param rol
     */
    public void setRol(int rol) {
        this.rol = rol;
    }

    /***
     * Devuelve el userServices
     * 
     * @return
     */
    public ProyectoServices getUserServices() {
        return this.userServices;
    }

    /**
     * Asigna el userServices
     * 
     * @param userServices
     */
    public void setUserServices(ProyectoServices userServices) {
        this.userServices = userServices;
    }

    /**
     * Devuelve la contraseña
     * 
     * @return
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Asigna la contraseña
     * 
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
