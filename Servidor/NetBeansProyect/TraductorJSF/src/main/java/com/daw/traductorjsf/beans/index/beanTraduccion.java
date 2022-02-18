package com.daw.traductorjsf.beans.index;

import java.util.Locale;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

public class beanTraduccion {

    public beanTraduccion() {
    }
    
    public void cambioIdioma(ActionEvent evento) {
        FacesContext contexto = FacesContext.getCurrentInstance();
        HtmlCommandButton boton = (HtmlCommandButton)evento.getComponent();
        if (boton.getValue().equals("en")) {
            contexto.getViewRoot().setLocale(Locale.ENGLISH);
        } else if(boton.getValue().equals("en_GB")) {
            contexto.getViewRoot().setLocale(Locale.UK);
        } else if(boton.getValue().equals("en_US")) {
            contexto.getViewRoot().setLocale(Locale.US);
        } else {
            contexto.getViewRoot().setLocale(Locale.getDefault());
        }
    }
}
