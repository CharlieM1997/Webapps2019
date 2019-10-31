/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cpwm20.webapps2019.ejb;

import com.cpwm20.webapps2019.entity.Project;
import com.cpwm20.webapps2019.entity.Project.Status;
import com.cpwm20.webapps2019.entity.Student;
import com.cpwm20.webapps2019.entity.Supervisor;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TransactionRequiredException;

/**
 * Project Service. Deals with registering, finding and modifying projects.
 * @author cpwm20
 */
@Stateless
public class ProjectService {

    @PersistenceContext
    EntityManager em;

    /**
     * Empty constructor.
     */
    public ProjectService() {
    }

    /**
     * Registers a project by a supervisor.
     * @param title
     * @param description
     * @param reqSkills
     * @param status
     * @param supervisor
     */
    public void registerProject(String title, String description, String reqSkills, Status status, Supervisor supervisor) {
        Project project = new Project(title, description, reqSkills, status, supervisor);
        em.persist(project);
    }

    /**
     *Registers a project with a topic set.
     * @param title
     * @param description
     * @param reqSkills
     * @param status
     * @param topicSet
     * @param supervisor
     */
    public void registerProjectWithSet(String title, String description, String reqSkills, Status status, Set topicSet, Supervisor supervisor) {
        Project project = new Project(title, description, reqSkills, status, topicSet, supervisor);

        em.persist(project);
    }

    /**
     * Registers a project by a student.
     * @param title
     * @param description
     * @param reqSkills
     * @param status
     * @param student
     * @param supervisor
     */
    public void registerProjectWithStudent(String title, String description, String reqSkills, Status status, Student student, Supervisor supervisor) {
        Project project = new Project(title, description, reqSkills, status, student, supervisor);

        em.persist(project);
    }

    /**
     * Registers a project by a student with a topic set.
     * @param title
     * @param description
     * @param reqSkills
     * @param status
     * @param topicSet
     * @param student
     * @param supervisor
     */
    public void registerProjectWithStudentAndSet(String title, String description, String reqSkills, Status status, Set topicSet, Student student, Supervisor supervisor) {
        Project project = new Project(title, description, reqSkills, status, topicSet, student, supervisor);

        em.persist(project);
    }

    /**
     * Finds a project by its unique id.
     * @param id The unique id
     * @return Project entity if exists, null if not
     */
    public Project findProject(Long id) {
        return em.find(Project.class, id);
    }

    /**
     * Finds all projects and enters them into a list.
     * @return list of all projects.
     */
    public List<Project> findAllProjects() {
        try {
            List<Project> allProjects = em.createQuery("SELECT p FROM Project p",
                    Project.class)
                    .getResultList();
            
            return allProjects;
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Finds project by their status
     * @param status
     * @return List of projects with given status
     */
    public List<Project> findProjectsByStatus(Status status) {
        try {
            List<Project> ppdProjects = em.createQuery("SELECT p FROM Project p WHERE p.status = :status",
                    Project.class)
                    .setParameter("status", status)
                    .getResultList();

            return ppdProjects;
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Finds project by a supervisor with a given status
     * @param status
     * @param supervisor
     * @return List of projects by supervisor with given status
     */
    public List<Project> findProjectsByStatusAndSupervisor(Status status, Supervisor supervisor) {
        try {
            List<Project> ppdProjects = em.createQuery("SELECT p FROM Project p WHERE p.status = :status AND p.supervisor = :supervisor",
                    Project.class)
                    .setParameter("status", status)
                    .setParameter("supervisor", supervisor)
                    .getResultList();
            return ppdProjects;
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Finds project connected to student
     * @param student
     * @return Project if exists, null if not
     */
    public Project findProjectByStudent(Student student) {
        try {
            Project project = em.createQuery("SELECT p FROM Project p WHERE p.student = :student",
                    Project.class)
                    .setParameter("student", student)
                    .getSingleResult();

            return project;
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Commits request by student, changes project to proposed and links student.
     * @param project
     * @param student
     */
    public void requestProject(Project project, Student student) {
        project.setStatus(Status.PROPOSED);
        project.setStudent(student);
        em.merge(project);
    }

    /**
     * Changes project status to accepted.
     * @param project
     */
    public void acceptProject(Project project) {
        project.setStatus(Status.ACCEPTED);
        em.merge(project);
        /**
         * Query query = em.createQuery("UPDATE Project SET status = ACCEPTED "
         * + "WHERE id = :id") .setParameter("id", project.getId());
         * query.executeUpdate();
         */
    }

    /**
     * Removes project from the database.
     * @param project
     */
    public void removeProject(Project project) {
        try {
            project = em.merge(project);
            em.remove(project);
        } catch (TransactionRequiredException e) {
            em.getTransaction().begin();
            project = em.merge(project);
            em.remove(project);
            em.getTransaction().commit();
        }
    }

    /**
     * Deselects a project. Removes student and marks project as available
     * @param project
     */
    public void deselectProject(Project project) {
        project.setStudent(null);
        project.setReports(null);
        project.setStatus(Status.AVAILABLE);
        em.merge(project);
    }

    /**
     * Registers a report connected to a project.
     * @param project
     * @param report
     */
    public void registerReport(Project project, String report) {
        project.addReport(report);
        em.merge(project);
    }
}
