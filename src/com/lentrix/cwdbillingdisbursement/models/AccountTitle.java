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
public class AccountTitle {
    private int id;
    private String accountNumber;
    private String accountTitle;

    public AccountTitle(int id, String accountNumber, String accountTitle) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.accountTitle = accountTitle;
    }
    
    public AccountTitle() {
        this.id = -1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountTitle() {
        return accountTitle;
    }

    public void setAccountTitle(String accountTitle) {
        this.accountTitle = accountTitle;
    }

    @Override
    public String toString() {
        return this.accountTitle;
    }
    
    public static AccountTitle load(int id) throws SQLException {
        ResultSet rs = Base.getConnection().createStatement().executeQuery(
                "SELECT * FROM accounts WHERE id=" + id);
        if(rs.first()) {
            return new AccountTitle(
                    rs.getInt("id"),
                    rs.getString("accountNo"),
                    rs.getString("accountTitle")
            );
        }else {
            return null;
        }
    }
    
    public static AccountTitle[] loadAll(String whereClause) throws SQLException {
        ResultSet rs = Base.getConnection().createStatement().executeQuery(
                "SELECT * FROM accounts " + whereClause + " ORDER BY accountTitle");
        rs.last();
        int rows = rs.getRow();
        AccountTitle[] accounts = new AccountTitle[rows];
        rs.beforeFirst();
        int i=0;
        while(rs.next()) {
            accounts[i] = new AccountTitle(
                    rs.getInt("id"),
                    rs.getString("accountNo"),
                    rs.getString("accountTitle"));
            i++;
        }
        return accounts;
    }
    
    public void save(User user) throws SQLException {
        PreparedStatement ps = null;
        if(this.id==-1) {
            ps = Base.getConnection().prepareStatement(
                    "INSERT INTO accounts (accountNo, accountTitle) "
                            + "VALUES (?,?)");
            ps.setString(1, this.accountNumber);
            ps.setString(2, this.accountTitle);
            ps.executeUpdate();
            Log.log(user.getId(), "Update the account title " + this.getAccountTitle());
        }else {
            ps = Base.getConnection().prepareStatement(
                    "UPDATE accounts SET "
                            + "accountNo=?, accountTitle=? "
                            + "WHERE id=?");
            ps.setString(1, this.accountNumber);
            ps.setString(2, this.accountTitle);
            ps.setInt(3, this.id);
            ps.executeUpdate();
            Log.log(user.getId(), "Created an account title " + this.getAccountTitle());
        }
    }
    
    public void delete(User user) throws SQLException {
        Base.getConnection().createStatement().executeUpdate(
                "DELETE FROM accounts WHERE id=" + this.id);
        Log.log(user.getId(), "Deleted the " + accountTitle + " account title.");
    }
    
    public boolean isValid() {
        return accountNumber!=null && !accountNumber.isEmpty() &&
               accountTitle!=null && !accountTitle.isEmpty();
    }
}
