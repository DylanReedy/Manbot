package com.rapidprototyping.manbot.controller.Project;

import com.rapidprototyping.manbot.controller.CommandContext;
import com.rapidprototyping.manbot.controller.CommandHandler;
import com.rapidprototyping.manbot.model.command.Command;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Dylan Reedy
 */
public class ProjectManager implements CommandHandler
{
    //Manbot should call these functions with the described signatures.

    //SIGNATURE: !project propose <name> <language>
    //Tells the database API to create a new project, adding the user that invokes the command to the project's member list. must check user access level.
    public void propose(Command command){}

    //SIGNATURE: !project vote <name>
    //adds the user that invoked the command to the project's member list.
    //this method should get the project's member count and, if it's reached the minimum, call begin(project name)
    public void vote(Command command){}

    //SIGNATURE: !project setstatus <name> <status>
    //alters the status of the given project - must parse the status string into an appropriate enum. must check user access level.
    public void setStatus(Command command){}

    //tell the database to begin the project, setting the status to Status.OPEN
    private void begin(Command command){}

    //print a list of all projects
    public void list(Command command){}

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void handle(CommandContext ctx) {
        String methodToCall = ctx.getCommand().getName().toLowerCase();
        Command cmd = ctx.getCommand();
        switch(ctx.getCommand().getName()){
            case ProjectCommandName.BEGIN:{
                begin(cmd);
                break;
            }
            case ProjectCommandName.LIST:{
                list(cmd);
                break;
            }
            case ProjectCommandName.PROPOSE:{
                propose(cmd);
                break;
            }
            case ProjectCommandName.SET_STATUS:{
                setStatus(cmd);
                break;
            }
            case ProjectCommandName.VOTE:{
                vote(cmd);
                break;
            }
            default:{
                break;
            }
        }
    }

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
