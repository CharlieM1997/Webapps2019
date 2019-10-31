package com.cpwm20.webapps2019.entity;

import com.cpwm20.webapps2019.entity.Project.Status;
import com.cpwm20.webapps2019.entity.ProjectTopic;
import com.cpwm20.webapps2019.entity.Student;
import com.cpwm20.webapps2019.entity.Supervisor;
import java.util.List;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-17T15:37:58")
@StaticMetamodel(Project.class)
public class Project_ { 

    public static volatile SingularAttribute<Project, List> reports;
    public static volatile SetAttribute<Project, ProjectTopic> topicSet;
    public static volatile SingularAttribute<Project, Student> student;
    public static volatile SingularAttribute<Project, String> reqSkills;
    public static volatile SingularAttribute<Project, String> description;
    public static volatile SingularAttribute<Project, Long> id;
    public static volatile SingularAttribute<Project, String> title;
    public static volatile SingularAttribute<Project, Supervisor> supervisor;
    public static volatile SingularAttribute<Project, Status> status;

}