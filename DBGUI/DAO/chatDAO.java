package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.LinkedList;

import Connection.DBconnection;
import Enum.Chat_Type;
import Enum.User_type;
import Tables.CHAT;
import Tables.USER;

public class chatDAO implements DAO<CHAT> {
    
    // Insert a new record into the CHAT table
    // For both (When a chat is created)
    public boolean insert(CHAT chat) {
        String sql = "INSERT INTO CHAT (Chat_ID, Chat_Type, Created_Date) VALUES (?, ?, ?)";
        try (Connection connection = DBconnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, chat.getChat_id());
            statement.setObject(2, chat.getChat_type());
            statement.setObject(3, chat.getCreated_Date());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    // Update an existing chat with new chat type in the CHAT table
    // For Administrator only
    public boolean update(CHAT chat) {
        String sql = "UPDATE CHAT SET Chat_Type = ? WHERE Chat_ID = ?";
        try (Connection connection = DBconnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, chat.getChat_type());
            statement.setInt(2, chat.getChat_id());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete a chat from the CHAT table based on the provided Chat_ID
    // For Administrator only
    public boolean delete(int id) {
        String sql = "DELETE FROM CHAT WHERE Chat_ID = ?";
        try (Connection connection = DBconnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Select a chat from the CHAT table based on the provided Chat_ID
    // This method is used to retrieve a specific chat from the database.
    // For Regular User only (Sees only if it was in that chat)
    public CHAT select(int user_id) {
        String sql = "SELECT C.* FROM CHAT AS C JOIN JOINS AS J ON C.Chat_ID = J.Chat_ID WHERE J.User_ID = ?";
        try (Connection connection = DBconnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, user_id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                CHAT chat = new CHAT();
                chat.setChat_id(resultSet.getInt("Chat_ID"));
                chat.setChat_type(Chat_Type.valueOf(resultSet.getString("Chat_Type")));
                chat.setCreated_Date(resultSet.getObject("Created_Date", LocalDate.class));
                return chat;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public CHAT selectByChatID(int Chat_ID){
        String sql = "SELECT * FROM CHAT WHERE Chat_ID = ?";
        try (Connection connection = DBconnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, Chat_ID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                CHAT chat = new CHAT();
                chat.setChat_id(resultSet.getInt("Chat_ID"));
                chat.setChat_type(Chat_Type.valueOf(resultSet.getString("Chat_Type")));
                chat.setCreated_Date(resultSet.getObject("Created_Date", LocalDate.class));
                return chat;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Select all records from the CHAT table
    // For Adminstrators only (to view all chats in the DB)
    public LinkedList<CHAT> selectAll() {
        LinkedList<CHAT> chats = new LinkedList<>();
        String sql = "SELECT * FROM CHAT";
        try (Connection connection = DBconnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                CHAT chat = new CHAT();
                chat.setChat_id(resultSet.getInt("Chat_ID"));
                chat.setChat_type(Chat_Type.valueOf(resultSet.getString("Chat_Type")));
                chat.setCreated_Date(resultSet.getObject("Created_Date", LocalDate.class));
                chats.add(chat);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chats;
    }

    // Select all that chats by user id
    // For both (For chat screen)
    // needs revise nassrah
    public LinkedList<CHAT> selectById(int user_id) {
        LinkedList<CHAT> chats = new LinkedList<>();
        String sql = "SELECT Chat_id FROM JOINS WHERE User_ID = ?";
        try (Connection connection = DBconnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, user_id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                CHAT chat = select(resultSet.getInt("Chat_ID"));
                if (chat != null) {
                    chats.add(chat);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chats;

    }

    // Retrieve the users id with specific type and chat id
    // For both 
    public LinkedList<USER> retrieveUsersInChatByType(User_type userType, int chat_id) {
        LinkedList<USER> users = new LinkedList<>();
        String sql = "SELECT User_ID FROM JOINS WHERE Chat_ID = ?, User_Type = ?";
        try (Connection connection = DBconnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, chat_id);
            statement.setString(2, userType.toString());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                USER user = new userDAO().select(resultSet.getInt("User_ID"));
                if (user != null) {
                    users.add(user);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    // Similar to delete from JOINS when user gets kicked
    // For Administrator only
    public boolean kickUserFromChat(int user_id, int chat_id) {
        String sql = "DELETE FROM JOINS WHERE User_ID = ? AND Chat_ID = ?";
        try (Connection connection = DBconnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, user_id);
            statement.setInt(2, chat_id);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
