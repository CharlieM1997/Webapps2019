package com.cpwm20.webapps2019.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * SystemUser entity. Is extended by student and supervisor.
 * @author cpwm20
 */
@Entity
@Table(name = "SYSTEMUSER")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type")
public class SystemUser implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;


    @NotNull
    @Column(unique=true)
    private String uniID;
    
    @NotNull
    String userpassword;

    @NotNull
    String name;

    @NotNull
    String surname;
    
    @NotNull
    @Column(unique=true)
    @Email
    String email;

    /**
     * Empty Constructor.
     */
    public SystemUser() {
        super();
    }

    /**
     * Constructs system user.
     * @param uniID
     * @param userpassword
     * @param name
     * @param surname
     * @param email
     */
    public SystemUser(String uniID, String userpassword, String name, String surname, String email) {
        super();
        this.uniID = uniID;
        this.userpassword = userpassword;
        this.name = name;
        this.surname = surname;
        this.email = email;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.id);
        hash = 41 * hash + Objects.hashCode(this.uniID);
        hash = 41 * hash + Objects.hashCode(this.userpassword);
        hash = 41 * hash + Objects.hashCode(this.name);
        hash = 41 * hash + Objects.hashCode(this.surname);
        hash = 41 * hash + Objects.hashCode(this.email);
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
        final SystemUser other = (SystemUser) obj;
        if (!Objects.equals(this.uniID, other.uniID)) {
            return false;
        }
        if (!Objects.equals(this.userpassword, other.userpassword)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.surname, other.surname)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SystemUser{" + "id=" + id + ", uniID=" + uniID + ", userpassword=" + userpassword + ", name=" + name + ", surname=" + surname + ", email=" + email + '}';
    }

    

}
