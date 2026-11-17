package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import Connection.DBconnection;
import Tables.STICKER;

public class used_inDAO {
    
    // Both Users use it undirectly through the messageDAO when a message is sent with a sticker or deleted with a sticker.

    // Insert a record into the USED_IN table 
    // This method is used to associate a sticker with a message in a specific chat. it invokes by the messageDAO when a message as sent with a sticker.
    public boolean insert(int sticker_id, int meesage_id, int chat_id) {
        String sql = "INSERT INTO USED_IN (Sticker_ID, Message_ID, Chat_ID) VALUES (?, ?, ?)";
        try (Connection connection = DBconnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, sticker_id);
            statement.setInt(2, meesage_id);
            statement.setInt(3, chat_id);
            // Execute the insert statement and check if any rows were affected
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete a record from the USED_IN table based on the provided Sticker_ID, Message_ID, and Chat_ID
    // This method is used to remove the association between a sticker and a message in a specific
    // chat. it invokes by the messageDAO when a message with a sticker is deleted.
    public boolean delete(int sticker_id, int meesage_id, int chat_id) {
        // SQL query to delete a record from the USED_IN table based on the provided Sticker_ID, Message_ID, and Chat_ID
        String sql = "DELETE FROM USED_IN WHERE Sticker_ID = ? AND Message_ID = ? AND Chat_ID = ?";
        try (Connection connection = DBconnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, sticker_id);
            statement.setInt(2, meesage_id);
            statement.setInt(3, chat_id);
            // Execute the delete statement and check if any rows were affected
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Select a sticker from the USED_IN table based on the provided Sticker_ID and Message_ID
    // This method is used to retrieve the sticker associated with a specific message in a chat.
    public STICKER selectSticker(int sticker_id, int meesage_id) {
        String sql = "SELECT * FROM USED_IN WHERE Sticker_ID = ? AND Message_ID = ?";
        try (Connection connection = DBconnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, sticker_id);
            statement.setInt(2, meesage_id);
            ResultSet resultSet = statement.executeQuery();
            // If a record is found, retrieve the sticker using the stickerDAO
            if (resultSet.next()) {
                return new stickerDAO().select(sticker_id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
