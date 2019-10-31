package com.cpwm20.webapps2019.jsf;

import com.cpwm20.webapps2019.ejb.TimestampService;
import com.cpwm20.webapps2019.ejb.UserService;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Registration Bean. Covers the registration of users.
 * @author cpwm20
 */
@Named
@ViewScoped
public class RegistrationBean implements Serializable {
    
    @Inject 
    private UserService usrSrv;
    
    @Inject
    private TimestampService timeSrv;
    
    //If true, then user is a student. If false, then user must be a supervisor.
    boolean student;
    
    //SystemUser variables
    String uniID;
    String userpassword;
    String name;
    String surname;
    String email;
    
    //Student variable
    String course;
    
    //Supervisor variables
    String department;
    String phoneNum;
    boolean admin;

    /**
     * Empty Constructor.
     */
    public RegistrationBean() {
    }

    /**
     * Registers a user with their specific values as a student or supervisor.
     * @return
     */
    public String register() {
        if (student == true) {
            System.out.println(course);
            usrSrv.registerStudent(uniID, userpassword, name, surname, email, course);
        }
        else {
            usrSrv.registerSupervisor(uniID, userpassword, name, surname, email, department, phoneNum, admin);
        }
        timeSrv.timestamp();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("User successfully registered."));
        return "admin";
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
    public boolean isStudent() {
        return student;
    }

    /**
     *
     * @param student
     */
    public void setStudent(boolean student) {
        this.student = student;
    }
    
    /**
     *
     * @return
     */
    public String getUniID() {
        return uniID;
    }

    /**
     *
     * @param uniID
     */
    public void setUniID(String uniID) {
        this.uniID = uniID;
    }

    /**
     *
     * @return
     */
    public String getUserpassword() {
        return userpassword;
    }

    /**
     *
     * @param userpassword
     */
    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     */
    public String getSurname() {
        return surname;
    }

    /**
     *
     * @param surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     */
    public String getCourse() {
        return course;
    }

    /**
     *
     * @param course
     */
    public void setCourse(String course) {
        this.course = course;
    }

    /**
     *
     * @return
     */
    public String getDepartment() {
        return department;
    }

    /**
     *
     * @param department
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     *
     * @return
     */
    public String getPhoneNum() {
        return phoneNum;
    }

    /**
     *
     * @param phoneNum
     */
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    /**
     *
     * @return
     */
    public boolean isAdmin() {
        return admin;
    }

    /**
     *
     * @param admin
     */
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    
}
