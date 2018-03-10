package TableObjects;

public class Project {

    String name;
    String language;
    String description;
    Status status;

    public Project(String name, String language, String description, Status status){
        this.name = name;
        this.language = language;
        this.description = description;
        this.status = status;
    }

    public Project(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}


