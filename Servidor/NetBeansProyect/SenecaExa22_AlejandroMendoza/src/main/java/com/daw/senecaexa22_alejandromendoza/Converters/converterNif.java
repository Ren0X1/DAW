package com.daw.senecaexa22_alejandromendoza.Converters;

import com.daw.senecaexa22_alejandromendoza.POJOS.Nif;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

public class converterNif implements Converter{
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string.length()==9) {
            int numero = 0;
            String letra = string.substring(8, 9);
            
            try {
                numero = Integer.parseInt(string.substring(0, 8));
            } catch(NumberFormatException e){
                throw new ConverterException(new FacesMessage("Los 8 primeros caracteres del NIF no pueden contener ninguna letra"));
            }
            
            if (Character.isLetter(letra.charAt(0))) {
                return new Nif(numero, letra);
            } else {
                throw new ConverterException(new FacesMessage("El ultimo digito de un NIF debe ser una letra"));
            }
        } else {
            throw new ConverterException(new FacesMessage("El NIF tiene que tener 8 caracteres y 1 letra."));
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        Nif x = (Nif)o;
        return x.toString();
    }
}
