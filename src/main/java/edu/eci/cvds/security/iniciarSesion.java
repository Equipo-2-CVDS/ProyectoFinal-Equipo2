package edu.eci.cvds.security;

import edu.eci.cvds.services.ServicesException;

public interface iniciarSesion {
    public void login(String fullName,String password, boolean RememberMe) throws ServicesException;
    public void logout();
    public boolean isLog();
}