package com.cpwm20.webapps2019.ejb;

import com.cpwm20.webapps2019.entity.Student;
import com.cpwm20.webapps2019.entity.Supervisor;
import com.cpwm20.webapps2019.entity.SystemUser;
import com.cpwm20.webapps2019.entity.SystemUserGroup;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * Service for users.
 * @author cpwm20
 */
@Stateless
public class UserService {

    @PersistenceContext
    EntityManager em;

    /**
     * Empty Constructor.
     */
    public UserService() {
    }

    /**
     * Registers a student with given details.
     * @param uniID
     * @param userpassword
     * @param name
     * @param surname
     * @param email
     * @param course
     */
    public void registerStudent(String uniID, String userpassword, String name, String surname, String email, String course) {
        String paswdToStoreInDB = encrypt(userpassword);

        // apart from the default constructor which is required by JPA
        // you need to also implement a constructor that will make the following code succeed
        Student student = new Student(uniID, paswdToStoreInDB, name, surname, email, course);
        SystemUserGroup sys_user_group = new SystemUserGroup(uniID, "students");

        //Debug code to test for Bean constraint violations
        /**
         * ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
         * Validator validator = factory.getValidator();
         *
         * Set<ConstraintViolation<Student>> constraintViolations =
         * validator.validate(student);
         *
         * if (constraintViolations.size() > 0) { System.out.println("Constraint
         * Violations occurred.."); for (ConstraintViolation<Student> contraints
         * : constraintViolations) {
         * System.out.println(contraints.getRootBeanClass().getSimpleName() +
         * "." + contraints.getPropertyPath() + " " + contraints.getMessage());
         * } }
         */
        em.persist(student);
        em.persist(sys_user_group);
    }

    /**
     * Registers a supervisor with given details.
     * @param uniID
     * @param userpassword
     * @param name
     * @param surname
     * @param email
     * @param department
     * @param phoneNum
     * @param is_admin
     */
    public void registerSupervisor(String uniID, String userpassword, String name, String surname, String email, String department, String phoneNum, boolean is_admin) {
        String paswdToStoreInDB = encrypt(userpassword);

        Supervisor supervisor = new Supervisor(uniID, paswdToStoreInDB, name, surname, email, department, phoneNum, is_admin);
        SystemUserGroup sys_user_group;
        if (is_admin == true) {
            sys_user_group = new SystemUserGroup(uniID, "admins");
        } else {
            sys_user_group = new SystemUserGroup(uniID, "supervisors");
        }

        em.persist(supervisor);
        em.persist(sys_user_group);
    }

    /**
     * Finds a user based on their unique id.
     * @param id
     * @return User based on id, null if does not exist
     */
    public SystemUser findUser(Long id) {
        return em.find(SystemUser.class, id);
    }

    /**
     * Finds user based on their University ID and password.
     * @param uniID
     * @param userpassword
     * @return User if exists, null if not
     */
    public SystemUser findUser(String uniID, String userpassword) {
        String paswdInDB = encrypt(userpassword);
        try {
            SystemUser user = em.createQuery("SELECT s FROM SystemUser s WHERE s.uniID = :uniID AND s.userpassword = :userpassword",
                    SystemUser.class)
                    .setParameter("uniID", uniID)
                    .setParameter("userpassword", paswdInDB)
                    .getSingleResult();
            
            return user;
        } catch (NoResultException e) {
            return null;
        }
    }
    
    /**
     * Gets list of all users.
     * @return List of users.
     */
    public List<SystemUser> getAllUsers() {
        try {
            List<SystemUser> allUsers = em.createQuery("SELECT s FROM SystemUser s",
                    SystemUser.class)
                    .getResultList();
            
            return allUsers;
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Finds the group that a user is connected to.
     * @param uniID
     * @return Group if user exists, null if not
     */
    public SystemUserGroup findGroup(String uniID) {
        try {
            SystemUserGroup group = em.createQuery("SELECT s FROM SystemUserGroup s WHERE s.uniID = :uniID",
                    SystemUserGroup.class)
                    .setParameter("uniID", uniID)
                    .getSingleResult();
            
            return group;
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Gets list of supervisors.
     * @return List of supervisors.
     */
    public List<Supervisor> getSupervisors() {
        try {
            List<Supervisor> allSupervisors = em.createQuery("SELECT s FROM Supervisor s",
                    Supervisor.class)
                    .getResultList();
            
            return allSupervisors;
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Encrypts a password.
     * @param password
     * @return Encrypted password.
     */
    public String encrypt(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes("UTF-8"));
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < digest.length; i++) {
                sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
            }
            
            
            return sb.toString();
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
