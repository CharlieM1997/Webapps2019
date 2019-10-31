/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpwm20.webapps2019.jsf;

import com.cpwm20.webapps2019.ejb.ProjectService;
import com.cpwm20.webapps2019.ejb.TimestampService;
import com.cpwm20.webapps2019.entity.Project;
import com.cpwm20.webapps2019.entity.Project.Status;
import com.cpwm20.webapps2019.entity.Student;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

/**
 * Student Bean. Checks the student's project status.
 * @author cpwm20
 */
@Named(value = "studentBean")
@SessionScoped
public class StudentBean implements Serializable {
    
    @Inject
    private ProjectService pjtSrv;
    
    @Inject
    private TimestampService timeSrv;
    
    Student student;
    Project project;

    /**
     * Empty Constructor.
     */
    public StudentBean() {
    }
    
    /**
     * Checks and relays information to the student regarding their project status.
     * @return New project page if the user can select a new project, student if
     * they cannot.
     */
    public String checkProject() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        student = (Student) request.getSession().getAttribute("user");
        project = pjtSrv.findProjectByStudent(student);
        timeSrv.timestamp();
        if (project == null) {
            return "newproject";
        }
        if (project.getStatus() == Status.PROPOSED) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Your project has been submitted."));
        } else if (project.getStatus() == Status.ACCEPTED) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("You have a project."));
        }
        return "student";
    }
    
}
