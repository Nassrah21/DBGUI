package Connection;
import java.sql.Connection;
import java.sql.DriverManager;
public class DBconnection {
    private static final String URL = "jdbc:mysql://localhost:3306/line";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static Connection getConnection(){
        try{
            return DriverManager.getConnection(URL, USERNAME, PASSWORD); // Establishes a connection to the database using the provided URL, username, and password.
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
    }
}
