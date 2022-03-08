package com.daw.juegapalabrasjsf_alejandromendoza.beans.jugar;

import com.daw.juegapalabrasjsf_alejandromendoza.DTO.Jugadores;
import com.daw.juegapalabrasjsf_alejandromendoza.DTO.Spanish;
import com.daw.juegapalabrasjsf_alejandromendoza.POJOS.objectoAuxJuego;
import com.daw.juegapalabrasjsf_alejandromendoza.utils.Utilidades;
import java.io.IOException;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.component.html.HtmlSelectOneMenu;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

@ManagedBean(name="beanJugar")
@ViewScoped
public class beanJugar implements Serializable {
    private final Utilidades utils_ = new Utilidades();
    @ManagedProperty("#{beanLogin.user}")
    private String nifUsu;
    private HtmlDataTable tablaPrincipal;
    private boolean mostrarResul;
    private boolean guarda_aux1;
    private boolean guarda_aux2;
    private boolean mostrarResultados;
    private int puntuacion;
    private int superar;
    private String color;
    private ArrayList<objectoAuxJuego> listaFinal;
    
    public beanJugar() {
        tablaPrincipal = new HtmlDataTable();
        tablaPrincipal.setBgcolor("gray");
    }

    public String getNifUsu() {
        return nifUsu;
    }

    public HtmlDataTable getTablaPrincipal() {
        return tablaPrincipal;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public int getSuperar() {
        return superar;
    }

    public void setSuperar(int superar) {
        this.superar = superar;
    }

    public void setPuntuacion(int puntuacion) {
        this.puntuacion = puntuacion;
    }

    public boolean isMostrarResultados() {
        return mostrarResultados;
    }

    public void setMostrarResultados(boolean mostrarResultados) {
        this.mostrarResultados = mostrarResultados;
    }

    public boolean isGuarda_aux1() {
        return guarda_aux1;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setGuarda_aux1(boolean guarda_aux1) {
        this.guarda_aux1 = guarda_aux1;
    }

    public boolean isGuarda_aux2() {
        return guarda_aux2;
    }

    public void setGuarda_aux2(boolean guarda_aux2) {
        this.guarda_aux2 = guarda_aux2;
    }

    public ArrayList<objectoAuxJuego> getListaFinal() {
        return listaFinal;
    }

    public void setListaFinal(ArrayList<objectoAuxJuego> listaFinal) {
        this.listaFinal = listaFinal;
    }

    public void setTablaPrincipal(HtmlDataTable tablaPrincipal) {
        this.tablaPrincipal = tablaPrincipal;
    }
    
    public void setNifUsu(String nifUsu) {
        this.nifUsu = nifUsu;
    }
    
    public String nombreUsu() {
        Jugadores v = utils_.getCtrJugadores().findJugadores(nifUsu);
        if (v.getNivelSuperado()==3) {
            redireccionar("alta.jsp");
        }
        return v.getNombre();
    }
    
    public int superados() {
        Jugadores v = utils_.getCtrJugadores().findJugadores(nifUsu);
        return v.getNivelSuperado();
    }
    
    public String foto() {
        Jugadores v = utils_.getCtrJugadores().findJugadores(nifUsu);
        return "./img/"+v.getImgPerfil();
    }
    
    public ArrayList<objectoAuxJuego> listaPalabras() {
        List<Spanish> palabras = listaPalabrasNivel();
        ArrayList<objectoAuxJuego> array = new ArrayList<>();
        for (Spanish o:palabras) {
            objectoAuxJuego e = new objectoAuxJuego(o.getPalabra(),o.getCodPal());
            array.add(e);
        }
        return array;
    }

    public boolean isMostrarResul() {
        return mostrarResul;
    }

    public void setMostrarResul(boolean mostrarResul) {
        this.mostrarResul = mostrarResul;
    }
    
    private List<Spanish> listaPalabrasNivel() {
        return utils_.getCtrSpanish().findByNivel(superados()+1);
    }
    
    public void showResul() {
        this.mostrarResul = true;
    }
    
    public ArrayList<objectoAuxJuego> lista() {
        try {
            if (!this.mostrarResul) {
                listaFinal = listaPalabras();
            } else if (!guarda_aux1) {
                guarda_aux1 = true;
            } else if (!guarda_aux2) {
                guarda_aux2 = true;
            } else if (guarda_aux2) {
                int puntuacionAdd = 0;
                for (objectoAuxJuego o:listaFinal) {
                    if (o.isOk()) {
                        puntuacionAdd=puntuacionAdd+(superados()+1);
                    }
                }
                puntuacion=puntuacionAdd;
                superar = (superados()+1)*listaFinal.size()/2;
                mostrarResultados=true;
            }
            return listaFinal;
        } catch (Exception ex) {
            Logger.getLogger(beanJugar.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public String mensaje() {
        if (puntuacion>superar) {
            addPun();
            return "Has pasado el nivel felicidades";
        } else {
            return "Lo sentimos no has superado el nivel";
        }
    }

    public void addPun() {
        try {
            Jugadores player = utils_.getCtrJugadores().findJugadores(nifUsu);
            int puntofinal = player.getPuntosAcum()+puntuacion;
            player.setPuntosAcum(puntofinal);
            if (puntuacion>superar) {
                int proximo = player.getNivelSuperado()+1;
                player.setNivelSuperado(proximo);
            }
            utils_.getCtrJugadores().edit(player);
            if (player.getNivelSuperado()==3) {
                redireccionar("final.jsp");
            }
        } catch (Exception ex) {
            Logger.getLogger(beanJugar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cambioColor(ValueChangeEvent evento) {
        HtmlSelectOneMenu menu = (HtmlSelectOneMenu)evento.getComponent();
        String bg = (String)menu.getValue();
        tablaPrincipal.setBgcolor(bg);
    }
    
    public static void redireccionar (String url) {
        ExternalContext ctx = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ctx.redirect(url);
        } catch (IOException ex) {}
    }
    
    public void irfin() {
        redireccionar("final.jsp");
    }
    
    public void irfoto() {
        redireccionar("subirFoto.jsp");
    }
}
