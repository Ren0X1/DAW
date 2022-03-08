package com.daw.juegapalabrasjsf_alejandromendoza.converters;

import com.daw.juegapalabrasjsf_alejandromendoza.DTO.English;
import com.daw.juegapalabrasjsf_alejandromendoza.utils.Utilidades;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public class convertirING implements Converter{
    private final Utilidades utils_ = new Utilidades();
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        String[] partes = string.split(";");
        English x = new English(null,partes[0],partes[1]);
        utils_.getCtrEnglish().create(x);
        return x;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        English x = (English) o;
        return x.getPalabra();
    }
}
