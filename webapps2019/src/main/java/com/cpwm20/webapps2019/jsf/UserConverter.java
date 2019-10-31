/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpwm20.webapps2019.jsf;

import com.cpwm20.webapps2019.ejb.UserService;
import com.cpwm20.webapps2019.entity.SystemUser;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * User Converter. Overrides basic converter methods for SystemUser entity.
 * @author cpwm20
 */
@Named
@ApplicationScoped
public class UserConverter implements Converter {

    @Inject
    private UserService usrSrv;

    /**
     *
     * @param context
     * @param component
     * @param modelValue
     * @return
     */
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object modelValue) {
        if (modelValue == null) {
            return "";
        }

        if (modelValue instanceof SystemUser) {
            return String.valueOf(((SystemUser) modelValue).getId());
        } else {
            throw new ConverterException(new FacesMessage(modelValue + " is not a valid User"));
        }
    }

    /**
     *
     * @param context
     * @param component
     * @param submittedValue
     * @return
     */
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
        if (submittedValue == null || submittedValue.isEmpty()) {
            return null;
        }

        try {
            return usrSrv.findUser(Long.valueOf(submittedValue));
        } catch (NumberFormatException e) {
            throw new ConverterException(new FacesMessage(submittedValue + " is not a valid ID"), e);
        }
    }
}
