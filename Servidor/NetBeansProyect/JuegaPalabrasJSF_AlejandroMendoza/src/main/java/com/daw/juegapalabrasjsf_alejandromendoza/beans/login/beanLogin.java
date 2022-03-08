package com.daw.juegapalabrasjsf_alejandromendoza.beans.login;

import com.daw.juegapalabrasjsf_alejandromendoza.DTO.Jugadores;
import com.daw.juegapalabrasjsf_alejandromendoza.utils.Utilidades;
import java.util.List;

public class beanLogin {

    private String user;
    private String pass;
    private String error;
    private final Utilidades utils_ = new Utilidades();
    
    public beanLogin() {
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    
    public String esUsuario() {
        String nif_    = this.user;
        String passwd_ = this.pass;
        error="";
        if (nif_ != null && passwd_ != null) {
            List<Jugadores> usuarios_ = utils_.getCtrJugadores().findJugadoresEntities();
            for(Jugadores o:usuarios_) {
                Jugadores v = o;
                if (v.getNif().equals(nif_)) {
                    if (v.getPass().equals(passwd_)) {
                        return "true";
                    } else {
                        return error = "Contraseña Errónea";
                    }
                }
            }
        } else {
            return error="NIF y Contraseña Incorrectos";
        }
        return error="NIF y Contraseña Incorrectos";
    }
}
