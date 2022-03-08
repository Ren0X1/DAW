package com.daw.juegapalabrasjsf_alejandromendoza.DTO;

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

@Entity
@Table(name = "vistawords")
@NamedQueries({
    @NamedQuery(name = "Vistawords.findAll", query = "SELECT v FROM Vistawords v"),
    @NamedQuery(name = "Vistawords.findByUuid", query = "SELECT v FROM Vistawords v WHERE v.uuid = :uuid"),
    @NamedQuery(name = "Vistawords.findByCodpalSp", query = "SELECT v FROM Vistawords v WHERE v.codpalSp = :codpalSp"),
    @NamedQuery(name = "Vistawords.findByPalabra", query = "SELECT v FROM Vistawords v WHERE v.palabra = :palabra")})
public class Vistawords implements Serializable {

    private static final long serialVersionUID = 1L;
    @Size(max = 36)
    @Column(name = "uuid()")
    @Id
    private String uuid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cod_palSp")
    private int codpalSp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "palabra")
    private String palabra;

    public Vistawords() {
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public int getCodpalSp() {
        return codpalSp;
    }

    public void setCodpalSp(int codpalSp) {
        this.codpalSp = codpalSp;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

}
