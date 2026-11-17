package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;

import Connection.DBconnection;
import Enum.Sticker_Type;
import Tables.STICKER;

public class stickerDAO implements DAO<STICKER>{

    // Insert a new sticker into the STICKER table
    // For Administrator only
    public boolean insert(STICKER sticker) {
        // SQL query to insert a new sticker into the STICKER table
        String sql = "INSERT INTO STICKER (Sticker_ID, Sticker_Name, Sticker_URL, Sticker_Type, Price, Package) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBconnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, sticker.getSticker_id());
            statement.setString(2, sticker.getSticker_name());
            statement.setString(3, sticker.getSticker_url());
            statement.setObject(4, sticker.getSticker_type());
            statement.setDouble(5, sticker.getPrice());
            statement.setString(6, sticker.getPackage());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update an existing sticker in the STICKER table
    // For Administrator only
    public boolean update(STICKER sticker) {
        String sql = "UPDATE STICKER SET Sticker_Name = ?, Sticker_URL = ?, Sticker_Type = ?, Price = ?, Package = ? WHERE Sticker_ID = ?";
        try (Connection connection = DBconnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, sticker.getSticker_name());
            statement.setString(2, sticker.getSticker_url());
            statement.setObject(3, sticker.getSticker_type());
            statement.setDouble(4, sticker.getPrice());
            statement.setString(5, sticker.getPackage());
            statement.setInt(6, sticker.getSticker_id());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete a sticker from the STICKER table based on the provided Sticker_ID
    // For Administrator only
    public boolean delete(int id) {
        String sql = "DELETE FROM STICKER WHERE Sticker_ID = ?";
        try (Connection connection = DBconnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Select a sticker from the STICKER table based on the provided Sticker_ID
    // This method is used to retrieve a specific sticker from the database.
    // For both (Regular user can only view the sticker he owns (in table OWNS), Admin can view any sticker)
    public STICKER select(int id) {
        String sql = "SELECT * FROM STICKER WHERE Sticker_ID = ?";
        try (Connection connection = DBconnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new STICKER(
                        resultSet.getInt("Sticker_ID"),
                        resultSet.getString("Sticker_Name"),
                        resultSet.getString("Sticker_URL"),
                        Sticker_Type.valueOf(resultSet.getString("Sticker_Type")),
                        resultSet.getDouble("Price"),
                        resultSet.getString("Package")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Select all stickers from the STICKER table
    // This method is used to retrieve all stickers from the database.
    // For both (in the marketplace)
    public LinkedList<STICKER> selectAll() {
        String sql = "SELECT * FROM STICKER";
        LinkedList<STICKER> stickers = new LinkedList<>();
        try (Connection connection = DBconnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                STICKER sticker = new STICKER(
                        resultSet.getInt("Sticker_ID"),
                        resultSet.getString("Sticker_Name"),
                        resultSet.getString("Sticker_URL"),
                        Sticker_Type.valueOf(resultSet.getString("Sticker_Type")),
                        resultSet.getDouble("Price"),
                        resultSet.getString("Package")
                );
                stickers.add(sticker);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stickers;
    }
}
