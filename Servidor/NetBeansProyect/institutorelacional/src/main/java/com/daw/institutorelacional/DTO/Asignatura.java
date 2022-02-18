package com.daw.institutorelacional.DTO;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "asignatura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Asignatura.findAll", query = "SELECT a FROM Asignatura a"),
    @NamedQuery(name = "Asignatura.findByCodasignatura", query = "SELECT a FROM Asignatura a WHERE a.codasignatura = :codasignatura"),
    @NamedQuery(name = "Asignatura.findByNombreasig", query = "SELECT a FROM Asignatura a WHERE a.nombreasig = :nombreasig"),
    @NamedQuery(name = "Asignatura.findByCurso", query = "SELECT a FROM Asignatura a WHERE a.curso = :curso")})
public class Asignatura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codasignatura")
    private Integer codasignatura;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "nombreasig")
    private String nombreasig;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "curso")
    private String curso;
    @JoinColumn(name = "dniprofesor", referencedColumnName = "dniprofesor")
    @ManyToOne(optional = false)
    private Profesor dniprofesor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "asignatura")
    private List<Matricula> matriculaList;

    public Asignatura() {
    }

    public Asignatura(Integer codasignatura) {
        this.codasignatura = codasignatura;
    }

    public Asignatura(Integer codasignatura, String nombreasig, String curso) {
        this.codasignatura = codasignatura;
        this.nombreasig = nombreasig;
        this.curso = curso;
    }

    public Integer getCodasignatura() {
        return codasignatura;
    }

    public void setCodasignatura(Integer codasignatura) {
        this.codasignatura = codasignatura;
    }

    public String getNombreasig() {
        return nombreasig;
    }

    public void setNombreasig(String nombreasig) {
        this.nombreasig = nombreasig;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public Profesor getDniprofesor() {
        return dniprofesor;
    }

    public void setDniprofesor(Profesor dniprofesor) {
        this.dniprofesor = dniprofesor;
    }

    @XmlTransient
    public List<Matricula> getMatriculaList() {
        return matriculaList;
    }

    public void setMatriculaList(List<Matricula> matriculaList) {
        this.matriculaList = matriculaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codasignatura != null ? codasignatura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asignatura)) {
            return false;
        }
        Asignatura other = (Asignatura) object;
        if ((this.codasignatura == null && other.codasignatura != null) || (this.codasignatura != null && !this.codasignatura.equals(other.codasignatura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.daw.institutorelacional.DTO.Asignatura[ codasignatura=" + codasignatura + " ]";
    }

}
