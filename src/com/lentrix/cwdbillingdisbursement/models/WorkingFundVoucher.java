/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lentrix.cwdbillingdisbursement.models;

import com.lentrix.cwdbillingdisbursement.Base;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author lentrix
 */
public class WorkingFundVoucher {
    private int id;
    private Date date;
    private String payee;
    private String address;

    public WorkingFundVoucher(int id, Date date, String payee, String address) {
        this.id = id;
        this.date = date;
        this.payee = payee;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public static WorkingFundVoucher load(int id) throws SQLException {
        ResultSet rs = Base.getConnection().createStatement().executeQuery(
                "SELECT * FROM workingFundVoucher WHERE id=" + id);
        if(rs.first()) {
            return new WorkingFundVoucher(
                    rs.getInt("id"),
                    rs.getDate("date"),
                    rs.getString("payee"),
                    rs.getString("addr")
            );
        }else {
            return null;
        }
    }
    
    public static WorkingFundVoucher[] load(String whereClause) throws SQLException {
        ResultSet rs = Base.getConnection().createStatement().executeQuery(
                "SELECT * FROM workingFundVoucher " + whereClause + " ORDER BY date DESC");
        rs.last();
        int n = rs.getRow();
        WorkingFundVoucher[] vouchers = new WorkingFundVoucher[n];
        
        rs.beforeFirst();
        int i = 0;
        while(rs.next()) {
            vouchers[i++] = new WorkingFundVoucher(
                    rs.getInt("id"),
                    rs.getDate("date"),
                    rs.getString("payee"),
                    rs.getString("addr")
            );
        }
        return vouchers;
    }
    
    public void save() throws SQLException {
        PreparedStatement ps = Base.getConnection().prepareStatement(
                "INSERT INTO workingFundVoucher (date, payee, addr) VALUES (?,?,?)",
                PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setDate(1, date);
        ps.setString(2, payee);
        ps.setString(3, address);
        ps.execute();
        
        ResultSet rs = ps.getGeneratedKeys();
        rs.first();
        this.id = rs.getInt(1);
    }
}
