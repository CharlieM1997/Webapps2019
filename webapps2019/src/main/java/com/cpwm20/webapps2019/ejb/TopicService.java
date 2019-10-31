/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpwm20.webapps2019.ejb;

import com.cpwm20.webapps2019.entity.ProjectTopic;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 * Service for topics.
 * @author cpwm20
 */
@Stateless
public class TopicService {
    
    @PersistenceContext
    EntityManager em;

    /**
     * Empty Constructor.
     */
    public TopicService() {
    }
    
    /**
     * Registers a new topic.
     * @param title
     * @param description
     */
    public void registerTopic(String title, String description) {
        ProjectTopic topic = new ProjectTopic(title, description);
        
        em.persist(topic);
    }
    
    /**
     * Gets list of all topics.
     * @return list of topics
     */
    public List<ProjectTopic> getAllTopics() {
        try {
            List<ProjectTopic> allTopics = em.createQuery("SELECT p FROM ProjectTopic p",
                    ProjectTopic.class)
                    .getResultList();
            
            return allTopics;
        } catch (NoResultException e) {
            return null;
        }
    }
    
}
