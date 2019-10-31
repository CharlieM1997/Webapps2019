/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpwm20.webapps2019.jsf;

import com.cpwm20.webapps2019.ejb.TimestampService;
import com.cpwm20.webapps2019.ejb.TopicService;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * Topic Bean. Covers registration of a project topic.
 * @author cpwm20
 */
@Named
@ViewScoped
public class TopicBean implements Serializable {
    
    @Inject
    private TopicService tpcSrv;
    
    @Inject
    private TimestampService timeSrv;
    
    String title;
    String description;

    /**
     * Empty Constructor.
     */
    public TopicBean() {
    }
    
    /**
     * Registers a new topic.
     * @return Supervisor page.
     */
    public String register() {
        tpcSrv.registerTopic(title, description);
        timeSrv.timestamp();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Project topic successfully registered."));
        return "supervisor";
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
    
    
    
}
