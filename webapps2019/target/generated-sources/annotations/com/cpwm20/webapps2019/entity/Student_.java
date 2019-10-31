package com.cpwm20.webapps2019.entity;

import com.cpwm20.webapps2019.entity.Project;
import com.cpwm20.webapps2019.entity.Supervisor;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-17T15:37:58")
@StaticMetamodel(Student.class)
public class Student_ extends SystemUser_ {

    public static volatile SingularAttribute<Student, String> course;
    public static volatile SingularAttribute<Student, Project> project;
    public static volatile SingularAttribute<Student, Supervisor> supervisor;

}