/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpwm20.webapps2019.entity;

import java.util.Objects;
import java.util.Set;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Supervisor entity. Based off of SystemUser entity with unique values.
 * International phone number regexp is used to validate phone numbers.
 * @author cpwm20
 */
@Entity
@DiscriminatorValue(value="supervisor")
@XmlRootElement
public class Supervisor extends SystemUser {
    
    @NotNull
    private String department;
    
    @NotNull
    @Pattern(regexp="((\\+[1-9]{3,4}|0[1-9]{4}|00[1-9]{3})\\-?)?\\d{8,20}")
    private String phoneNum;
    
    @NotNull
    private boolean isAdmin;
    
    @OneToMany(targetEntity=Project.class)
    private Set projects;
    
    @OneToMany(targetEntity=Student.class)
    private Set students;

    /**
     * Empty Constructor.
     */
    public Supervisor() {
        super();
    }

    /**
     * Constructors supervisor entity with given values.
     * @param uniID
     * @param userpassword
     * @param name
     * @param surname
     * @param email
     * @param department
     * @param phoneNum
     * @param isAdmin
     */
    public Supervisor(String uniID, String userpassword, String name, String surname, String email,
            String department, String phoneNum, boolean isAdmin) {
        super(uniID, userpassword, name, surname, email);
        
        this.department = department;
        this.phoneNum = phoneNum;
        this.isAdmin = isAdmin;
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
    public boolean isadmin() {
        return isAdmin;
    }

    /**
     *
     * @param isAdmin
     */
    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    /**
     *
     * @return
     */
    @XmlTransient
    public Set getProjects() {
        return projects;
    }

    /**
     *
     * @param projects
     */
    public void setProjects(Set projects) {
        this.projects = projects;
    }

    /**
     *
     * @return
     */
    @XmlTransient
    public Set getStudents() {
        return students;
    }

    /**
     *
     * @param students
     */
    public void setStudents(Set students) {
        this.students = students;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.department);
        hash = 59 * hash + Objects.hashCode(this.phoneNum);
        hash = 59 * hash + (this.isAdmin ? 1 : 0);
        hash = 59 * hash + Objects.hashCode(this.projects);
        hash = 59 * hash + Objects.hashCode(this.students);
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
        final Supervisor other = (Supervisor) obj;
        if (this.isAdmin != other.isAdmin) {
            return false;
        }
        if (!Objects.equals(this.department, other.department)) {
            return false;
        }
        if (!Objects.equals(this.phoneNum, other.phoneNum)) {
            return false;
        }
        if (!Objects.equals(this.projects, other.projects)) {
            return false;
        }
        if (!Objects.equals(this.students, other.students)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Supervisor{" + "department=" + department + ", phoneNum=" + phoneNum + ", isAdmin=" + isAdmin + ", projects=" + projects + ", students=" + students + '}';
    }
    
    
}
