import TableObjects.Project;
import TableObjects.Status;
import TableObjects.User;
import com.sun.prism.shader.Solid_TextureRGB_AlphaTest_Loader;

import java.sql.*;
import java.util.ArrayList;

public class SQLearning {

    private static final String USERNAME = "sid";
    private static final String PASSWORD = "1qa2ws3ed4rf";
    private static final String CONN_STRING = "jdbc:mysql://localhost/manbot";

    public static void main(String args[]) throws SQLException {

        ManbotDBHelper helper = new ManbotDBHelper();
        ArrayList<Project> projects = helper.getOpenProjects();
        for (Project project: projects) {
            System.out.println(project.getName());
        }
    }
}
