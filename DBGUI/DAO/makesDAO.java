package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import Connection.DBconnection;

public class makesDAO {
    
    // Insert a new record into the MAKES table
    // For both (When a call is made)
    public boolean insert(int user_id, int call_id) {
        String sql = "INSERT INTO MAKES (Call_ID, User_ID) VALUES (?, ?)";
        try (Connection connection = DBconnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, call_id);
            statement.setInt(2, user_id);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
