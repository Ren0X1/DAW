package com.daw.senecaexa22_alejandromendoza.Validators;

import com.daw.senecaexa22_alejandromendoza.POJOS.Nif;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class validatorNif implements Validator{

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {
        Nif nif = (Nif)o;
        if(!validaDNI(nif)) {
            String exp = "NIF no valido";
            throw new ValidatorException(new FacesMessage(exp));
        }
    }
    
    private boolean validaDNI(Nif nif) {
        String dni_letters = "TRWAGMYFPDXBNJZSQVHLCKET";
        char letter = dni_letters.charAt(nif.getNif_number()%23);
        return letter==nif.getNif_letter().toCharArray()[0];
    }

}
