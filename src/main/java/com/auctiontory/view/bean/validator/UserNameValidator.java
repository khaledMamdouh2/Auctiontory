package com.auctiontory.view.bean.validator;

import com.auctiontory.controller.UserController;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;

@FacesValidator("userNameValidator")
public class UserNameValidator implements Validator {
    @Inject
    private UserController userController;

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
        String userName = (String) o;
        boolean userNameUsed = userController.isUser(userName);
        if (userNameUsed) {
            throw new ValidatorException(new FacesMessage("Username Already Exists!"));
        }
    }
}
