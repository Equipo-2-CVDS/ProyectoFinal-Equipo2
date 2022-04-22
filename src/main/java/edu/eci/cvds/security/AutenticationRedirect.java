package edu.eci.cvds.security;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

public class AutenticationRedirect extends AccessControlFilter  {

    String welcomeurl="/faces/home.xhtml";
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)throws Exception {
            Subject subject = getSubject(request, response);
            return !subject.isAuthenticated();         
    }

    
    public String getWelcomeurl() {
        return welcomeurl;
    }


    public void setWelcomeurl(String welcomeurl) {
        this.welcomeurl = welcomeurl;
    }


    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        WebUtils.issueRedirect(request, response, welcomeurl);
        return false;
    }
}
