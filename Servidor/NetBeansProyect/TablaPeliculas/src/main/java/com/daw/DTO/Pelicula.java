package com.daw.DTO;

import java.util.Objects;

public class Pelicula {
    private String codigo_pelicula;
    private String director;
    private String estreno;
    private String publico;
    private String fecha;
    private String genero;
    private String imagen;
    private String nombre;

    public Pelicula() {
    }

    public Pelicula(String codigo_pelicula, String director, String estreno, String publico, String fecha, String genero, String imagen, String nombre) {
        this.codigo_pelicula = codigo_pelicula;
        this.director = director;
        this.estreno = estreno;
        this.publico = publico;
        this.fecha = fecha;
        this.genero = genero;
        this.imagen = imagen;
        this.nombre = nombre;
    }
    
    public String getCodigo_pelicula() {
        return codigo_pelicula;
    }

    public void setCodigo_pelicula(String codigo_pelicula) {
        this.codigo_pelicula = codigo_pelicula;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getEstreno() {
        return estreno;
    }

    public void setEstreno(String estreno) {
        this.estreno = estreno;
    }

    public String getPublico() {
        return publico;
    }

    public void setPublico(String publico) {
        this.publico = publico;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + Objects.hashCode(this.codigo_pelicula);
        hash = 11 * hash + Objects.hashCode(this.director);
        hash = 11 * hash + Objects.hashCode(this.estreno);
        hash = 11 * hash + Objects.hashCode(this.publico);
        hash = 11 * hash + Objects.hashCode(this.fecha);
        hash = 11 * hash + Objects.hashCode(this.genero);
        hash = 11 * hash + Objects.hashCode(this.imagen);
        hash = 11 * hash + Objects.hashCode(this.nombre);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pelicula other = (Pelicula) obj;
        if (this.estreno == null ? other.estreno != null : !this.estreno.equals(other.estreno)) {
            return false;
        }
        if (this.publico == null ? other.publico != null : !this.publico.equals(other.publico)) {
            return false;
        }
        if (!Objects.equals(this.codigo_pelicula, other.codigo_pelicula)) {
            return false;
        }
        if (!Objects.equals(this.director, other.director)) {
            return false;
        }
        if (!Objects.equals(this.genero, other.genero)) {
            return false;
        }
        if (!Objects.equals(this.imagen, other.imagen)) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return Objects.equals(this.fecha, other.fecha);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pelicula{codigo_pelicula=").append(codigo_pelicula);
        sb.append(", director=").append(director);
        sb.append(", estreno=").append(estreno);
        sb.append(", publico=").append(publico);
        sb.append(", fecha=").append(fecha);
        sb.append(", genero=").append(genero);
        sb.append(", imagen=").append(imagen);
        sb.append(", nombre=").append(nombre);
        sb.append('}');
        return sb.toString();
    }
}
