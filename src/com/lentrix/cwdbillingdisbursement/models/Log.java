/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lentrix.cwdbillingdisbursement.models;

import com.lentrix.cwdbillingdisbursement.Base;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author lentrix
 */
public class Log {
    private int id;
    private User user;
    private String activity;
    private LocalDateTime date;

    public Log(int id, User user, String activity, LocalDateTime date) {
        this.id = id;
        this.user = user;
        this.activity = activity;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    
    public static Log load(int id) throws SQLException {
        ResultSet rs = Base.getConnection().createStatement().executeQuery("SELECT * FROM log WHERE id=" + id);
        rs.first();
        return new Log(
                rs.getInt("id"),
                User.load(rs.getInt("userId")),
                rs.getString("activity"),
                LocalDateTime.of(rs.getDate("date").toLocalDate(), rs.getTime("date").toLocalTime())
        );
    }
    
    public static Log[] loadFromUser(int userId) throws SQLException {
        ResultSet rs = Base.getConnection().createStatement().executeQuery(
                "SELECT * FROM log WHERE userId=" + userId + " ORDER BY date DESC");
        rs.last();
        int rows = rs.getRow();
        Log[] obj = new Log[rows];
        rs.beforeFirst();
        int i=0;
        while(rs.next()) {
            obj[i] = new Log(
                rs.getInt("id"),
                User.load(rs.getInt("userId")),
                rs.getString("activity"),
                LocalDateTime.of(rs.getDate("date").toLocalDate(), rs.getTime("date").toLocalTime())
            );
            i++;
        }
        return obj;
    }
    
    public static Log[] loadAll() throws SQLException {
        ResultSet rs = Base.getConnection().createStatement().executeQuery("SELECT * FROM log ORDER BY date DESC");
        rs.last();
        int rows = rs.getRow();
        Log[] obj = new Log[rows];
        rs.beforeFirst();
        int i=0;
        while(rs.next()) {
            obj[i] = new Log(
                rs.getInt("id"),
                User.load(rs.getInt("userId")),
                rs.getString("activity"),
                LocalDateTime.of(rs.getDate("date").toLocalDate(), rs.getTime("date").toLocalTime())
            );
            i++;
        }
        return obj;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
    
    public static void log(int userId, String activity) throws SQLException {
        PreparedStatement ps = Base.getConnection().prepareStatement(
                "INSERT INTO log (userId, activity, date) "
                        + "VALUES (?,?,?)");
        ps.setInt(1, userId);
        ps.setString(2, activity);
        ps.setString(3, LocalDateTime.now().toString());
        ps.executeUpdate();
    }
}
