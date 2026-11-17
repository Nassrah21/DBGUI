package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import Connection.DBconnection;
import Enum.Role;
import Tables.CHAT;
import Tables.JOINS;
import Tables.USER;

public class joinsDAO{
    

    // Insert a new record into the JOINS table
    // For both (When a user joins a chat)
    public boolean insert(JOINS joins) {
        String sql = "INSERT INTO JOINS (Chat_ID, User_ID) VALUES (?, ?)";
        try (Connection connection = DBconnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, joins.getChat().getChat_id());
            statement.setInt(2, joins.getUser().getUser_id());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update an existing record in the JOINS table
    // For both 
    public boolean update(JOINS joins) {
        String sql = "UPDATE JOINS SET User_ID = ? WHERE Chat_ID = ?";
        try (Connection connection = DBconnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, joins.getUser().getUser_id());
            statement.setInt(2, joins.getChat().getChat_id());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete a record from the JOINS table based on the provided Chat_ID and User_ID
    // For both (When a user leaves a chat)
    public boolean delete(int chatId, int userId) {
        String sql = "DELETE FROM JOINS WHERE Chat_ID = ? AND User_ID = ?";
        try (Connection connection = DBconnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, chatId);
            statement.setInt(2, userId);

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Select a record from the JOINS table based on the provided Chat_ID and User_ID
    // For Regular Users only (to view the chat he joined)
    public JOINS select(int chatId, int userId) {
        String sql = "SELECT * FROM JOINS WHERE Chat_ID = ? AND User_ID = ?";
        try (Connection connection = DBconnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, chatId);
            statement.setInt(2, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                CHAT chat = new chatDAO().select(chatId);
                USER user = new userDAO().select(userId);
                return new JOINS(user,chat, Role.valueOf(resultSet.getString("Role")), resultSet.getObject("Join_Date", java.time.LocalDate.class), resultSet.getBoolean("Mute"), resultSet.getBoolean("Pin"), resultSet.getBoolean("Archive"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    // Select all records from the JOINS table
    // For Adminstrators only (to view all joins in the DB)
    public LinkedList<JOINS> selectAll() {
        String sql = "SELECT * FROM JOINS";
        LinkedList<JOINS> joinsList = new LinkedList<>();
        try (Connection connection = DBconnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                CHAT chat = new chatDAO().select(resultSet.getInt("Chat_id"));
                USER user = new userDAO().select(resultSet.getInt("User_id"));
                JOINS joins = new JOINS(user, chat, Role.valueOf(resultSet.getString("Role")), resultSet.getObject("Join_Date", java.time.LocalDate.class), resultSet.getBoolean("Mute"), resultSet.getBoolean("Pin"), resultSet.getBoolean("Archive"));
                joinsList.add(joins);
            }
            return joinsList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}
