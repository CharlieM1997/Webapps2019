package com.cpwm20.webapps2019.jsf;

import com.cpwm20.webapps2019.ejb.TimestampService;
import com.cpwm20.webapps2019.ejb.UserService;
import com.cpwm20.webapps2019.entity.SystemUser;
import com.cpwm20.webapps2019.entity.SystemUserGroup;
import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

/**
 * Login Bean. Validates user details and directs them to their respective group
 * page.
 * @author cpwm20
 */
@Named
@SessionScoped
public class LoginBean implements Serializable {

    private String uniID;
    private String userpassword;
    private String originalURL;

    /**
     * Retains current page.
     */
    @PostConstruct
    public void init() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        originalURL = (String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_REQUEST_URI);

        if (originalURL == null) {
            originalURL = externalContext.getRequestContextPath() + "/index.xhtml";
        } else {
            String originalQuery = (String) externalContext.getRequestMap().get(RequestDispatcher.FORWARD_QUERY_STRING);

            if (originalQuery != null) {
                originalURL += "?" + originalQuery;
            }
        }
    }

    @Inject
    private UserService userSrv;
    
    @Inject
    private TimestampService timeSrv;
    
    private static final Logger logger = Logger.getLogger(LoginBean.class);

    /**
     * Logs user in. Finds their value from the database, confirms the login,
     * logs their login and directs them to the correct page.
     * @return String of next location, covered by faces-config.
     * @throws IOException
     */
    public String login() throws IOException {
        
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context
                .getExternalContext().getRequest();

        SystemUser user = userSrv.findUser(uniID, userpassword);
        if (user == null) {
            return "error";
        }
        try {
            request.login(uniID, userpassword);
        } catch (ServletException e) {
            return "error";
        }
        SystemUserGroup group = userSrv.findGroup(uniID);
        if (group == null) {
            //this shouldn't happen
            return "error";
        } else if (group.getGroupName().equals("students")) {
            context.getExternalContext().getSessionMap().put("user", user);
            logger.warn("Student " + uniID + " logged in at " + timeSrv.inlineTimestamp());
            return "student";
        } else if (group.getGroupName().equals("supervisors")) {
            context.getExternalContext().getSessionMap().put("user", user);
            logger.warn("Supervisor " + uniID + " logged in at " + timeSrv.inlineTimestamp());
            return "supervisor";
        } else if (group.getGroupName().equals("admins")) {
            context.getExternalContext().getSessionMap().put("user", user);
            logger.warn("Administrator " + uniID + " logged in at " + timeSrv.inlineTimestamp());
            return "supervisor";
        } else {
            //this also shouldn't happen
            return "error";
        }
    }

    /**
     * Logs user out. Invalidates the session.
     * @throws IOException
     */
    public void logout() throws IOException {
        ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
        extContext.invalidateSession();
        extContext.redirect(extContext.getRequestContextPath() + "/faces/index.xhtml");
        timeSrv.timestamp();
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
    public UserService getUserService() {
        return userSrv;
    }

    /**
     *
     * @param userService
     */
    public void setUserService(UserService userService) {
        this.userSrv = userService;
    }

}
