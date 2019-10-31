/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpwm20.webapps2019.jsf;

import com.cpwm20.webapps2019.ejb.UserService;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

/**
 *
 * @author cpwm20 Registers an initial admin to the database.
 */
@Singleton
@Startup
public class StartupBean {

    @Inject
    private UserService usrSrv;

    /**
     * Calls the user service with details for admin1 and the name 'Root
     * Administrator' after the project has been constructed.
     */
    @PostConstruct
    public void init() {
        if (usrSrv.findUser("admin1", "admin1") == null) {
            usrSrv.registerSupervisor("admin1", "admin1", "Root", "Administrator", "admin1@sussex.ac.uk", "Informatics", "+447000000000", true);
        }
    }

}
