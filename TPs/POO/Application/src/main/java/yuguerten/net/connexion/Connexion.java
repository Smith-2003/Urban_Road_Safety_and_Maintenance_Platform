package yuguerten.net.connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
    private static final String SERVER = "localhost";
    private static final int PORT = 3306;
    private static final String DATABASE = "Rh";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "";


    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        String url = "jdbc:mysql://" + SERVER + ":" + PORT + "/" + DATABASE;
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(url, LOGIN, PASSWORD);
    }
}
