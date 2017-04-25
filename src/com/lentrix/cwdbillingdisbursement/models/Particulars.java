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
 * wvfParticulars table.
 */
public class Particulars {
    private int id;
    private WorkingFundVoucher voucher;
    private AccountTitle account;
    private float amount;

    public Particulars(int id, WorkingFundVoucher voucher, AccountTitle account, float amount) {
        this.id = id;
        this.voucher = voucher;
        this.account = account;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public WorkingFundVoucher getVoucher() {
        return voucher;
    }

    public void setVoucher(WorkingFundVoucher voucher) {
        this.voucher = voucher;
    }

    public AccountTitle getAccount() {
        return account;
    }

    public void setAccount(AccountTitle account) {
        this.account = account;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
    
    public Particulars load(int id) throws SQLException{
        ResultSet rs = Base.getConnection().createStatement().executeQuery(
                "SELECT * FROM wfvParticulars WHERE id=" + id);
        if(rs.first()) {
            return new Particulars(
                    rs.getInt("id"),
                    WorkingFundVoucher.load(rs.getInt("workingFundId")),
                    AccountTitle.load(rs.getInt("accountId")),
                    rs.getFloat("amount")
            );
        }else {
            return null;
        }
    }
    
    public Particulars[] load(WorkingFundVoucher voucher) throws SQLException {
        ResultSet rs = Base.getConnection().createStatement().executeQuery(
                "SELECT * FROM wfvParticulars WHERE workingFundId=" + voucher.getId());
        rs.last();
        int n = rs.getRow();
        int i = 0;
        Particulars particulars[] = new Particulars[n];
        rs.beforeFirst();
        while(rs.next()) {
            particulars[i++] = new Particulars(
                    rs.getInt("id"),
                    WorkingFundVoucher.load(rs.getInt("workingFundId")),
                    AccountTitle.load(rs.getInt("accountId")),
                    rs.getFloat("amount")
            );
        }
        return particulars;
    }
    
    public boolean save() throws SQLException {
        PreparedStatement ps = Base.getConnection().prepareStatement(
                "INSERT INTO wfvParticulars (workingFundId, accountId, amount) VALUES (?,?,?)");
        System.out.println(voucher.getId());
        ps.setInt(1, voucher.getId());
        ps.setInt(2, account.getId());
        ps.setFloat(3, amount);
        return ps.execute();
    }
}
