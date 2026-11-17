package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.LinkedList;

import Connection.DBconnection;
import Enum.User_type;
import Tables.USER;

public class userDAO{
    
    // For Regular Users only
    public boolean insert(USER user){
        // SQL query to insert a new user (sign in) into the USER table
        String sql = "INSERT INTO USER (User_ID, Username, Display_Name, Phone_Number, Birth_Date, Password, User_type, Bio, Registration_Date, Profile_Photo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        // Use try-with-resources to ensure the connection and statement are closed automatically
        try(Connection connection = DBconnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, user.getUser_id());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getDisplay_Name());
            statement.setString(4, user.getPhone_Number());
            statement.setObject(5, user.getBirth_Date());
            statement.setString(6, user.getPassword());
            statement.setString(7, user.getUser_Type().toString());
            statement.setString(8, user.getBio());
            statement.setObject(9, user.getRegistration_Date());
            statement.setString(10, user.getProfile_Photo());

            // Execute the insert statement and check if any rows were affected
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } 
    }

    // For both (Regular user can only update their own data, Admin can update any user data)
    public boolean update(USER user){
        // SQL query to update an existing user data in the USER table
        String sql = "UPDATE USER SET Username = ?, Display_Name = ?, Phone_Number = ?, Birth_Date = ?, Password = ?, User_type = ?, Bio = ?, Registration_Date = ?, Profile_Photo = ? WHERE User_ID = ?";
        try(Connection connection = DBconnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getDisplay_Name());
            statement.setString(3, user.getPhone_Number());
            statement.setObject(4, user.getBirth_Date());
            statement.setString(5, user.getPassword());
            statement.setString(6, user.getUser_Type().toString());
            statement.setString(7, user.getBio());
            statement.setObject(8, user.getRegistration_Date());
            statement.setString(9, user.getProfile_Photo());
            statement.setInt(10, user.getUser_id());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // For both (Regular user can only delete their own account, Admin can delete any user account)
    public boolean delete(int id){
        // SQL query to delete a user from the USER table based on the provided User_ID
        String sql = "DELETE FROM USER WHERE User_ID = ?";
        try(Connection connection = DBconnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            // Set the User_ID parameter in the prepared statement
            statement.setInt(1, id);
            // Execute the delete statement and check if any rows were affected
            int rowsAffected = statement.executeUpdate();
            // Return true if at least one row was affected (user deleted), otherwise return false
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // For both (Used for log in/ retrieving user data)
    public USER select(int id){
        // SQL query to select a user (log in) from the USER table based on the provided User_ID
        String sql = "SELECT * FROM USER WHERE User_ID = ?";
        try(Connection connection = DBconnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            // Set the User_ID parameter in the prepared statement
            statement.setInt(1, id);
            // Execute the query and retrieve the result set
            var resultSet = statement.executeQuery();
            // If a user is found, create a USER object with the retrieved data
            if(resultSet.next()){
                USER user = new USER();
                user.setUser_id(resultSet.getInt("User_id"));
                user.setUsername(resultSet.getString("Username"));
                user.setDisplay_Name(resultSet.getString("Display_Name"));
                user.setPhone_Number(resultSet.getString("Phone_Number"));
                user.setBirth_Date(resultSet.getObject("Birth_Date", LocalDate.class));
                user.setPassword(resultSet.getString("Password"));
                user.setUser_Type(User_type.valueOf(resultSet.getString("User_Type")));
                user.setBio(resultSet.getString("Bio"));
                user.setRegistration_Date(resultSet.getObject("Registration_Date", LocalDate.class));
                user.setProfile_Photo(resultSet.getString("Profile_Photo"));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public USER valid(String username, String password){
        // SQL query to select a user (log in) from the USER table based on the provided User_ID
        String sql = "SELECT * FROM USER WHERE Username = ? AND Password = ?";
        try(Connection connection = DBconnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            // Set the User_ID parameter in the prepared statement
            statement.setString(1, username);
            statement.setString(2, password);
            // Execute the query and retrieve the result set
            var resultSet = statement.executeQuery();
            // If a user is found, create a USER object with the retrieved data
            if(resultSet.next()){
                USER user = new USER();
                user.setUser_id(resultSet.getInt("User_id"));
                user.setUsername(resultSet.getString("Username"));
                user.setDisplay_Name(resultSet.getString("Display_Name"));
                user.setPhone_Number(resultSet.getString("Phone_Number"));
                user.setBirth_Date(resultSet.getObject("Birth_Date", LocalDate.class));
                user.setPassword(resultSet.getString("Password"));
                user.setUser_Type(User_type.valueOf(resultSet.getString("User_Type")));
                user.setBio(resultSet.getString("Bio"));
                user.setRegistration_Date(resultSet.getObject("Registration_Date", LocalDate.class));
                user.setProfile_Photo(resultSet.getString("Profile_Photo"));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // For Administrator only 
    public LinkedList<USER> selectAll(){
        // SQL query to select all users from the USER table
        String sql = "SELECT * FROM USER";
        // Create a LinkedList to store the retrieved users
        LinkedList<USER> users = new LinkedList<>();
        try(Connection connection = DBconnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            // Execute the query and retrieve the result set
            var resultSet = statement.executeQuery();
            // Iterate through the result set and create USER objects for each user found
            while(resultSet.next()){
                USER user = new USER();
                user.setUser_id(resultSet.getInt("User_id"));
                user.setUsername(resultSet.getString("Username"));
                user.setDisplay_Name(resultSet.getString("Display_Name"));
                user.setPhone_Number(resultSet.getString("Phone_Number"));
                user.setBirth_Date(resultSet.getObject("Birth_Date", LocalDate.class));
                user.setPassword(resultSet.getString("Password"));
                user.setUser_Type(User_type.valueOf(resultSet.getString("User_Type")));
                user.setBio(resultSet.getString("Bio"));
                user.setRegistration_Date(resultSet.getObject("Registration_Date", LocalDate.class));
                user.setProfile_Photo(resultSet.getString("Profile_Photo"));
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    // For Administrator only (Used to retrieve users in the DB by their type for analysis)
    public LinkedList<USER> selectByType(User_type user_type){
        // SQL query to select users from the USER table based on the provided User_Type
        String sql = "SELECT * FROM USER WHERE User_Type = ?";
        // Create a LinkedList to store the retrieved users
        LinkedList<USER> users = new LinkedList<>();
        try(Connection connection = DBconnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user_type.toString());
            var resultSet = statement.executeQuery();
            while(resultSet.next()){
                USER user = new USER();
                user.setUser_id(resultSet.getInt("User_id"));
                user.setUsername(resultSet.getString("Username"));
                user.setDisplay_Name(resultSet.getString("Display_Name"));
                user.setPhone_Number(resultSet.getString("Phone_Number"));
                user.setBirth_Date(resultSet.getObject("Birth_Date", LocalDate.class));
                user.setPassword(resultSet.getString("Password"));
                user.setUser_Type(User_type.valueOf(resultSet.getString("User_Type")));
                user.setBio(resultSet.getString("Bio"));
                user.setRegistration_Date(resultSet.getObject("Registration_Date", LocalDate.class));
                user.setProfile_Photo(resultSet.getString("Profile_Photo"));
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

}