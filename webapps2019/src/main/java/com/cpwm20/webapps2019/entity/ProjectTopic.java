/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpwm20.webapps2019.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Project topic entity.
 * @author cpwm20
 */

@Entity
public class ProjectTopic implements Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Size(min=2, max=100)
    @Column(unique=true)
    private String title;
    
    @NotNull
    @Size(max=1000)
    private String description;
    
    @ManyToMany(targetEntity=Project.class)
    private HashSet<Project> projectSet;

    /**
     * Empty Constructor.
     */
    public ProjectTopic() {
    }

    /**
     * Constructs project topic with title and description.
     * @param title
     * @param description
     */
    public ProjectTopic(String title, String description) {
        this.title = title;
        this.description = description;
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
    public HashSet getProjectSet() {
        return projectSet;
    }

    /**
     *
     * @param projectSet
     */
    public void setProjectSet(HashSet projectSet) {
        this.projectSet = projectSet;
    }
    
    /**
     *
     * @param project
     */
    public void addProjectToSet(Project project) {
        projectSet.add(project);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.title);
        hash = 29 * hash + Objects.hashCode(this.description);
        hash = 29 * hash + Objects.hashCode(this.projectSet);
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
        final ProjectTopic other = (ProjectTopic) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.projectSet, other.projectSet)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProjectTopic{" + "id=" + id + ", title=" + title + ", description=" + description + ", projectSet=" + projectSet + '}';
    }

    
    
    
    
}
