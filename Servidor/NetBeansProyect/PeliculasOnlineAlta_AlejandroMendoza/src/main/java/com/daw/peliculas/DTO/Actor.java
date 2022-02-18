package com.daw.peliculas.DTO;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "actor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Actor.findAll", query = "SELECT a FROM Actor a"),
    @NamedQuery(name = "Actor.findByCodActor", query = "SELECT a FROM Actor a WHERE a.codActor = :codActor"),
    @NamedQuery(name = "Actor.findByNombreActor", query = "SELECT a FROM Actor a WHERE a.nombreActor = :nombreActor"),
    @NamedQuery(name = "Actor.findByNacionalidad", query = "SELECT a FROM Actor a WHERE a.nacionalidad = :nacionalidad"),
    @NamedQuery(name = "Actor.findBySexo", query = "SELECT a FROM Actor a WHERE a.sexo = :sexo")})
public class Actor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codActor")
    private Integer codActor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "nombreActor")
    private String nombreActor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "nacionalidad")
    private String nacionalidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "sexo")
    private String sexo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "actor", fetch = FetchType.LAZY)
    private List<Reparto> repartoList;

    public Actor() {
    }

    public Actor(Integer codActor) {
        this.codActor = codActor;
    }

    public Actor(Integer codActor, String nombreActor, String nacionalidad, String sexo) {
        this.codActor = codActor;
        this.nombreActor = nombreActor;
        this.nacionalidad = nacionalidad;
        this.sexo = sexo;
    }

    public Integer getCodActor() {
        return codActor;
    }

    public void setCodActor(Integer codActor) {
        this.codActor = codActor;
    }

    public String getNombreActor() {
        return nombreActor;
    }

    public void setNombreActor(String nombreActor) {
        this.nombreActor = nombreActor;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @XmlTransient
    public List<Reparto> getRepartoList() {
        return repartoList;
    }

    public void setRepartoList(List<Reparto> repartoList) {
        this.repartoList = repartoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codActor != null ? codActor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Actor)) {
            return false;
        }
        Actor other = (Actor) object;
        if ((this.codActor == null && other.codActor != null) || (this.codActor != null && !this.codActor.equals(other.codActor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.daw.peliculas.DTO.Actor[ codActor=" + codActor + " ]";
    }

}
