package com.daw.floristeriajsf.beans.login;

import com.daw.floristeriajsf.DTO.Usuarios;
import com.daw.floristeriajsf.utils.Utilidades;

public class beanLogin {
    
    private final Utilidades utils_ = new Utilidades();
    private String dni;
    private String passwd;
    
    public beanLogin() {
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
    
    public String esUsuario() {
        String dni_    = this.dni;
        String passwd_ = this.passwd;
        if (dni_!=null && passwd_!=null) {
            Usuarios user_ = utils_.getCtrUsuarios().findUsuarios(dni_);
            if (user_!=null) {
                if (user_.getPassusuario().equals(passwd_)) {
                    return "true";
                } else {
                    return "false";
                }
            } else {
                return "false";
            }
        } else {
            return "false";
        }
    }
    
}
