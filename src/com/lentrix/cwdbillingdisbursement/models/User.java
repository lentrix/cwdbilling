/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lentrix.cwdbillingdisbursement.models;

import com.lentrix.cwdbillingdisbursement.Base;
import com.lentrix.cwdbillingdisbursement.MyCrypto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

/**
 *
 * @author lentrix
 */
public class User {
    private int id;
    private String userName;
    private String lastName;
    private String firstName;
    private int accessLevel;
    
    public static final int ROLE_ADMIN = 100;
    public static final int ROLE_STAFF = 50;

    public User(int id, String userName, String lastName, String firstName, int accessLevel) {
        this.id = id;
        this.userName = userName;
        this.lastName = lastName;
        this.firstName = firstName;
        this.accessLevel = accessLevel;
    }
    
    public User() {
        this.id = -1;
        this.accessLevel = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }
    
    @Override
    public String toString() {
        return "[" + userName + "] "  + lastName + ", " + firstName;
    }
    
    public static User load(int id) {
        try{
            Connection cxn = Base.getConnection();
            ResultSet rs = cxn.createStatement().executeQuery("SELECT * FROM user WHERE id = " + id);
            if(rs.first()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("lastName"),
                        rs.getString("firstName"),
                        rs.getInt("accessLevel")
                );
            }else {
                return null;
            }
        }catch(Exception ex) {
            Base.error(ex.getMessage(), null);
            return null;
        }
    }
    
    public static User load(String username, String password){
        try{
            Connection cxn = Base.getConnection();
            ResultSet rs = cxn.createStatement().executeQuery("SELECT * FROM user "
                    + "WHERE username = '" + username + "' AND password=MD5('" + password + "')");
            if(rs.first()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("lastName"),
                        rs.getString("firstName"),
                        rs.getInt("accessLevel")
                );
            }else {
                return null;
            }
        }catch(Exception ex) {
            Base.error(ex.getMessage(), null);
            return null;
        }
    }
    
    public static LinkedList loadAll(Connection cxn) throws SQLException {
        ResultSet rs = cxn.createStatement().executeQuery("SELECT * FROM user ORDER BY lastName, firstName");
        LinkedList users = new LinkedList();
        rs.beforeFirst();
        while(rs.next()) {
            users.add(new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("lastName"),
                    rs.getString("firstName"),
                    rs.getInt("accessLevel")
            ));
        }
        return users;
    }
    
    public void save(User user) throws SQLException{
        PreparedStatement ps = null;
        String password = MyCrypto.getRandomString(8);
        if(this.id==-1){
            ps = Base.getConnection().prepareStatement(
                    "INSERT INTO user (username, password, authKey, accessToken, lastName, firstName, accessLevel) "
                            + "VALUES (?,md5(?),?,?,?,?,?)");
            ps.setString(1, userName);
            ps.setString(2, password);
            ps.setString(3, MyCrypto.getRandomString(8));
            ps.setString(4, MyCrypto.getRandomString(8));
            ps.setString(5, lastName);
            ps.setString(6, firstName);
            ps.setInt(7, accessLevel);
            ps.executeUpdate();
            Base.info("The user has been created!\nThe generated password for the new user is " + password, null);
            Log.log(user.getId(), "Created a new user with a username of " + this.userName);
        }else {
            ps = Base.getConnection().prepareStatement(
                    "UPDATE user SET username=?, lastName=?, firstName=?, "
                            + "accessLevel=? WHERE id=?");
            ps.setString(1, userName);
            ps.setString(2, lastName);
            ps.setString(3, firstName);
            ps.setInt(4, accessLevel);
            ps.setInt(5, id);
            ps.executeUpdate();
            Base.info("The user details have been updated.", null);
            Log.log(user.getId(), "Update the user details of " + this.getUserName());
        }
    }
    
    public boolean isValid() {
        return userName!=null && !userName.isEmpty() &&
               lastName!=null && !lastName.isEmpty() &&
               firstName !=null && !firstName.isEmpty() &&
                accessLevel!=0;
    }
    
    public void delete() throws SQLException {
        PreparedStatement ps = Base.getConnection().prepareCall("DELETE FROM user WHERE id=?");
        ps.setInt(1, id);
        ps.executeUpdate();
    }
}
