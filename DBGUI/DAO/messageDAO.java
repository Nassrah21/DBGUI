package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.LinkedList;

import Connection.DBconnection;
import Enum.MSG_Type;
import Tables.CHAT;
import Tables.MESSAGE;
import Tables.USER;

public class messageDAO {

    // Insert a new message into the MESSAGE table
    // For both (When a message is sent)
    public boolean insert(MESSAGE message) {
        String sql = "INSERT INTO MESSAGE (Message_ID, Chat_ID, User_ID, Message_content, Sent_Time, Read_Time, Message_Type) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBconnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, message.getMessage_id());
            statement.setInt(2, message.getChat().getChat_id());
            statement.setInt(3, message.getSender().getUser_id());
            statement.setString(4, message.getContent());
            statement.setObject(5, message.getSent_Time());
            statement.setObject(6, message.getRead_Time());
            statement.setString(7, message.getMessage_type().toString());

            // If the message type is STICKER, insert a record into the USED_IN table to associate the sticker with the message and chat
            if(message.getMessage_type() == MSG_Type.STICKER)
                new used_inDAO().insert(Integer.parseInt(message.getContent()), message.getMessage_id(), message.getChat().getChat_id());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update an existing message in the MESSAGE table
    // For both (When a message is edited)
    public boolean update(MESSAGE message) {
        String sql = "UPDATE MESSAGE SET Message_content = ?, Message_content = ?, Message_Type = ? WHERE Message_ID = ?";
        try (Connection connection = DBconnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, message.getContent());
            statement.setObject(2, message.getSent_Time());
            statement.setString(3, message.getMessage_type().toString());
            statement.setInt(4, message.getMessage_id());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete a message from the MESSAGE table based on the provided Message_ID
    // For both (When a message is deleted)
    public boolean delete(int id) {
        String sql = "DELETE FROM MESSAGE WHERE Message_ID = ?";
        try (Connection connection = DBconnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            // We didn't delete the record from USED_IN table because it will be deleted automatically when the message is deleted due to the foreign key constraint with ON DELETE CASCADE.

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Select a message from the MESSAGE table based on the provided Message_ID
    // For Regular Users only (to view the message he sent or received)
    public MESSAGE select(int id, int user_id) {
        String sql = "SELECT * FROM MESSAGE WHERE Message_ID = ? AND Chat_ID IN (SELECT Chat_ID FROM JOINS WHERE User_ID = ?)";
        try (Connection connection = DBconnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.setInt(2, user_id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                CHAT chat = new chatDAO().select(resultSet.getInt("Chat_ID"));
                USER sender = new userDAO().select(resultSet.getInt("User_ID"));
                return new MESSAGE(
                    resultSet.getInt("Message_ID"),
                    MSG_Type.valueOf(resultSet.getString("Message_Type")),
                    resultSet.getString("Message_content"),
                    resultSet.getObject("Sent_Time", LocalDateTime.class),
                    resultSet.getObject("Read_Time", LocalDateTime.class),
                    chat, 
                    sender 
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public LinkedList<MESSAGE> retrievePerChat(int Chat_ID){
        LinkedList<MESSAGE> messages = new LinkedList<>();
        String sql = "SELECT * FROM MESSAGE WHERE Chat_ID = ?";
        try (Connection connection = DBconnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, Chat_ID);
            
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                CHAT chat = new chatDAO().select(resultSet.getInt("Chat_ID"));
                USER sender = new userDAO().select(resultSet.getInt("User_ID"));
                MESSAGE message = new MESSAGE(
                    resultSet.getInt("Message_ID"),
                    MSG_Type.valueOf(resultSet.getString("Message_Type")),
                    resultSet.getString("Message_content"),
                    resultSet.getObject("Sent_Time", LocalDateTime.class),
                    resultSet.getObject("Read_Time", LocalDateTime.class),
                    chat, 
                    sender 
                );
                messages.add(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return messages;
    }

    // Select all messages from the MESSAGE table
    // For Adminstrators only (to view all messages in the DB)
    public LinkedList<MESSAGE> selectAll() {
        LinkedList<MESSAGE> messages = new LinkedList<>();
        String sql = "SELECT * FROM MESSAGE";
        try (Connection connection = DBconnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                CHAT chat = new chatDAO().select(resultSet.getInt("Chat_ID"));
                USER sender = new userDAO().select(resultSet.getInt("User_ID"));
                MESSAGE message = new MESSAGE(
                    resultSet.getInt("Message_ID"),
                    MSG_Type.valueOf(resultSet.getString("Message_Type")),
                    resultSet.getString("Message_content"),
                    resultSet.getObject("Sent_Time", LocalDateTime.class),
                    resultSet.getObject("Read_Time", LocalDateTime.class),
                    chat, 
                    sender 
                );
                messages.add(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return messages;
    }


    
}
