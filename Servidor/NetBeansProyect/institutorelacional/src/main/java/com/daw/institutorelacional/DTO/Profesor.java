package com.daw.institutorelacional.DTO;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "profesor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Profesor.findAll", query = "SELECT p FROM Profesor p"),
    @NamedQuery(name = "Profesor.findByDniprofesor", query = "SELECT p FROM Profesor p WHERE p.dniprofesor = :dniprofesor"),
    @NamedQuery(name = "Profesor.findByNombreprofesor", query = "SELECT p FROM Profesor p WHERE p.nombreprofesor = :nombreprofesor")})
public class Profesor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "dniprofesor")
    private String dniprofesor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "nombreprofesor")
    private String nombreprofesor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dniprofesor")
    private List<Asignatura> asignaturaList;

    public Profesor() {
    }

    public Profesor(String dniprofesor) {
        this.dniprofesor = dniprofesor;
    }

    public Profesor(String dniprofesor, String nombreprofesor) {
        this.dniprofesor = dniprofesor;
        this.nombreprofesor = nombreprofesor;
    }

    public String getDniprofesor() {
        return dniprofesor;
    }

    public void setDniprofesor(String dniprofesor) {
        this.dniprofesor = dniprofesor;
    }

    public String getNombreprofesor() {
        return nombreprofesor;
    }

    public void setNombreprofesor(String nombreprofesor) {
        this.nombreprofesor = nombreprofesor;
    }

    @XmlTransient
    public List<Asignatura> getAsignaturaList() {
        return asignaturaList;
    }

    public void setAsignaturaList(List<Asignatura> asignaturaList) {
        this.asignaturaList = asignaturaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dniprofesor != null ? dniprofesor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Profesor)) {
            return false;
        }
        Profesor other = (Profesor) object;
        if ((this.dniprofesor == null && other.dniprofesor != null) || (this.dniprofesor != null && !this.dniprofesor.equals(other.dniprofesor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.daw.institutorelacional.DTO.Profesor[ dniprofesor=" + dniprofesor + " ]";
    }

}
