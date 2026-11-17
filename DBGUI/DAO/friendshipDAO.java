package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import Connection.DBconnection;
import Tables.USER;

public class friendshipDAO {

    // Insert a new record into the FRIENDSHIP table
    // For both (When a user adds a friend)
    public boolean insert(int user1_id, int user2_id) {
        String sql = "INSERT INTO FRIENDSHIP (User_ID, Friend_ID) VALUES (?, ?)";
        try (Connection connection = DBconnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, user1_id);
            statement.setInt(2, user2_id);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete a record from the FRIENDSHIP table based on the provided User_ID and Friend_ID
    // For both (When a user removes a friend)
    public boolean delete(int user1_id, int user2_id) {
        String sql = "DELETE FROM FRIENDSHIP WHERE User_ID = ? AND Friend_ID = ?";
        try (Connection connection = DBconnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, user1_id);
            statement.setInt(2, user2_id);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Select all friends of a specific user from the FRIENDSHIP table
    // For Regular Users only (to view the friends he added)
    public LinkedList<USER> selectFriends(int user_id) {
        String sql = "SELECT Friend_ID FROM FRIENDSHIP WHERE User_ID = ?";
        LinkedList<USER> friendsList = new LinkedList<>();
        try (Connection connection = DBconnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, user_id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                USER friend = new userDAO().select(resultSet.getInt("Friend_ID"));
                friendsList.add(friend);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return friendsList;
    }

}
