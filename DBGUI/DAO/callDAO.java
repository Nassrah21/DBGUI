package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.LinkedList;

import Connection.DBconnection;
import Enum.CallStatus;
import Enum.Call_Type;
import Tables.CALL;
import Tables.CHAT;

public class callDAO {
    
    // Insert a new record into the CALL table
    // For both (When a call is made)
    public boolean insert(CALL call){
        String sql = "INSERT INTO CALL (Call_ID, Call_Type, Start_Time, End_Time, Status, Chat_ID) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DBconnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, call.getCall_ID());
            statement.setObject(2, call.getCall_Type());
            statement.setObject(3, call.getStart_Time());
            statement.setObject(4, call.getEnd_Time());
            statement.setObject(5, call.getStatus());
            statement.setInt(6, call.getChat().getChat_id());
            
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update an existing call in the CALL table
    // For Both
    public boolean update(CALL call){
        String sql = "UPDATE CALL SET Call_Type = ?, End_Time = ?, Status = ? WHERE Call_ID = ?";
        try (Connection connection = DBconnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setObject(1, call.getCall_Type());
            statement.setObject(2, call.getEnd_Time());
            statement.setObject(3, call.getStatus());
            statement.setInt(4, call.getCall_ID());
            
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete a call from the CALL table based on the provided Call_ID
    // For Both
    public boolean delete(int id){
        String sql = "DELETE FROM CALL WHERE Call_ID = ?";
        try (Connection connection = DBconnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Select a call from the CALL table based on the provided Call_ID
    // This method is used to retrieve a specific call from the database.
    // For Regular User only (Sees only if it participated that call)
    public LinkedList<CALL> selectByUserId(int userId) {
    LinkedList<CALL> calls = new LinkedList<>();
    String sql = "SELECT C.* FROM CALL AS C JOIN MAKES AS M ON C.Call_ID = M.Call_ID WHERE M.User_ID = ?";
    try (Connection connection = DBconnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {

        statement.setInt(1, userId);
        
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                CHAT chat = Database.chats.select(resultSet.getInt("Chat_ID"));
                calls.add(new CALL(
                    resultSet.getInt("Call_ID"),
                    Call_Type.valueOf(resultSet.getString("Call_Type")),
                    resultSet.getObject("Start_Time", LocalDateTime.class),
                    resultSet.getObject("End_Time", LocalDateTime.class),
                    CallStatus.valueOf(resultSet.getString("Status")),
                    chat
                ));
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return calls;
}

    public LinkedList<CALL> selectAll(){
        String sql = "SELECT * FROM CALL";
        LinkedList<CALL> calls = new LinkedList<>();
        try (Connection connection = DBconnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            chatDAO chatDAO = new chatDAO(); 
            while (resultSet.next()) {
                CHAT chat = chatDAO.select(resultSet.getInt("Chat_ID")); // Fetch the CHAT object using the chatDAO
                CALL call = new CALL(
                    resultSet.getInt("Call_ID"),
                    (Call_Type) resultSet.getObject("Call_Type"),
                    resultSet.getObject("Start_Time", LocalDateTime.class),
                    resultSet.getObject("End_Time", LocalDateTime.class),
                    (CallStatus) resultSet.getObject("Status"),
                    chat
                );
                calls.add(call);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return calls;
    }
    
}
