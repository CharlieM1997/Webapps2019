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
import com.cpwm20.webapps2019.entity.Project;
import com.cpwm20.webapps2019.entity.Project.Status;
import com.cpwm20.webapps2019.entity.ProjectTopic;
import com.cpwm20.webapps2019.entity.Student;
import com.cpwm20.webapps2019.entity.Supervisor;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 * Proposals Bean. Covers the majority of interactions regarding interacting with
 * projects and proposals.
 * @author cpwm20
 */
@Named
@SessionScoped
public class ProposalsBean implements Serializable {

    @Inject
    private ProjectService pjtSrv;

    @Inject
    private TopicService tpcSrv;

    @Inject
    private UserService usrSrv;

    @Inject
    private TimestampService timeSrv;

    private static final Logger logger = Logger.getLogger(ProposalsBean.class);

    String title;
    String description;
    String rptDetails;
    String reqSkills;
    Set topicSet;
    Supervisor supervisor;
    Student student;
    Project project;

    List<Project> allProjects;
    List<Project> openProjects;
    List<Project> ppdProjects;
    List<Project> acceptedProjects;

    private String result;

    /**
     * Empty Constructor.
     */
    public ProposalsBean() {
    }

    /**
     * Gets the user type and initialises values that may be used.
     */
    @PostConstruct
    public void init() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        if (request.getSession().getAttribute("user").getClass().equals(Student.class)) {
            student = (Student) request.getSession().getAttribute("user");
            openProjects = pjtSrv.findProjectsByStatus(Status.AVAILABLE);
            timeSrv.timestamp();
        } else {
            supervisor = (Supervisor) request.getSession().getAttribute("user");
            if (supervisor.isadmin()) {
                allProjects = pjtSrv.findAllProjects();
                timeSrv.timestamp();
            }
            ppdProjects = pjtSrv.findProjectsByStatusAndSupervisor(Status.PROPOSED, supervisor);
            timeSrv.timestamp();
            acceptedProjects = pjtSrv.findProjectsByStatusAndSupervisor(Status.ACCEPTED, supervisor);
            timeSrv.timestamp();
        }
    }

    /**
     * Goes to proposal page.
     * @return
     */
    public String toProposal() {
        return "proposal";
    }

    /**
     * Goes to access page if a supervisor has a project to access.
     * @return
     */
    public String toAccess() {
        if (acceptedProjects == null || acceptedProjects.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("You do not have any accepted projects to view."));
            return "supervisor";
        } else {
            return "access";
        }
    }

    /**
     * Goes to view page.
     * @return
     */
    public String toView() {
        return "view";
    }

    /**
     * Goes to report page.
     * @return
     */
    public String toReport() {
        return "report";
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
    public String getRptDetails() {
        return rptDetails;
    }

    /**
     *
     * @param rptDetails
     */
    public void setRptDetails(String rptDetails) {
        this.rptDetails = rptDetails;
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
     * Returns the topic set of a project as a string so it can be displayed on
     * the web page. Topics are comma separated.
     * @return All topics of a project as a string.
     */
    public String topicsAsString() {
        try {
            topicSet = project.getTopicSet();
        } catch (NullPointerException e) {
            //do nothing
        }

        if (topicSet == null || topicSet.isEmpty()) {
            return null;
        }
        String topics = "";
        Iterator<ProjectTopic> it = topicSet.iterator();

        while (it.hasNext()) {
            topics = topics.concat(it.next().getTitle() + ", ");
        }
        topics = topics.substring(0, topics.length() - 2);
        return topics;
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
    public Project getProject() {
        return project;
    }

    /**
     *
     * @param project
     */
    public void setProject(Project project) {
        this.project = project;
    }

    /**
     *
     * @return
     */
    public List<Project> getOpenProjects() {
        return openProjects;
    }

    /**
     *
     * @param openProjects
     */
    public void setOpenProjects(List<Project> openProjects) {
        this.openProjects = openProjects;
    }

    /**
     *
     * @return
     */
    public List<Project> getPpdProjects() {
        return ppdProjects;
    }

    /**
     *
     * @param ppdProjects
     */
    public void setPpdProjects(List<Project> ppdProjects) {
        this.ppdProjects = ppdProjects;
    }

    /**
     *
     * @return
     */
    public List<Project> getAcceptedProjects() {
        return acceptedProjects;
    }

    /**
     *
     * @param acceptedProjects
     */
    public void setAcceptedProjects(List<Project> acceptedProjects) {
        this.acceptedProjects = acceptedProjects;
    }

    /**
     *
     * @return
     */
    public List<Project> getAllProjects() {
        return allProjects;
    }

    /**
     *
     * @param allProjects
     */
    public void setAllProjects(List<Project> allProjects) {
        this.allProjects = allProjects;
    }

    /**
     *
     * @return
     */
    public String getResult() {
        return result;
    }

    /**
     *
     * @param result
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * Checks if a supervisor has any proposed projects to review and throws a
     * respective message.
     * @return Project selection page if there are any projects to review,
     * supervisor page if there aren't.
     */
    public String checkProject() {
        init();
        if (ppdProjects == null || ppdProjects.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("No new proposed projects."));
            return "supervisor";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("You have new proposed project(s) to view."));
        }
        return "selectproject";
    }

    /**
     * Requests an open project as a student.
     * @return Student page.
     */
    public String requestProject() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        student = (Student) request.getSession().getAttribute("user");
        pjtSrv.requestProject(project, student);
        logger.warn("Project " + project.getTitle() + " requested by student " + student.getUniID() + " at " + timeSrv.inlineTimestamp());
        return "student";
    }

    /**
     * Accepts a student's request.
     * @return Supervisor page.
     */
    public String acceptProject() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        supervisor = (Supervisor) request.getSession().getAttribute("user");
        pjtSrv.acceptProject(project);
        logger.warn("Project " + project.getTitle() + " accepted by supervisor " + supervisor.getUniID() + " at " + timeSrv.inlineTimestamp());
        return "supervisor";
    }

    /**
     * Rejects a student's request.
     * @return Supervisor page.
     */
    public String rejectProject() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        supervisor = (Supervisor) request.getSession().getAttribute("user");
        pjtSrv.removeProject(project);
        logger.warn("Project " + project.getTitle() + " rejected by supervisor " + supervisor.getUniID() + " at " + timeSrv.inlineTimestamp());
        setProject(null);
        return "supervisor";
    }

    /**
     * Deselects an active project. Refreshes project lists after.
     * @return Admin page if the deselection is possible, view if not.
     */
    public String deselect() {
        if (project.getStudent() == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cannot deselect a project that does not have a student."));
            return "view";
        } else {
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            supervisor = (Supervisor) request.getSession().getAttribute("user");
            pjtSrv.deselectProject(project);
            logger.warn("Project " + project.getTitle() + " deselected by supervisor " + supervisor.getUniID() + " at " + timeSrv.inlineTimestamp());
            setProject(null);
            openProjects = pjtSrv.findProjectsByStatus(Status.AVAILABLE);
            allProjects = pjtSrv.findAllProjects();
            ppdProjects = pjtSrv.findProjectsByStatusAndSupervisor(Status.PROPOSED, supervisor);
            acceptedProjects = pjtSrv.findProjectsByStatusAndSupervisor(Status.ACCEPTED, supervisor);
            return "admin";
        }
    }

    /**
     * Submits a report to a project.
     * @return View page.
     */
    public String submitReport() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        supervisor = (Supervisor) request.getSession().getAttribute("user");
        pjtSrv.registerReport(project, rptDetails);
        logger.warn("Report for Project " + title + " submitted by supervisor " + supervisor.getUniID() + " at " + timeSrv.inlineTimestamp());
        rptDetails = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Successfully submitted report."));
        return "view";
    }
}
