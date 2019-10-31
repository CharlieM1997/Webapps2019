/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpwm20.webapps2019.jsf;

import com.cpwm20.webapps2019.ejb.ProjectService;
import com.cpwm20.webapps2019.ejb.TimestampService;
import com.cpwm20.webapps2019.ejb.TopicService;
import com.cpwm20.webapps2019.ejb.UserService;
import com.cpwm20.webapps2019.entity.Project.Status;
import com.cpwm20.webapps2019.entity.ProjectTopic;
import com.cpwm20.webapps2019.entity.Student;
import com.cpwm20.webapps2019.entity.Supervisor;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 * Project Bean. Covers the registration of projects.
 * @author cpwm20
 */
@Named
@ViewScoped
public class ProjectBean implements Serializable {

    @Inject
    private ProjectService pjtSrv;
    
    @Inject
    private TopicService tpcSrv;
    
    @Inject
    private UserService usrSrv;
    
    @Inject
    private TimestampService timeSrv;

    String title;
    String description;
    String reqSkills;
    Set topicSet;
    Supervisor supervisor;
    Student student;
    
    List<ProjectTopic> allTopics;
    List<Supervisor> allSupervisors;
    
    private static final Logger logger = Logger.getLogger(ProjectBean.class);

    /**
     * Empty Constructor.
     */
    public ProjectBean() {
    }
    
    /**
     * Gets topics and supervisors for future use.
     */
    @PostConstruct
    public void init() {
        allTopics = tpcSrv.getAllTopics();
        timeSrv.timestamp();
        allSupervisors = usrSrv.getSupervisors();
        timeSrv.timestamp();
    }

    /**
     * Registers a project as a supervisor, with topics if selected.
     * @return
     */
    public String registerAsSupervisor() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        supervisor = (Supervisor) request.getSession().getAttribute("user");
        if (topicSet == null || topicSet.isEmpty()) {
            pjtSrv.registerProject(title, description, reqSkills, Status.AVAILABLE, supervisor);
        } else {
            pjtSrv.registerProjectWithSet(title, description, reqSkills, Status.AVAILABLE, topicSet, supervisor);
        }
        logger.warn("Project " + title + " registered by supervisor " + supervisor.getUniID() + " at " + timeSrv.inlineTimestamp());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Project successfully registered."));
        return "supervisor";
    }
    
    /**
     * Registers a project as a student, with topics if selected.
     * @return
     */
    public String registerAsStudent() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        student = (Student) request.getSession().getAttribute("user");
        if (topicSet == null || topicSet.isEmpty()) {
            pjtSrv.registerProjectWithStudent(title, description, reqSkills, Status.PROPOSED, student, supervisor);
        } else {
            pjtSrv.registerProjectWithStudentAndSet(title, description, reqSkills, Status.PROPOSED, topicSet, student, supervisor);
        }
        logger.warn("Project " + title + " registered by student " + student.getUniID() + " at " + timeSrv.inlineTimestamp());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Project successfully registered."));
        return "student";
    }

    /**
     *
     * @return
     */
    public ProjectService getPjtSrv() {
        return pjtSrv;
    }

    /**
     *
     * @param pjtSrv
     */
    public void setPjtSrv(ProjectService pjtSrv) {
        this.pjtSrv = pjtSrv;
    }

    /**
     *
     * @return
     */
    public TopicService getTpcSrv() {
        return tpcSrv;
    }

    /**
     *
     * @param tpcSrv
     */
    public void setTpcSrv(TopicService tpcSrv) {
        this.tpcSrv = tpcSrv;
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
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     */
    public String getReqSkills() {
        return reqSkills;
    }

    /**
     *
     * @param reqSkills
     */
    public void setReqSkills(String reqSkills) {
        this.reqSkills = reqSkills;
    }

    /**
     *
     * @return
     */
    public Set getTopicSet() {
        return topicSet;
    }

    /**
     *
     * @param topicSet
     */
    public void setTopicSet(Set topicSet) {
        this.topicSet = topicSet;
    }

    /**
     *
     * @return
     */
    public Supervisor getSupervisor() {
        return supervisor;
    }

    /**
     *
     * @param supervisor
     */
    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
    }

    /**
     *
     * @return
     */
    public Student getStudent() {
        return student;
    }

    /**
     *
     * @param student
     */
    public void setStudent(Student student) {
        this.student = student;
    }

    /**
     *
     * @return
     */
    public List<ProjectTopic> getAllTopics() {
        return allTopics;
    }

    /**
     *
     * @param allTopics
     */
    public void setAllTopics(List<ProjectTopic> allTopics) {
        this.allTopics = allTopics;
    }

    /**
     *
     * @return
     */
    public List<Supervisor> getAllSupervisors() {
        return allSupervisors;
    }

    /**
     *
     * @param allSupervisors
     */
    public void setAllSupervisors(List<Supervisor> allSupervisors) {
        this.allSupervisors = allSupervisors;
    }
    
    

}
