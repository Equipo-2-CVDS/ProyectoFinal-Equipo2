package edu.eci.cvds.persistence.mybatisimpl;

import com.google.inject.Inject;

import edu.eci.cvds.persistence.PersistenceException;
import edu.eci.cvds.persistence.RolesDAO;
import edu.eci.cvds.persistence.mybatisimpl.mappers.RolesMapper;

public class MyBatisRolesDAO implements RolesDAO {
    @Inject
    RolesMapper rolMapper;


    @Override
    public String getRol(int id) throws PersistenceException {
        try {
            return rolMapper.consultarRol(id).getNombre();
        } catch (Exception e) {
            throw new PersistenceException("Error al consultar", e);
        }  
    }       
}
