package com.daw.senecaexa22_alejandromendoza.beans.login;

import com.daw.senecaexa22_alejandromendoza.DTO.Profesor;
import com.daw.senecaexa22_alejandromendoza.utilidades.Utilidades;
import java.util.List;

public class beanLogin {

    private final Utilidades utils_ = new Utilidades();
    private String usuario;
    private String passwd;
    private int cod_prof;
    
    public beanLogin() {
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public int getCod_prof() {
        return cod_prof;
    }

    public void setCod_prof(int cod_prof) {
        this.cod_prof = cod_prof;
    }
    
    
    
    public String esUsuario() {
        String usu_    = this.usuario;
        String passwd_ = this.passwd;
        if (usu_!=null && passwd_!=null) {
            List<Profesor> profes = utils_.getCtrProfesor().findProfesorEntities();
            for (Profesor o:profes) {
                Profesor x = o;
                if (x.getUsuProfesor().equals(usu_)) {
                    if(x.getPassProfesor().equals(passwd_)) {
                        this.cod_prof = x.getCodProfesor();
                        return "true";
                    } else {
                        return "false";
                    }
                }
            }
            return "false";
        } else {
            return "false";
        }
    }
}
