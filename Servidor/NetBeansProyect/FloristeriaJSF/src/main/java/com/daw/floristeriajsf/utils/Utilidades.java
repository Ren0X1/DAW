package com.daw.floristeriajsf.utils;

import com.daw.floristeriajsf.DAO.CarritoJpaController;
import com.daw.floristeriajsf.DAO.FloresJpaController;
import com.daw.floristeriajsf.DAO.LuzJpaController;
import com.daw.floristeriajsf.DAO.UsuariosJpaController;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Utilidades {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("floristeriaPU1");
    private final CarritoJpaController ctrCarrito = new CarritoJpaController(emf);
    private final FloresJpaController ctrFlores = new FloresJpaController(emf);
    private final LuzJpaController ctrLuz = new LuzJpaController(emf);
    private final UsuariosJpaController ctrUsuarios = new UsuariosJpaController(emf);

    public CarritoJpaController getCtrCarrito() {
        return ctrCarrito;
    }

    public FloresJpaController getCtrFlores() {
        return ctrFlores;
    }

    public LuzJpaController getCtrLuz() {
        return ctrLuz;
    }

    public UsuariosJpaController getCtrUsuarios() {
        return ctrUsuarios;
    }
    
    
}
