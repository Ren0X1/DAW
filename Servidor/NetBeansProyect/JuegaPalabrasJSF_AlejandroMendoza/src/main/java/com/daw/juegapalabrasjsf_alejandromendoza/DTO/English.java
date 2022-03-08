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
@Table(name = "english")
@NamedQueries({
    @NamedQuery(name = "English.findAll", query = "SELECT e FROM English e"),
    @NamedQuery(name = "English.findByCodPal", query = "SELECT e FROM English e WHERE e.codPal = :codPal"),
    @NamedQuery(name = "English.findByPalabra", query = "SELECT e FROM English e WHERE e.palabra = :palabra")})
public class English implements Serializable {

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

    public English() {
    }

    public English(Integer codPal) {
        this.codPal = codPal;
    }

    public English(Integer codPal, String palabra, String comentario) {
        this.codPal = codPal;
        this.palabra = palabra;
        this.comentario = comentario;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codPal != null ? codPal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof English)) {
            return false;
        }
        English other = (English) object;
        return !((this.codPal == null && other.codPal != null) || (this.codPal != null && !this.codPal.equals(other.codPal)));
    }

    @Override
    public String toString() {
        return "com.daw.juegapalabrasjsf_alejandromendoza.DTO.English[ codPal=" + codPal + " ]";
    }

}
