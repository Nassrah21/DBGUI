package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import Connection.DBconnection;
import Tables.STICKER;

public class ownsDAO {
    
    // Insert a new record into the OWNS table
    // For both (When sticker is bought)
    public boolean insert(int user_id, int sticker_id) {
        String sql = "INSERT INTO OWNS (User_ID, Sticker_ID) VALUES (?, ?)";
        try (Connection connection = DBconnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, user_id);
            statement.setInt(2, sticker_id);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete a record from the OWNS table based on the provided User_ID and Sticker_ID
    // For both (When sticker is removed from the user)
    public boolean delete(int user_id, int sticker_id) {
        String sql = "DELETE FROM OWNS WHERE User_ID = ? AND Sticker_ID = ?";
        try (Connection connection = DBconnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, user_id);
            statement.setInt(2, sticker_id);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Select all stickers owned by a specific user from the OWNS table
    // For Regular Users only (to view the stickers he owns)
    public LinkedList<STICKER> selectStickers(int user_id) {
        String sql = "SELECT Sticker_ID FROM OWNS WHERE User_ID = ?";
        LinkedList<STICKER> stickerList = new LinkedList<>();
        try (Connection connection = DBconnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, user_id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                STICKER sticker = new stickerDAO().select(resultSet.getInt("Sticker_ID"));
                stickerList.add(sticker);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stickerList;
    }
}
