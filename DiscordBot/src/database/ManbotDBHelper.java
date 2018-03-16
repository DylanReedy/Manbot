package database;

import database.TableObjects.Project;
import database.TableObjects.Status;
import database.TableObjects.RPUser;

import java.sql.*;
import java.util.ArrayList;

public class ManbotDBHelper {
    private static final String USERNAME = "sid";
    private static final String PASSWORD = "1qa2ws3ed4rf";
    private static final String CONN_STRING = "jdbc:mysql://localhost/manbot";


    Connection conn = null;
    Statement statement = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public ManbotDBHelper(){
    }

    // Project table methods
    public Project getProject(String name) throws SQLException {
        Project project = null;
        try {
            conn = DriverManager.getConnection(CONN_STRING,USERNAME,PASSWORD);
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery("SELECT * FROM projects WHERE name='"+name+"'");
            project = parseProject(resultSet);
        } catch (SQLException e) {
            System.err.println(e);
        }finally{
            if(conn != null){
                conn.close();
            }
        }

            return project;
    }

    public ArrayList<Project> getAllProjects() throws SQLException{
        ArrayList<Project> projects = null;
        try {
            conn = DriverManager.getConnection(CONN_STRING,USERNAME,PASSWORD);
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery("SELECT * FROM projects");
            projects = parseProjects(resultSet);
        } catch (SQLException e) {
            System.err.println(e);
        }finally{
            if(conn != null){
                conn.close();
            }
        }

        return projects;
    }

    public ArrayList<Project> getOpenProjects() throws SQLException{
        ArrayList<Project> projects = null;
        try {
            conn = DriverManager.getConnection(CONN_STRING,USERNAME,PASSWORD);
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery("SELECT * FROM projects WHERE status!='closed' ");
            projects = parseProjects(resultSet);
        } catch (SQLException e) {
            System.err.println(e);
        }finally{
            if(conn != null){
                conn.close();
            }
        }

        return projects;
    }

    public String newProject(Project project) throws SQLException{
        String message;

        try {
            conn = DriverManager.getConnection(CONN_STRING,USERNAME,PASSWORD);
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery("SELECT * FROM projects WHERE name='"+project.getName()+"' LIMIT 1");
            if(resultSet.next()){
                message = "Project already exists in table.";
            }else{
                preparedStatement = conn.prepareStatement(GetInsertProjectStatement(project));;
                preparedStatement.execute();
                message = String.format("Project %s successfully added to database!", project.getName());}
            } catch (SQLException e) {
                System.err.println(e);
                message = e.getMessage().toString();
            }finally{
                if(conn != null){
                    conn.close();
                }
            }

            return message;
    }

    public String setProjectStatus(String name, Status status) throws SQLException {
        String message;
        try {
            conn = DriverManager.getConnection(CONN_STRING,USERNAME,PASSWORD);
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE projects SET status='"+status.toString()+"' WHERE name='"+name+"'");
            preparedStatement.execute();
            message = String.format("Project %s status updated to %s", name, status.toString());
        } catch (SQLException e) {
            System.err.println(e);
            message = e.getMessage().toString();
        }finally{
            if(conn != null){
                conn.close();
            }
        }

        return message;
    }

    String GetInsertProjectStatement(Project project){
        String statement = String.format(String.format("INSERT INTO projects VALUES ('%s','%s','%s','%s')",project.getName(),
            project.getLanguage(),project.getDescription(), project.getStatus().toString()));
        return statement;
    }

    Project parseProject(ResultSet resultSet) throws SQLException {
        resultSet.first();
        Project project = new Project();
        project.setName(resultSet.getString("name"));
        project.setDescription(resultSet.getString("name"));
        project.setLanguage(resultSet.getString("language"));
        String string = resultSet.getString("status");
        project.setStatus(Status.valueOf(resultSet.getString("status").toUpperCase()));
        return project;
    }

    ArrayList<Project> parseProjects (ResultSet resultSet) throws SQLException{
        ArrayList<Project> projects = new ArrayList<Project>();
        while(resultSet.next()){
            Project project = new Project();
            project.setName(resultSet.getString("name"));
            project.setDescription(resultSet.getString("name"));
            project.setLanguage(resultSet.getString("language"));
            project.setStatus(Status.valueOf(resultSet.getString("status").toUpperCase()));
            projects.add(project);
        }

        return projects;
    }

    //user table methods
    public RPUser getUser(String name)throws SQLException{
        RPUser user = null;
        try {
            conn = DriverManager.getConnection(CONN_STRING,USERNAME,PASSWORD);
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery("SELECT * FROM users WHERE name='"+name+"'");
            user = parseUser(resultSet);
        } catch (SQLException e) {
            System.err.println(e);
        }finally{
            if(conn != null){
                conn.close();
            }
        }

        return user;
    }

    RPUser parseUser(ResultSet resultSet) throws SQLException {
        resultSet.first();
        RPUser user = new RPUser();
        user.setHandle(resultSet.getString("handle"));
        user.setExperience(resultSet.getInt("experience"));
        user.setLanguage(resultSet.getString("language"));
        user.setRole(resultSet.getString("role"));
        return user;
    }

    public String newUser(RPUser user) throws SQLException{
        String message;

        try {
            conn = DriverManager.getConnection(CONN_STRING,USERNAME,PASSWORD);
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery("SELECT * FROM users WHERE handle='"+user.getHandle()+"' LIMIT 1");
            if(resultSet.next()){
                message = "User already exists in table.";
            }else{
                preparedStatement = conn.prepareStatement(GetInsertUserStatement(user));;
                preparedStatement.execute();
                message = String.format("User %s successfully added to database!", user.getHandle());}
            } catch (SQLException e) {
                System.err.println(e);
                message = e.getMessage().toString();
            }finally{
                if(conn != null){
                    conn.close();
                }
            }
            return message;
    }

    String GetInsertUserStatement(RPUser user){
        String statement = String.format(String.format("INSERT INTO users VALUES ('%s','%s','%d','%s')",user.getHandle(),
            user.getLanguage(),user.getExperience(), user.getRole()));
        return statement;
    }

    //voting methods
    //TODO: voting will currently just add a user to a project list. checking the status of a vote will return the count on the users for a given project
    public String castVote(RPUser user, Project project) throws SQLException{
        String message;

        //verification requirements:



        try {
            conn = DriverManager.getConnection(CONN_STRING,USERNAME,PASSWORD);
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            //1) check if project exists
            resultSet = statement.executeQuery("SELECT * FROM projects WHERE name='"+project.getName()+"'");
            if(!resultSet.next()){
                message = "Project does not exist in the database.";
                return message;
            }
            //2) user exists (add them to the user table if not)
            resultSet = statement.executeQuery("SELECT * FROM users WHERE handle='"+user.getHandle()+"'");
            if(!resultSet.next()){
                newUser(user);
            }
            //3) user is not in any other active projects
            resultSet = statement.executeQuery("SELECT * FROM votes WHERE user_handle='"+user.getHandle()+"'");
            while(resultSet.next()){
                Statement checkProjectsStatement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                ResultSet checkProjectsResult = checkProjectsStatement.executeQuery("SELECT * FROM projects WHERE status != '"+Status.CLOSED+"'");
                while(checkProjectsResult.next()){
                    if(resultSet.getString("project_name").equals(checkProjectsResult.getString("name"))){
                        message = "User is currently involved in active projects. Cannot vote for a new one.";
                        return message;
                    }
                }
            }
            //4) user hasn't voted for the project already
            resultSet = statement.executeQuery("SELECT * FROM votes WHERE user_handle='"+user.getHandle()+"' AND project_name='"+project.getName()+"' LIMIT 1");
            if(resultSet.next()){
                message = "User has already voted for this project.";
            }else{
                preparedStatement = conn.prepareStatement(GetVoteStatement(user.getHandle(), project.getName()));;
                preparedStatement.execute();
                message = String.format("%s has voted for project %s!", user.getHandle(), project.getName());}
            } catch (SQLException e) {
                System.err.println(e);
                message = e.getMessage().toString();
            }finally{
                if(conn != null){
                    conn.close();
                }
            }
            return message;
    }

    String GetVoteStatement(String userName, String projectName){
        String statement = String.format(String.format("INSERT INTO votes VALUES ('%s','%s','%s')",projectName,
                userName, Status.NEW));
        return statement;
    }

//        try {
//            conn = DriverManager.getConnection(CONN_STRING,USERNAME,PASSWORD);
//            statement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
//            statement.execute("INSERT INTO projects VALUES (3, 'my next project', 'c++', 'stuff')");
//            resultSet = statement.executeQuery("SELECT * FROM Projects");
//            while(resultSet.next()){
//                System.out.println("Project name: " + resultSet.getString("name"));
//                System.out.println("Project name: " + resultSet.getString("language"));
//            }
//        } catch (SQLException e) {
//            System.err.println(e);
//        }finally{
//            if(conn != null){
//                conn.close();
//            }
//        }
}
