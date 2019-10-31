package com.cpwm20.webapps2019.entity;

import com.cpwm20.webapps2019.entity.Project;
import com.cpwm20.webapps2019.entity.Student;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-17T15:37:58")
@StaticMetamodel(Supervisor.class)
public class Supervisor_ extends SystemUser_ {

    public static volatile SetAttribute<Supervisor, Project> projects;
    public static volatile SetAttribute<Supervisor, Student> students;
    public static volatile SingularAttribute<Supervisor, String> phoneNum;
    public static volatile SingularAttribute<Supervisor, Boolean> isAdmin;
    public static volatile SingularAttribute<Supervisor, String> department;

}