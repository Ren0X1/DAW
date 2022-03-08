package com.daw.juegapalabrasjsf_alejandromendoza.beans.fin;

import com.daw.juegapalabrasjsf_alejandromendoza.DTO.Jugadores;
import com.daw.juegapalabrasjsf_alejandromendoza.utils.Utilidades;
import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpSession;

@ManagedBean(name="beanFinal")
@ViewScoped
public class beanFinal {

    @ManagedProperty("#{beanLogin.user}")
    private String nifUsu;
    private boolean estado;
    private int puntuacion;
    private final Utilidades utils_ = new Utilidades();
    
    public beanFinal() {
    }

    public String getNifUsu() {
        return nifUsu;
    }

    public void setNifUsu(String nifUsu) {
        this.nifUsu = nifUsu;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    public String nombreUsu() {
        Jugadores v = utils_.getCtrJugadores().findJugadores(nifUsu);
        puntuacion=v.getPuntosAcum();
        if(v.getNivelSuperado()==3) {
            estado=true;
        }
        return v.getNombre();
    }
    
    public void terminar() {
        logout("login.jsp");
    }
    
    public void terminar2() {
        redireccionar("alta.jsp");
    }
    
    public static void redireccionar (String url) {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ctx.redirect(url);
        } catch (IOException ex) {}
    }
    
    public static void logout(String surl) {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ((HttpSession) ctx.getSession(false)).invalidate();
            ctx.redirect(surl);
        } catch (IOException ex) {}
    }
}
