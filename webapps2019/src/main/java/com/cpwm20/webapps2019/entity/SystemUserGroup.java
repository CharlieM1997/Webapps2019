package com.cpwm20.webapps2019.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * System User Group entity. Holds user's ID, university ID and their related group
 * (Student, Supervisor or Administrator).
 * @author cpwm20
 */
@Entity
public class SystemUserGroup implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Column(unique=true)
    private String uniID;

    @NotNull
    private String groupname;

    /**
     * Empty Constructor.
     */
    public SystemUserGroup() {
    }

    /**
     * Constructs group entity with given values.
     * @param uniID
     * @param groupName
     */
    public SystemUserGroup(String uniID, String groupName) {
        this.uniID = uniID;
        this.groupname = groupName;
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
    public String getGroupName() {
        return groupname;
    }

    /**
     *
     * @param groupName
     */
    public void setGroupName(String groupName) {
        this.groupname = groupName;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.uniID);
        hash = 89 * hash + Objects.hashCode(this.groupname);
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
        final SystemUserGroup other = (SystemUserGroup) obj;
        if (!Objects.equals(this.uniID, other.uniID)) {
            return false;
        }
        if (!Objects.equals(this.groupname, other.groupname)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SystemUserGroup{" + "id=" + id + ", uniID=" + uniID + ", groupname=" + groupname + '}';
    }

    

}
