package controller.Project;

//imported User entity. Maybe we want this to be RPUser?
import net.dv8tion.jda.core.entities.User;

import java.util.List;

/**
 *
 * @author Pavan C (pavan407)
 */
public class Project
{
    private String name;
    private String description;

    private List<User> maintainers;
    private List<User> contributors;

    public Project(String name, String description)
    {
        this.name = name;
        this.description = description;
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    public List<User> getMaintainers()
    {
        return maintainers;
    }

    public List<User> getContributors()
    {
        return contributors;
    }
}
