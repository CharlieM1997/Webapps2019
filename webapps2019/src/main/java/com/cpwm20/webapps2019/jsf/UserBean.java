/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpwm20.webapps2019.jsf;

import com.cpwm20.webapps2019.ejb.TimestampService;
import com.cpwm20.webapps2019.ejb.UserService;
import com.cpwm20.webapps2019.entity.SystemUser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

/**
 *
 * 
 * Bean for dealing with SystemUsers. It is only used for the log, as specific 
 * beans for students and supervisors are used for the rest of the project.
 * 
 * @author cpwm20
 */
@Named(value = "userBean")
@SessionScoped
public class UserBean implements Serializable {

    @Inject
    private UserService usrSrv;

    @Inject
    private TimestampService timeSrv;

    SystemUser user;
    List<SystemUser> allUsers;
    File log;
    String userlog;

    /**
     * Empty Constructor.
     */
    public UserBean() {
    }

    /**
     * Gets list of all users.
     * @return
     */
    public List<SystemUser> getAllUsers() {
        allUsers = usrSrv.getAllUsers();
        return allUsers;
    }
    
    /**
     * Goes to user log page.
     * @return User log page.
     */
    public String toUserLog() {
        return "userlog";
    }

    /**
     * Gets a specific user's log by searching through the log for lines with
     * their University ID. Please note that if the log4j log file had to be
     * moved, the first line of this method will need to be changed to
     * reflect where the log file is now located.
     * @return A string of all of the lines in the log that reference the user.
     */
    public String logUser() {
        log = new File("D:\\log4j-application.log");
        userlog = "";
        try (FileInputStream fstream = new FileInputStream(log)) {
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
            String strLine;
            /* read log line by line */
            while ((strLine = br.readLine()) != null) {
                if (strLine.contains(user.getUniID())) {
                    userlog = userlog.concat(strLine + "\n");
                }
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
        return userlog;
    }

    /**
     *
     * @return
     */
    public UserService getUsrSrv() {
        return usrSrv;
    }

    /**
     *
     * @param usrSrv
     */
    public void setUsrSrv(UserService usrSrv) {
        this.usrSrv = usrSrv;
    }

    /**
     *
     * @return
     */
    public TimestampService getTimeSrv() {
        return timeSrv;
    }

    /**
     *
     * @param timeSrv
     */
    public void setTimeSrv(TimestampService timeSrv) {
        this.timeSrv = timeSrv;
    }

    /**
     *
     * @return
     */
    public SystemUser getUser() {
        return user;
    }

    /**
     *
     * @param user
     */
    public void setUser(SystemUser user) {
        this.user = user;
    }

}
