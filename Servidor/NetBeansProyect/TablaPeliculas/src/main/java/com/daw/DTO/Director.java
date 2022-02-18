package com.daw.DTO;

public class Director {
    private String nombre;
    private String dni;
    private String nacionalidad;

    public Director() {
    }

    public Director(String dni) {
        this.dni = dni;
    }

    public Director(String dni, String nombre, String nacionalidad) {
        this.dni = dni;
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dni != null ? dni.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Director)) {
            return false;
        }
        Director other = (Director) object;
        return !((this.dni == null && other.dni != null) || (this.dni != null && !this.dni.equals(other.dni)));
    }

    @Override
    public String toString() {
        return "com.daw.DTO.Director[ dni=" + dni + " ]";
    }
    
}
