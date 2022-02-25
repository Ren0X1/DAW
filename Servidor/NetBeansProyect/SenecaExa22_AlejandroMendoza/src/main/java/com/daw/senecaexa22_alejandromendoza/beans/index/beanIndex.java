package com.daw.senecaexa22_alejandromendoza.beans.index;

import com.daw.senecaexa22_alejandromendoza.DTO.Alumasig;
import com.daw.senecaexa22_alejandromendoza.DTO.AlumasigPK;
import com.daw.senecaexa22_alejandromendoza.DTO.Alumno;
import com.daw.senecaexa22_alejandromendoza.DTO.Asignatura;
import com.daw.senecaexa22_alejandromendoza.DTO.Cursos;
import com.daw.senecaexa22_alejandromendoza.DTO.Detallenota;
import com.daw.senecaexa22_alejandromendoza.utilidades.Utilidades;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.model.SelectItem;

@ManagedBean(name="beanIndex")
@ViewScoped
public class beanIndex {
    private final Utilidades utils_ = new Utilidades();
    
    @ManagedProperty("#{beanLogin.usuario}")
    private String usuario;
    @ManagedProperty("#{beanLogin.cod_prof}")
    private int cod_prof;
    private final List<Cursos> listaC = utils_.getCtrCursos().findCursosEntities();
    private final List<Asignatura> listaA = utils_.getCtrAsignatura().findAsignaturaEntities();
    private String eva;
    private String curso;
    private int asignatura;
    private ArrayList listaCursos = null;
    private ArrayList listaAsig = null;
    private HtmlDataTable tablaNotas;
    private HtmlDataTable tablaNotas2;
    private boolean mostrar = false;
    private boolean mostrar3 = false;
    private boolean mostrar2 = false;
    private List listaAlum = null;
    
    
    public beanIndex() {
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEva() {
        return eva;
    }

    public boolean isMostrar2() {
        return mostrar2;
    }

    public void setMostrar2(boolean mostrar2) {
        this.mostrar2 = mostrar2;
    }

    public void setEva(String eva) {
        this.eva = eva;
    }

    public int getCod_prof() {
        return cod_prof;
    }

    public void setCod_prof(int cod_prof) {
        this.cod_prof = cod_prof;
    }
    
    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public HtmlDataTable getTablaNotas2() {
        return tablaNotas2;
    }

    public void setTablaNotas2(HtmlDataTable tablaNotas2) {
        this.tablaNotas2 = tablaNotas2;
    }

    public int getAsignatura() {
        return asignatura;
    }

    public boolean isMostrar() {
        return mostrar;
    }

    public void setMostrar(boolean mostrar) {
        this.mostrar = mostrar;
    }

    public HtmlDataTable getTablaNotas() {
        return tablaNotas;
    }

    public void setTablaNotas(HtmlDataTable tablaNotas) {
        this.tablaNotas = tablaNotas;
    }

    public void setAsignatura(int asignatura) {
        this.asignatura = asignatura;
    }

    public ArrayList getListaCursos() {
        listaCursos = new ArrayList();
        listaAsig = new ArrayList();
        for(Asignatura j:listaA) {
            Asignatura x1 = j;
            if(Integer.parseInt(x1.getCodProfesor())==cod_prof) {
                listaAsig.add(new SelectItem(x1.getIdAsig(),x1.getNomAsig()));
                Cursos ff = utils_.getCtrCursos().findCursos(x1.getIdCurso());
                SelectItem cursoAdd = new SelectItem(ff.getIdCurso(),ff.getNomCursos());
                if (!listaCursos.contains(cursoAdd)) {
                    listaCursos.add(cursoAdd);
                }
            }
        }
        return listaCursos;
    }

    public void setListaCursos(ArrayList listaCursos) {
        this.listaCursos = listaCursos;
    }

    public ArrayList getListaAsig() {
        return listaAsig;
    }

    public void setListaAsig(ArrayList listaAsig) {
        this.listaAsig = listaAsig;
    }
    
    public List<Detallenota> listaAlumnos() {
        return utils_.getCtrDetallenota().findDetallenotaEntities();
    }
    
    public void mostrar() {
        this.mostrar = true;
    }
    
    public void cambiarNota(Detallenota aa) {
        try {
            Detallenota k = (Detallenota)aa;
            int x = k.getIdAlum();
            int asig = k.getIdASig();
            int nota1 = k.getNota1();
            int nota2 = k.getNota2();
            int nota3 = k.getNota3();
            AlumasigPK gg = null;
            gg.setIdAlumno(x);
            gg.setIdAsig(asig);
            Alumasig alu = utils_.getCtrAlumasig().findAlumasig(gg);
            alu.setNota1(nota1);
            alu.setNota2(nota2);
            alu.setNota3(nota3);
            utils_.getCtrAlumasig().edit(alu);
        } catch (Exception ex) {
            Logger.getLogger(beanIndex.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isMostrar3() {
        return mostrar3;
    }

    public void setMostrar3(boolean mostrar3) {
        this.mostrar3 = mostrar3;
    }

    public List getListaAlum() {
        return listaAlum;
    }

    public void setListaAlum(List listaAlum) {
        this.listaAlum = listaAlum;
    }
    
    
    public void datosAlum() {
        this.mostrar3 = true;
    }
    
    public void tablarDos() {
        this.mostrar2 = true;
        cambiarNota((Detallenota)tablaNotas.getRowData());
        tablaNotas2 = tablaNotas;
    }
}
