package com.daw.juegapalabrasjsf_alejandromendoza.DTO;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "spanish")
@NamedQueries({
    @NamedQuery(name = "Spanish.findAll", query = "SELECT s FROM Spanish s"),
    @NamedQuery(name = "Spanish.findByCodPal", query = "SELECT s FROM Spanish s WHERE s.codPal = :codPal"),
    @NamedQuery(name = "Spanish.findByPalabra", query = "SELECT s FROM Spanish s WHERE s.palabra = :palabra"),
    @NamedQuery(name = "Spanish.findByNivel", query = "SELECT s FROM Spanish s WHERE s.nivel = :nivel")})
public class Spanish implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cod_pal")
    private Integer codPal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "palabra")
    private String palabra;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "comentario")
    private String comentario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nivel")
    private int nivel;

    public Spanish() {
    }

    public Spanish(Integer codPal) {
        this.codPal = codPal;
    }

    public Spanish(Integer codPal, String palabra, String comentario, int nivel) {
        this.codPal = codPal;
        this.palabra = palabra;
        this.comentario = comentario;
        this.nivel = nivel;
    }

    public Integer getCodPal() {
        return codPal;
    }

    public void setCodPal(Integer codPal) {
        this.codPal = codPal;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codPal != null ? codPal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Spanish)) {
            return false;
        }
        Spanish other = (Spanish) object;
        return !((this.codPal == null && other.codPal != null) || (this.codPal != null && !this.codPal.equals(other.codPal)));
    }

    @Override
    public String toString() {
        return "com.daw.juegapalabrasjsf_alejandromendoza.DTO.Spanish[ codPal=" + codPal + " ]";
    }

}
