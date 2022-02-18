package com.daw.saludojsf22;

import javax.faces.bean.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class saludo {

    private String usuario;
    public saludo() {
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
}
