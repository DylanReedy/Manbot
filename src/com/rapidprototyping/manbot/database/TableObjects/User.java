package TableObjects;

import java.util.ArrayList;

public class User {

    int experience;
    String handle;
    String language;
    String role;


    public User(int experience, String handle, String language, String role){
        this.experience = experience;
        this.handle = handle;
        this.language = language;
        this.role = role;
    }

    public User(){}

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


}
