package com.daw.mantenimientopersonasjsf.alta;

import com.daw.mantenimientopersonasjsf.DAO.PersonasJpaController;
import com.daw.mantenimientopersonasjsf.DTO.Personas;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class altaPersona {
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("mantenimientoPersonas");
    private final PersonasJpaController ctrPersonas = new PersonasJpaController(emf);
    private String nombre;
    private String apellidos;
    private String fnacimiento;
    private String observaciones;
    
    public altaPersona() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFnacimiento() {
        return fnacimiento;
    }

    public void setFnacimiento(String fnacimiento) {
        this.fnacimiento = fnacimiento;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    public void guardarPersona() {
        try {
            SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
            Date newDate = formatDate.parse(fnacimiento);
            Personas persona = new Personas(
                    ctrPersonas.getPersonasCount()+1,
                    nombre,
                    apellidos,
                    newDate,
                    observaciones
            );
            ctrPersonas.create(persona);
        } catch (ParseException ex) {
            Logger.getLogger(altaPersona.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
