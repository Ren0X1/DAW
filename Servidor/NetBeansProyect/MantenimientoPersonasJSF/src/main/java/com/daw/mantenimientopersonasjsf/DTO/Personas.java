package com.daw.mantenimientopersonasjsf.DTO;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "personas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Personas.findAll", query = "SELECT p FROM Personas p"),
    @NamedQuery(name = "Personas.findById", query = "SELECT p FROM Personas p WHERE p.id = :id"),
    @NamedQuery(name = "Personas.findByNombre", query = "SELECT p FROM Personas p WHERE p.nombre = :nombre"),
    @NamedQuery(name = "Personas.findByApellidos", query = "SELECT p FROM Personas p WHERE p.apellidos = :apellidos"),
    @NamedQuery(name = "Personas.findByFnacimiento", query = "SELECT p FROM Personas p WHERE p.fnacimiento = :fnacimiento")})
public class Personas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "NOMBRE")
    private String nombre;
    @Size(max = 55)
    @Column(name = "APELLIDOS")
    private String apellidos;
    @Column(name = "FNACIMIENTO")
    @Temporal(TemporalType.DATE)
    private Date fnacimiento;
    @Lob
    @Size(max = 65535)
    @Column(name = "OBSERVACIONES")
    private String observaciones;
    
    private static int editable;

    public Personas() {
    }

    public Personas(Integer id) {
        this.id = id;
    }

    public Personas(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Personas(Integer id, String nombre, String apellidos, Date newDate, String observaciones) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fnacimiento = newDate;
        this.observaciones = observaciones;
    }

    public Integer getId() {
        return id;
    }
    
    public int getEditable() {
        return editable;
    }
    
    public void setEditable(int editable) {
        this.editable = editable;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getFnacimiento() {
        return fnacimiento;
    }

    public void setFnacimiento(Date fnacimiento) {
        this.fnacimiento = fnacimiento;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Personas)) {
            return false;
        }
        Personas other = (Personas) object;
        return !((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id)));
    }

    @Override
    public String toString() {
        return "com.daw.mantenimientopersonasjsf.DTO.Personas[ id=" + id + " ]";
    }
    
}
