package edu.eci.cvds.security;

import edu.eci.cvds.persistence.PersistenceException;

public interface iniciarSesion {
    public void login(String fullName, String password, boolean RememberMe) throws PersistenceException;

    public void logout();

    public boolean isLog();
}