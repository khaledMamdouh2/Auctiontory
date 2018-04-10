package com.auctiontory.view.bean.validator;

import com.auctiontory.controller.UserController;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

@FacesValidator("emailValidator")
public class EmailValidator implements Validator {
    @Inject
    private UserController userController;

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        String email = (String) o;
        boolean valid = email.matches("^\\S+@\\S+$");
        boolean emailUsed = userController.emailUsed(email);
        if (emailUsed) {
            throw new ValidatorException(new FacesMessage("Email Already Exists!"));
        }
        if (!valid) {
            throw new ValidatorException(new FacesMessage("Please enter a valid Email!"));
        }
    }
}
