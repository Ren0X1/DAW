package com.daw.mantenimientopersonasjsf.index;

import com.daw.mantenimientopersonasjsf.DAO.PersonasJpaController;
import com.daw.mantenimientopersonasjsf.DAO.exceptions.NonexistentEntityException;
import com.daw.mantenimientopersonasjsf.DTO.Personas;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.html.HtmlDataTable;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class consultasBean {
    
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("mantenimientoPersonas");
    private final PersonasJpaController ctrPersonas = new PersonasJpaController(emf);
    private HtmlDataTable tablaRespaldo;
    private int render;
    
    public consultasBean() {
    }

    public int getRender() {
        return render;
    }

    public void setRender(int render) {
        this.render = render;
    }

    public HtmlDataTable getTablaRespaldo() {
        return tablaRespaldo;
    }

    public void setTablaRespaldo(HtmlDataTable tablaRespaldo) {
        this.tablaRespaldo = tablaRespaldo;
    }
    
    public List<Personas> consultaListaPersonas() {
        render = 1;
        return ctrPersonas.findPersonasEntities();
    }
    
    public void eliminarPersona() {
        try {
            Personas persona = (Personas) tablaRespaldo.getRowData();
            ctrPersonas.destroy(persona.getId());
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(consultasBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void guardarPersona() {
        try {
            //tablaRespaldo.getValue();
            Personas persona = (Personas) tablaRespaldo.getRowData();
            persona.setEditable(0);
            ctrPersonas.edit(persona);
        } catch (Exception ex) {
            Logger.getLogger(consultasBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void modificarPersona() {
        Personas persona = (Personas) tablaRespaldo.getRowData();
        persona.setEditable(1);
    }
}
