package com.daw.juegapalabrasjsf_alejandromendoza.POJOS;

import com.daw.juegapalabrasjsf_alejandromendoza.DTO.Vistawords;
import com.daw.juegapalabrasjsf_alejandromendoza.utils.Utilidades;
import java.util.List;

public class objectoAuxJuego {

    private int code_palabraESP;
    private String palabraESP;
    private String palabraING;
    private boolean ok = false;
    private final Utilidades utils_ = new Utilidades();

    public objectoAuxJuego() {
    }

    public objectoAuxJuego(String palabraESP, int code_palabraESP) {
        this.palabraESP = palabraESP;
        this.code_palabraESP = code_palabraESP;
    }

    
    public int getCode_palabraESP() {
        return code_palabraESP;
    }

    public void setCode_palabraESP(int code_palabraESP) {
        this.code_palabraESP = code_palabraESP;
    }

    public String getPalabraESP() {
        return palabraESP;
    }

    public void setPalabraESP(String palabraESP) {
        this.palabraESP = palabraESP;
    }

    public String getPalabraING() {
        return palabraING;
    }

    public void setPalabraING(String palabraING) {
        this.palabraING = palabraING;
    }

    public boolean isOk() {
        List<Vistawords> v = utils_.getCtrVista().findById_(code_palabraESP);
        for (Vistawords o:v) {
            Vistawords x = o;
            if (x.getPalabra().equals(palabraING)) {
                ok=true;
            }
        }
        return ok;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }
    
    
}
