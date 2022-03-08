package com.daw.juegapalabrasjsf_alejandromendoza.converters;

import com.daw.juegapalabrasjsf_alejandromendoza.DTO.Spanish;
import com.daw.juegapalabrasjsf_alejandromendoza.utils.Utilidades;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public class convertirESP implements Converter{
    private final Utilidades utils_ = new Utilidades();
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        String[] partes = string.split(";");
        Spanish x = new Spanish(null,partes[0],partes[1],4);
        utils_.getCtrSpanish().create(x);
        return x;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        Spanish x = (Spanish) o;
        return x.getPalabra();
    }
}
