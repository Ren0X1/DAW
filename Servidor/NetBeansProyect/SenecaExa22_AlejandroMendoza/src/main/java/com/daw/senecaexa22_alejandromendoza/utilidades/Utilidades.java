package com.daw.senecaexa22_alejandromendoza.utilidades;

import com.daw.senecaexa22_alejandromendoza.DAO.AlumasigJpaController;
import com.daw.senecaexa22_alejandromendoza.DAO.AlumnoJpaController;
import com.daw.senecaexa22_alejandromendoza.DAO.AsignaturaJpaController;
import com.daw.senecaexa22_alejandromendoza.DAO.CursosJpaController;
import com.daw.senecaexa22_alejandromendoza.DAO.DetallenotaJpaController;
import com.daw.senecaexa22_alejandromendoza.DAO.ProfesorJpaController;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Utilidades {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("senecaP");
    private final AlumasigJpaController ctrAlumasig = new AlumasigJpaController(emf);
    private final AlumnoJpaController ctrAlumno = new AlumnoJpaController(emf);
    private final AsignaturaJpaController ctrAsignatura = new AsignaturaJpaController(emf);
    private final CursosJpaController ctrCursos = new CursosJpaController(emf);
    private final DetallenotaJpaController ctrDetallenota = new DetallenotaJpaController(emf);
    private final ProfesorJpaController ctrProfesor = new ProfesorJpaController(emf);

    public AlumasigJpaController getCtrAlumasig() {
        return ctrAlumasig;
    }

    public AlumnoJpaController getCtrAlumno() {
        return ctrAlumno;
    }

    public AsignaturaJpaController getCtrAsignatura() {
        return ctrAsignatura;
    }

    public CursosJpaController getCtrCursos() {
        return ctrCursos;
    }

    public DetallenotaJpaController getCtrDetallenota() {
        return ctrDetallenota;
    }

    public ProfesorJpaController getCtrProfesor() {
        return ctrProfesor;
    }
    
    
    
}
