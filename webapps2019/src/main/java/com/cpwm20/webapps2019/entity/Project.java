/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpwm20.webapps2019.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Project entity.
 * @author cpwm20
 */
@Entity
@XmlRootElement
public class Project implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 3, max = 200)
    private String title;

    @NotNull
    @Size(min = 20, max = 1000)
    private String description;

    @NotNull
    private String reqSkills;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToMany(targetEntity = ProjectTopic.class)
    private Set topicSet;

    @OneToOne(optional = true)
    private Student student;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    private Supervisor supervisor;
    
    private List<String> reports;

    /**
     * Status of the project; accepted, proposed or available.
     */
    public enum Status {
        ACCEPTED,
        PROPOSED,
        AVAILABLE;
    }

    /**
     * Empty constructor.
     */
    public Project() {

    }

    /**
     * Constructs project.
     * @param title
     * @param description
     * @param reqSkills
     * @param status
     * @param supervisor
     */
    public Project(String title, String description, String reqSkills, Status status, Supervisor supervisor) {
        this.title = title;
        this.description = description;
        this.reqSkills = reqSkills;
        this.status = status;
        this.supervisor = supervisor;
    }

    /**
     * Constructs project with topic set.
     * @param title
     * @param description
     * @param reqSkills
     * @param status
     * @param topicSet
     * @param supervisor
     */
    public Project(String title, String description, String reqSkills, Status status, Set topicSet, Supervisor supervisor) {
        this.title = title;
        this.description = description;
        this.reqSkills = reqSkills;
        this.status = status;
        this.topicSet = topicSet;
        this.supervisor = supervisor;
    }
    
    /**
     * Construct project with student.
     * @param title
     * @param description
     * @param reqSkills
     * @param status
     * @param student
     * @param supervisor
     */
    public Project(String title, String description, String reqSkills, Status status, Student student, Supervisor supervisor) {
        this.title = title;
        this.description = description;
        this.reqSkills = reqSkills;
        this.status = status;
        this.student = student;
        this.supervisor = supervisor;
    }

    /**
     * Constructs project with topic set and student.
     * @param title
     * @param description
     * @param reqSkills
     * @param status
     * @param topicSet
     * @param student
     * @param supervisor
     */
    public Project(String title, String description, String reqSkills, Status status, Set topicSet, Student student, Supervisor supervisor) {
        this.title = title;
        this.description = description;
        this.reqSkills = reqSkills;
        this.status = status;
        this.topicSet = topicSet;
        this.student = student;
        this.supervisor = supervisor;
    }

    /**
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
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
    public Status getStatus() {
        return status;
    }

    /**
     *
     * @param status
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     *
     * @return
     */
    @XmlTransient
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
    public List<String> getReports() {
        return reports;
    }

    /**
     *
     * @param reports
     */
    public void setReports(List<String> reports) {
        this.reports = reports;
    }
    
    /**
     *
     * @param report
     */
    public void addReport(String report) {
        if (reports == null) {
            reports = new ArrayList<>();
        }
        reports.add(report);
    }
    
    /**
     *
     * @return
     */
    public String reportsToString() {

        if (reports == null || reports.isEmpty()) {
            return null;
        }
        String reportsToString = "";
        int i = 0;
        for (String r : reports) {
            i++;
            reportsToString = reportsToString.concat("Report " + i + ":\n" + r + "\n\n");
        }
        return reportsToString;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.id);
        hash = 41 * hash + Objects.hashCode(this.title);
        hash = 41 * hash + Objects.hashCode(this.description);
        hash = 41 * hash + Objects.hashCode(this.reqSkills);
        hash = 41 * hash + Objects.hashCode(this.status);
        hash = 41 * hash + Objects.hashCode(this.topicSet);
        hash = 41 * hash + Objects.hashCode(this.student);
        hash = 41 * hash + Objects.hashCode(this.supervisor);
        hash = 41 * hash + Objects.hashCode(this.reports);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Project other = (Project) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.reqSkills, other.reqSkills)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.topicSet, other.topicSet)) {
            return false;
        }
        if (!Objects.equals(this.student, other.student)) {
            return false;
        }
        if (!Objects.equals(this.supervisor, other.supervisor)) {
            return false;
        }
        if (!Objects.equals(this.reports, other.reports)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Project{" + "id=" + id + ", title=" + title + ", description=" + description + ", reqSkills=" + reqSkills + ", status=" + status + ", topicSet=" + topicSet + ", student=" + student + ", supervisor=" + supervisor + ", reports=" + reports + '}';
    }
}
