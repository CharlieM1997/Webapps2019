package com.cpwm20.webapps2019.entity;

import com.cpwm20.webapps2019.entity.Project;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2019-05-17T15:37:58")
@StaticMetamodel(ProjectTopic.class)
public class ProjectTopic_ { 

    public static volatile SingularAttribute<ProjectTopic, String> description;
    public static volatile SingularAttribute<ProjectTopic, Long> id;
    public static volatile SingularAttribute<ProjectTopic, String> title;
    public static volatile SetAttribute<ProjectTopic, Project> projectSet;

}