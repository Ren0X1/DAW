package com.daw.juegapalabrasjsf_alejandromendoza.beans.alta;

import com.daw.juegapalabrasjsf_alejandromendoza.DTO.English;
import com.daw.juegapalabrasjsf_alejandromendoza.DTO.Spanish;
import com.daw.juegapalabrasjsf_alejandromendoza.utils.Utilidades;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "beanAlta")
@SessionScoped
public class beanAlta implements Serializable {
    private final Utilidades utils_ = new Utilidades();
    private Spanish palabraESP;
    private English palabraING1;
    private English palabraING2;
    private English palabraING3;
    
    public beanAlta() {
    }

    public Spanish getPalabraESP() {
        return palabraESP;
    }

    public void setPalabraESP(Spanish palabraESP) {
        this.palabraESP = palabraESP;
    }

    public English getPalabraING1() {
        return palabraING1;
    }

    public void setPalabraING1(English palabraING1) {
        this.palabraING1 = palabraING1;
    }

    public English getPalabraING2() {
        return palabraING2;
    }

    public void setPalabraING2(English palabraING2) {
        this.palabraING2 = palabraING2;
    }

    public English getPalabraING3() {
        return palabraING3;
    }

    public void setPalabraING3(English palabraING3) {
        this.palabraING3 = palabraING3;
    }
    
    public void guardar() {
    }
}
