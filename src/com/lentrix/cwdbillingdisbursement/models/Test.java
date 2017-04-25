/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lentrix.cwdbillingdisbursement.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author lentrix
 */
public class Test {
    int id;
    String name;

    public Test(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Test(String name) {
        this.name = name;
    }
    
    public void show(){
        System.out.println("ID: " + this.id);
        System.out.println("Name: " + this.name);
    }
    
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection cxn = DriverManager.getConnection("jdbc:mysql:///cwd","root","");
            ResultSet rs = cxn.createStatement().executeQuery("SELECT * FROM billing");
            rs.beforeFirst();
            while(rs.next()) {
                java.sql.Date dateBilled = rs.getDate("dateBilled");
                int monthValue = dateBilled.toLocalDate().getMonth().getValue();
                int yearValue = dateBilled.toLocalDate().getYear();
                int dayValue = dateBilled.toLocalDate().getDayOfMonth();
                
                int dueMonthValue=0; int dueYearValue=0;
                if(monthValue==12) {
                    dueMonthValue = 1;
                    dueYearValue = yearValue + 1;
                }else {
                    dueMonthValue = monthValue + 1;
                }
                                
                cxn.createStatement().executeUpdate(
                        "UPDATE billing SET dateDue = '" + dueYearValue + "-" 
                                + dueMonthValue + "-" + dayValue + "' WHERE id=" + rs.getInt("id"));
                System.out.println("Updated " + rs.getInt("id"));
            }
        }catch(SQLException ex) {
            ex.printStackTrace();
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
