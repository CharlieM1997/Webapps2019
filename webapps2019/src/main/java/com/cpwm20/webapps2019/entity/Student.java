/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpwm20.webapps2019.entity;

import java.util.Objects;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Construct student entity. Based off of SystemUser with unique values.
 * @author cpwm20
 */
@Entity
@DiscriminatorValue(value = "student")
@XmlRootElement
public class Student extends SystemUser {
    
    @NotNull
    private String course;
    
    @OneToOne(optional = true)
    private Project project;
    
    @ManyToOne(fetch = FetchType.EAGER)
    private Supervisor supervisor;

    /**
     * Empty Constructor.
     */
    public Student() {
        super();
    }

    /**
     * Constructs student.
     * @param uniID
     * @param userpassword
     * @param name
     * @param surname
     * @param email
     * @param course
     */
    public Student(String uniID, String userpassword, String name, String surname, String email,
            String course) {
        super(uniID, userpassword, name, surname, email);
        this.course = course;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.course);
        hash = 47 * hash + Objects.hashCode(this.project);
        hash = 47 * hash + Objects.hashCode(this.supervisor);
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
        final Student other = (Student) obj;
        if (!Objects.equals(this.course, other.course)) {
            return false;
        }
        if (!Objects.equals(this.project, other.project)) {
            return false;
        }
        if (!Objects.equals(this.supervisor, other.supervisor)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Student{" + "course=" + course + ", project=" + project + ", supervisor=" + supervisor + '}';
    }
    
    
    
}
