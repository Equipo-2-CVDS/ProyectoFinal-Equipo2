package edu.eci.cvds.guice;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import edu.eci.cvds.persistence.HorarioDAO;
import edu.eci.cvds.persistence.RecursoDAO;
import edu.eci.cvds.persistence.mybatisimpl.MyBatisHorarioDAO;
import edu.eci.cvds.persistence.mybatisimpl.MyBatisRecursoDAO;
import org.mybatis.guice.XMLMyBatisModule;
import org.mybatis.guice.datasource.helper.JdbcHelper;

import edu.eci.cvds.persistence.RolesDAO;
import edu.eci.cvds.persistence.UsuarioDAO;
import edu.eci.cvds.persistence.mybatisimpl.MyBatisRolesDAO;
import edu.eci.cvds.persistence.mybatisimpl.MyBatisUsuarioDAO;
import edu.eci.cvds.services.ProyectoServices;
import edu.eci.cvds.services.impl.ProyectoServicesImpl;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class GuiceContextListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		ServletContext servletContext = servletContextEvent.getServletContext();
		servletContext.removeAttribute(Injector.class.getName());
	}

	public void contextInitialized(ServletContextEvent servletContextEvent) {
		Injector injector = Guice.createInjector(new XMLMyBatisModule() {
			@Override
			protected void initialize() {

				install(JdbcHelper.PostgreSQL);
				setEnvironmentId("development");
				setClassPathResource("mybatis-config.xml");
				bind(UsuarioDAO.class).to(MyBatisUsuarioDAO.class);
				bind(ProyectoServices.class).to(ProyectoServicesImpl.class);
				bind(RolesDAO.class).to(MyBatisRolesDAO.class);
				bind(RecursoDAO.class).to(MyBatisRecursoDAO.class);
				bind(HorarioDAO.class).to(MyBatisHorarioDAO.class);
			}
		}

		);

		ServletContext servletContext = servletContextEvent.getServletContext();
		servletContext.setAttribute(Injector.class.getName(), injector);
	}

}