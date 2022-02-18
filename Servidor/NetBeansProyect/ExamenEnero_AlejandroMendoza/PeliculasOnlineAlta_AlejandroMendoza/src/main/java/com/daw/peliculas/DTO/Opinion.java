package com.daw.peliculas.DTO;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "opinion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Opinion.findAll", query = "SELECT o FROM Opinion o"),
    @NamedQuery(name = "Opinion.findByCodComen", query = "SELECT o FROM Opinion o WHERE o.codComen = :codComen"),
    @NamedQuery(name = "Opinion.findByComentario", query = "SELECT o FROM Opinion o WHERE o.comentario = :comentario"),
    @NamedQuery(name = "Opinion.findByPuntuacion", query = "SELECT o FROM Opinion o WHERE o.puntuacion = :puntuacion")})
public class Opinion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "codComen")
    private Integer codComen;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "comentario")
    private String comentario;
    @Basic(optional = false)
    @NotNull
    @Column(name = "puntuacion")
    private int puntuacion;
    @JoinColumn(name = "codPelicula", referencedColumnName = "codPelicula")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Pelicula codPelicula;

    public Opinion() {
    }

    public Opinion(Integer codComen) {
        this.codComen = codComen;
    }

    public Opinion(Integer codComen, String comentario, int puntuacion) {
        this.codComen = codComen;
        this.comentario = comentario;
        this.puntuacion = puntuacion;
    }

    public Integer getCodComen() {
        return codComen;
    }

    public void setCodComen(Integer codComen) {
        this.codComen = codComen;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public Pelicula getCodPelicula() {
        return codPelicula;
    }

    public void setCodPelicula(Pelicula codPelicula) {
        this.codPelicula = codPelicula;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codComen != null ? codComen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Opinion)) {
            return false;
        }
        Opinion other = (Opinion) object;
        if ((this.codComen == null && other.codComen != null) || (this.codComen != null && !this.codComen.equals(other.codComen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.daw.peliculas.DTO.Opinion[ codComen=" + codComen + " ]";
    }

}
