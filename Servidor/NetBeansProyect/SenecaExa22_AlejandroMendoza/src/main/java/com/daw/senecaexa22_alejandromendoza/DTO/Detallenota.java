package com.daw.senecaexa22_alejandromendoza.DTO;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "detallenota")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Detallenota.findAll", query = "SELECT d FROM Detallenota d"),
    @NamedQuery(name = "Detallenota.findById", query = "SELECT d FROM Detallenota d WHERE d.id = :id"),
    @NamedQuery(name = "Detallenota.findByIdAlum", query = "SELECT d FROM Detallenota d WHERE d.idAlum = :idAlum"),
    @NamedQuery(name = "Detallenota.findByIdASig", query = "SELECT d FROM Detallenota d WHERE d.idASig = :idASig"),
    @NamedQuery(name = "Detallenota.findByNomAlumno", query = "SELECT d FROM Detallenota d WHERE d.nomAlumno = :nomAlumno"),
    @NamedQuery(name = "Detallenota.findByNomAsig", query = "SELECT d FROM Detallenota d WHERE d.nomAsig = :nomAsig"),
    @NamedQuery(name = "Detallenota.findByImagen", query = "SELECT d FROM Detallenota d WHERE d.imagen = :imagen"),
    @NamedQuery(name = "Detallenota.findByNota1", query = "SELECT d FROM Detallenota d WHERE d.nota1 = :nota1"),
    @NamedQuery(name = "Detallenota.findByNota2", query = "SELECT d FROM Detallenota d WHERE d.nota2 = :nota2"),
    @NamedQuery(name = "Detallenota.findByNota3", query = "SELECT d FROM Detallenota d WHERE d.nota3 = :nota3")})
public class Detallenota implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 36)
    @Column(name = "id")
    @Id
    private String id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idAlum")
    private int idAlum;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idASig")
    private int idASig;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "nomAlumno")
    private String nomAlumno;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "nomAsig")
    private String nomAsig;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "imagen")
    private String imagen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nota1")
    private int nota1;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nota2")
    private int nota2;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nota3")
    private int nota3;

    public Detallenota() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getIdAlum() {
        return idAlum;
    }

    public void setIdAlum(int idAlum) {
        this.idAlum = idAlum;
    }

    public int getIdASig() {
        return idASig;
    }

    public void setIdASig(int idASig) {
        this.idASig = idASig;
    }

    public String getNomAlumno() {
        return nomAlumno;
    }

    public void setNomAlumno(String nomAlumno) {
        this.nomAlumno = nomAlumno;
    }

    public String getNomAsig() {
        return nomAsig;
    }

    public void setNomAsig(String nomAsig) {
        this.nomAsig = nomAsig;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getNota1() {
        return nota1;
    }

    public void setNota1(int nota1) {
        this.nota1 = nota1;
    }

    public int getNota2() {
        return nota2;
    }

    public void setNota2(int nota2) {
        this.nota2 = nota2;
    }

    public int getNota3() {
        return nota3;
    }

    public void setNota3(int nota3) {
        this.nota3 = nota3;
    }

}
