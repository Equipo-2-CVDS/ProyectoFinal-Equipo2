package edu.eci.cvds.services;

import com.google.inject.Injector;
import org.mybatis.guice.XMLMyBatisModule;

import edu.eci.cvds.entities.Reserva;
import edu.eci.cvds.persistence.HorarioDAO;
import edu.eci.cvds.persistence.RecursoDAO;
import edu.eci.cvds.persistence.ReservaDAO;
import edu.eci.cvds.persistence.RolesDAO;
import edu.eci.cvds.persistence.UsuarioDAO;
import edu.eci.cvds.persistence.mybatisimpl.MyBatisHorarioDAO;
import edu.eci.cvds.persistence.mybatisimpl.MyBatisRecursoDAO;
import edu.eci.cvds.persistence.mybatisimpl.MyBatisReservaDAO;
import edu.eci.cvds.persistence.mybatisimpl.MyBatisRolesDAO;
import edu.eci.cvds.persistence.mybatisimpl.MyBatisUsuarioDAO;
import edu.eci.cvds.services.impl.ProyectoServicesImpl;

import java.sql.Timestamp;
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
               bind(RecursoDAO.class).to(MyBatisRecursoDAO.class);
               bind(HorarioDAO.class).to(MyBatisHorarioDAO.class);
               bind(ReservaDAO.class).to(MyBatisReservaDAO.class);
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
    ProyectoServices servicio = ProyectoServicesFactory.getInstance().getServiciosProyecto();
    //servicio.getHorariosDisponibles(1);
    //System.out.println(servicio.getReservasUsuario(1));
    System.out.println(servicio.getReservas());
//    System.out.println(servicio.buscarUsuario("admin"));
   // System.out.println(servicio.getHorariosDisponibles(1));
    
}

}
