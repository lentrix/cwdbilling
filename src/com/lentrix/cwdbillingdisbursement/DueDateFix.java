/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lentrix.cwdbillingdisbursement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

/**
 *
 * @author lentrix
 */
public class DueDateFix {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection cxn = DriverManager.getConnection("jdbc:mysql:///cwd", "cwd_user", "system");
            
            ResultSet rs = cxn.createStatement().executeQuery(
                    "SELECT id, dateBilled FROM billing "
                            + "WHERE dateDue = '0000-00-00'");
            rs.beforeFirst();
            PreparedStatement ps = cxn.prepareStatement(
                    "UPDATE billing SET dateDue=? WHERE id=?");
            while(rs.next()) {
                LocalDate dateBilled = rs.getDate("dateBilled").toLocalDate();
                String dueStr = dateBilled.getYear() + "-" + dateBilled.getMonthValue() + "-28";
                java.sql.Date dueDate = java.sql.Date.valueOf(dueStr);
                
                ps.setDate(1, dueDate);
                ps.setInt(2, rs.getInt("id"));
                ps.addBatch();
            }
            ps.executeBatch();
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
