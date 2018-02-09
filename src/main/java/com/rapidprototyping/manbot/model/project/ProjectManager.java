package com.rapidprototyping.manbot.model.project;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Pavan C (pavan407)
 */
public enum ProjectManager
{
    INSTANCE;

    //Manbot should call these functions with the described signatures.

    //SIGNATURE: !project propose <name> <language>
    //Tells the database API to create a new project, adding the user that invokes the command to the project's member list. must check user access level.
    public void propose(String user, String projectName, String projectLanguage){}

    //SIGNATURE: !project vote <name>
    //adds the user that invoked the command to the project's member list.
    //this method should get the project's member count and, if it's reached the minimum, call begin(project name)
    public void vote(String user, String project){}

    //SIGNATURE: !project setstatus <name> <status>
    //alters the status of the given project - must parse the status string into an appropriate enum. must check user access level.
    public void setStatus(String user, String project, Status status){}

    //tell the database to begin the project, setting the status to Status.OPEN
    private void begin(String project){}

    //print a list of all projects
    public void list(){}

//    private static Map<String, Project> projects = new HashMap<>();
//
//    static
//    {
//        Project manbot = new Project("Manbot", "A discord chat bot, the technology behind me!");
//        projects.put("manbot", manbot);
//    }
//
//    public static Project getProject(String name)
//    {
//        return projects.get(name);
//    }
//
//    public static Collection<Project> getProjects()
//    {
//        return projects.values();
//    }
//
//    public static void addProject(Project p)
//    {
//        projects.put(p.getName(), p);
//    }
}
