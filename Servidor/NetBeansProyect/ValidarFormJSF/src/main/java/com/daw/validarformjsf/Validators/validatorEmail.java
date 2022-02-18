package com.daw.validarformjsf.Validators;

import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class validatorEmail implements Validator{

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {
        String email = (String)o;
        String emailRegex = "^(.+)@(.+)$";
        boolean v = Pattern.matches(emailRegex, email);
        if (!v) {
            String exp = "Email no valido";
            throw new ValidatorException(new FacesMessage(exp));
        }
    }

}
