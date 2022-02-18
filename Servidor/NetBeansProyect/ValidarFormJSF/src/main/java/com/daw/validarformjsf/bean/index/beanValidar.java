package com.daw.validarformjsf.bean.index;

import com.daw.validarformjsf.POJOS.Nif;

public class beanValidar {
    
    private String nombre;
    private String apellidos;
    private int edad;
    private Nif nif;
    private String email;
    
    public beanValidar() {
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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public Nif getNif() {
        return nif;
    }

    public void setNif(Nif nif) {
        this.nif = nif;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
}
