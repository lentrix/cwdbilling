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

/**
 *
 * @author lentrix
 */
public class BillItem {
    private int id;
    private Billing billing;
    private String type;
    private float amount;

    public BillItem(int id, Billing billing, String type, float amount) {
        this.id = id;
        this.billing = billing;
        this.type = type;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Billing getBilling() {
        return billing;
    }

    public void setBilling(Billing billing) {
        this.billing = billing;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return type;
    }
    
    public static BillItem load(int id) throws SQLException {
        PreparedStatement ps = Base.getConnection().prepareStatement(
                "SELECT * FROM billItem WHERE id=?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if(rs.first()) {
            return new BillItem(
                    rs.getInt("id"),
                    Billing.load(rs.getInt("billingId")),
                    rs.getString("type"),
                    rs.getFloat("amount")
            );
        }else {
            return null;
        }
    }
    
    public static BillItem[] load(Billing billing) throws SQLException{
        PreparedStatement ps = Base.getConnection().prepareStatement(
                "SELECT * FROM billItem WHERE billingId=?");
        ps.setInt(1, billing.getId());
        ResultSet rs = ps.executeQuery();
        rs.last();
        int rows = rs.getRow();
        BillItem[] billItems = new BillItem[rows];
        rs.beforeFirst();
        int i=0;
        while(rs.next()) {
            billItems[i] = new BillItem(
                    rs.getInt("id"),
                    Billing.load(rs.getInt("billingId")),
                    rs.getString("type"),
                    rs.getFloat("amount")
                );
            i++;
        }
        return billItems;
    }
    
    public boolean exists(String type) throws SQLException {
        PreparedStatement ps = Base.getConnection().prepareStatement(
                "SELECT * FROM billItem WHERE type=? AND billingId=?");
        ps.setString(1, type);
        ps.setInt(2, billing.getId());
        ResultSet rs = ps.executeQuery();
        return rs.first();
    }
    
    public void save(User user) throws SQLException {
        PreparedStatement ps;
        if(exists(type)) {
            ps = Base.getConnection().prepareStatement(
                    "UPDATE billItem SET amount=? WHERE billingId=? AND type=?");
            ps.setInt(1, billing.getId());
            ps.setString(2, type);
            ps.executeUpdate();
            Log.log(user.getId(), "Updated the " + type + " of " 
                    + billing.getCustomer().getName() 
                    + " for " + Billing.months[billing.getMonth()] + " " + billing.getYear());
        }else {
            ps = Base.getConnection().prepareStatement(
                    "INSERT INTO billItem (billingId, type, amount) "
                            + "VALUES (?,?,?)");
            ps.setInt(1, billing.getId());
            ps.setString(2, type);
            ps.setFloat(3, amount);
            ps.executeUpdate();
            Log.log(user.getId(), "Added " + type + " of " 
                    + billing.getCustomer().getName() + " for " 
                    + Billing.months[billing.getMonth()] + " " 
                    + billing.getYear() + ".");
        }
    }
    
    public static void updateAmount(int billingId, String type, float amount) throws SQLException{
        PreparedStatement ps = Base.getConnection().prepareStatement(
                "UPDATE billItem SET amount=? WHERE billingId=? AND type=?");
        ps.setFloat(1, amount);
        ps.setInt(2, billingId);
        ps.setString(3, type);
        ps.executeUpdate();
    }
    
    public void remove() throws SQLException {
        PreparedStatement ps = Base.getConnection().prepareStatement("DELETE FROM billItem WHERE id=?");
        ps.setInt(1, id);
        ps.executeUpdate();
    }
}
