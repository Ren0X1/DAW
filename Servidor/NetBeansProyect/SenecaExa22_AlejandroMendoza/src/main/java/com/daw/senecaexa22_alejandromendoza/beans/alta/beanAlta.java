package com.daw.senecaexa22_alejandromendoza.beans.alta;

import com.daw.senecaexa22_alejandromendoza.DTO.Alumno;
import com.daw.senecaexa22_alejandromendoza.DTO.Cursos;
import com.daw.senecaexa22_alejandromendoza.POJOS.Nif;
import com.daw.senecaexa22_alejandromendoza.utilidades.Utilidades;
import java.util.ArrayList;
import java.util.List;
import javax.faces.model.SelectItem;

public class beanAlta {
    
    private final Utilidades utils_ = new Utilidades();
    private Nif DNI;
    private String nombreape;
    private String localidad;
    private String curso;
    private String imagen;
    private ArrayList listaCurso = null;
    
    public beanAlta() {
    }

    public Nif getDNI() {
        return DNI;
    }

    public void setDNI(Nif DNI) {
        this.DNI = DNI;
    }

    public String getNombreape() {
        return nombreape;
    }

    public void setNombreape(String nombreape) {
        this.nombreape = nombreape;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public ArrayList getListaCurso() {
        if (listaCurso == null) {
            listaCurso = new ArrayList();
            List<Cursos> v = utils_.getCtrCursos().findCursosEntities();
            for( Cursos o:v) {
                Cursos x = o;
                listaCurso.add(new SelectItem(x.getIdCurso(),x.getNomCursos()));
            }
        }
        return listaCurso;
    }

    public void setListaCurso(ArrayList listaCurso) {
        this.listaCurso = listaCurso;
    }
    
    public void guardarAlumno() {
        Alumno alu = new Alumno(
                utils_.getCtrAlumno().getAlumnoCount()+1,
                DNI.getNif_number()+DNI.getNif_letter(),
                nombreape,
                localidad,
                Integer.parseInt(curso),
                imagen
        );
        utils_.getCtrAlumno().create(alu);
    }
    
}
