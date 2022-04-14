package edu.eci.cvds.security;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import edu.eci.cvds.services.ServicesException;

public class iniciarSesionImpl implements iniciarSesion {

    @Override
    public void login(String fullname, String password, boolean RememberMe) throws ServicesException {
        try {
            Subject user = SecurityUtils.getSubject();
            Session session = user.getSession();// crear la sesion usuario
            session.setAttribute("username", fullname);
            if(!user.isAuthenticated()){ //abrir la sesion si no tiene una activa
                UsernamePasswordToken token = new UsernamePasswordToken("username",fullname);
                token.setRememberMe(true);
                user.login(token);
            }
        } catch ( IncorrectCredentialsException a ) {
            throw new ServicesException("Wrong Password"+a);
        }catch ( UnknownAccountException a ) {
            throw new ServicesException("User not register"+a);
        } 
    }

    @Override
    public void logout() {
        Subject user = SecurityUtils.getSubject();
        user.logout();
    }

    @Override
    public boolean isLog() {
        return SecurityUtils.getSubject().isAuthenticated();
    }
    
}