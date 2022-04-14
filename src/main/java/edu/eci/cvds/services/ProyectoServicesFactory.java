package edu.eci.cvds.services;

import com.google.inject.Injector;
import org.mybatis.guice.XMLMyBatisModule;

import edu.eci.cvds.persistence.RolesDAO;
import edu.eci.cvds.persistence.UsuarioDAO;
import edu.eci.cvds.persistence.mybatisimpl.MyBatisRolesDAO;
import edu.eci.cvds.persistence.mybatisimpl.MyBatisUsuarioDAO;
import edu.eci.cvds.services.impl.ProyectoServicesImpl;

import java.util.Optional;

import static com.google.inject.Guice.createInjector;


public class ProyectoServicesFactory {

   private static final ProyectoServicesFactory instance = new ProyectoServicesFactory();

   private static Optional<Injector> optInjector;

   private Injector myBatisInjector(String env, String pathResource) {
       return createInjector(new XMLMyBatisModule() {
           @Override
           protected void initialize() {
               setEnvironmentId(env);
               setClassPathResource(pathResource);
               bind(UsuarioDAO.class).to(MyBatisUsuarioDAO.class);
               bind(RolesDAO.class).to(MyBatisRolesDAO.class);
			   bind(ProyectoServices.class).to(ProyectoServicesImpl.class);
           }
       });
   }

   private ProyectoServicesFactory(){
       optInjector = Optional.empty();
   }

   public ProyectoServices getServiciosProyecto(){
       if (!optInjector.isPresent()) {
           optInjector = Optional.of(myBatisInjector("development","mybatis-config.xml"));
       }
       return optInjector.get().getInstance(ProyectoServices.class);
   }


   public ProyectoServices getServiciosProyectoTesting(){
       if (!optInjector.isPresent()) {
           optInjector = Optional.of(myBatisInjector("test","mybatis-config-h2.xml"));
       }
       return optInjector.get().getInstance(ProyectoServices.class);
   }


   public static ProyectoServicesFactory getInstance(){
       return instance;
   }

   public static void main(String a[]) throws ServicesException {
    System.out.println(ProyectoServicesFactory.getInstance().getServiciosProyecto().buscarUsuario("prueba"));
}

}
