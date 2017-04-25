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
public class Receipt {
    private int id;
    private java.sql.Date date;
    private Customer customer;
    private float current;
    private float arrearsCY;
    private float arrearsPY;
    private float penalty;
    private float ni;
    private float recon;
    private float reloc;
    private float pca;
    private float meterSale;
    private float others;
    private float lossDisc;
    private float srDisc;
    private String pmtType;
    private float runningBalance;

    public Receipt(int id, Date date, Customer customer, float current, float arrearsCY, float arrearsPY, float penalty, float ni, float recon, float reloc, float pca, float meterSale, float others, float lossDisc, float srDisc, String pmtType, float runningBalance) {
        this.id = id;
        this.date = date;
        this.customer = customer;
        this.current = current;
        this.arrearsCY = arrearsCY;
        this.arrearsPY = arrearsPY;
        this.penalty = penalty;
        this.ni = ni;
        this.recon = recon;
        this.reloc = reloc;
        this.pca = pca;
        this.meterSale = meterSale;
        this.others = others;
        this.lossDisc = lossDisc;
        this.srDisc = srDisc;
        this.pmtType = pmtType;
        this.runningBalance = runningBalance;
    }
   
    public static Receipt load(int id) throws SQLException {
        PreparedStatement ps = Base.getConnection().prepareStatement(
                "SELECT * FROM receipt WHERE id=?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if(rs.first()) {
            Customer customer = Customer.load(Base.getConnection(), rs.getInt("customerId"));
            return new Receipt(
                    rs.getInt("id"),
                    rs.getDate("date"),
                    customer,
                    rs.getFloat("current"),
                    rs.getFloat("arrearsCY"),
                    rs.getFloat("arrearsPY"),
                    rs.getFloat("penalty"),
                    rs.getFloat("ni"),
                    rs.getFloat("recon"),
                    rs.getFloat("reloc"),
                    rs.getFloat("pca"),
                    rs.getFloat("meterSale"),
                    rs.getFloat("others"),
                    rs.getFloat("lossDisc"),
                    rs.getFloat("srDisc"),
                    rs.getString("pmtType"),
                    rs.getFloat("runningBalance")
            );
        }else {
            return null;
        }
    }
    
    public void save() throws SQLException{
        if(this.id==-1) {
            PreparedStatement ps = Base.getConnection().prepareStatement(
                    "INSERT INTO receipt (date, customerId, current, arrearsCY, arrearsPY, penalty, ni, recon, reloc, pca, meterSale, others, lossDisc, srDisc, pmtType, runningBalance) "
                            + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
            
            ps.setDate(1, date);
            ps.setInt(2, customer.getId());
            ps.setFloat(3, current);
            ps.setFloat(4, arrearsCY);
            ps.setFloat(5, arrearsPY);
            ps.setFloat(6, penalty);
            ps.setFloat(7, ni);
            ps.setFloat(8, recon);
            ps.setFloat(9, reloc);
            ps.setFloat(10, pca);
            ps.setFloat(11, meterSale);
            ps.setFloat(12, others);
            ps.setFloat(13, lossDisc);
            ps.setFloat(14, srDisc);
            ps.setString(15, pmtType);
            ps.setFloat(16, runningBalance);
            ps.executeUpdate();
            ResultSet keyRs = ps.getGeneratedKeys();
            keyRs.first();
            this.setId(keyRs.getInt(1));
            
        }else {
            PreparedStatement ps = Base.getConnection().prepareStatement(
                    "UPDATE receipt SET date=?, customerId=?, current=?, "
                            + "arrearsCY=?, arrearsPY=?, penalty=?, ni=?, recon=?, "
                            + "reloc=?, pca=?, meterSale=?, others=?, lossDisc=?, "
                            + "srDisc=?, pmtType=?, runningBalance=? "
                            + "WHERE ID=?");
            ps.setDate(1, date);
            
            ps.setInt(2, customer.getId());
            ps.setFloat(3, current);
            ps.setFloat(4, arrearsCY);
            ps.setFloat(5, arrearsPY);
            ps.setFloat(6, penalty);
            ps.setFloat(7, ni);
            ps.setFloat(8, recon);
            ps.setFloat(9, reloc);
            ps.setFloat(10, pca);
            ps.setFloat(11, meterSale);
            ps.setFloat(12, others);
            ps.setFloat(13, lossDisc);
            ps.setFloat(14, srDisc);
            ps.setString(15, pmtType);
            ps.setFloat(16, runningBalance);
            ps.setInt(17, id);
            ps.executeUpdate();
        }
    }
    
    public Float generateRunningBalance() throws SQLException {
        Receipt prevReceipt = Receipt.getLatestReceipt(customer);
        Billing prevBilling = Billing.getLatestBilling(customer);
        
        if(prevReceipt.date.after(prevBilling.getDateBilled())){
            if(prevReceipt.getRunningBalance()>0) {
                this.runningBalance = prevReceipt.getRunningBalance()-this.getAmount();
                return this.runningBalance;
            }else {
                return null;
            }
        }else {
            if(prevBilling.getRunningBalance()>0) {
                this.runningBalance = prevBilling.getRunningBalance()-this.getAmount();
                return this.runningBalance;
            }else {
                return null;
            }
        }
    }
    
    public static int nextORNumber() throws SQLException {
        ResultSet rs = Base.getConnection().createStatement().executeQuery(
                "SELECT AUTO_INCREMENT FROM information_schema.TABLES "
                        + "WHERE TABLE_SCHEMA='cwd' AND TABLE_NAME='receipt'");
        rs.first();
        return rs.getInt(1);
    }
    
    public float getAmount() {
        return (current + arrearsCY + arrearsPY + penalty + ni + recon + reloc + pca + meterSale + others);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public java.sql.Date getDate() {
        return date;
    }

    public void setDate(java.sql.Date date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public float getCurrent() {
        return current;
    }

    public void setCurrent(float current) {
        this.current = current;
    }

    public float getArrearsCY() {
        return arrearsCY;
    }

    public void setArrearsCY(float arrearsCY) {
        this.arrearsCY = arrearsCY;
    }

    public float getArrearsPY() {
        return arrearsPY;
    }

    public void setArrearsPY(float arrearsPY) {
        this.arrearsPY = arrearsPY;
    }

    public float getPenalty() {
        return penalty;
    }

    public void setPenalty(float penalty) {
        this.penalty = penalty;
    }

    public float getNi() {
        return ni;
    }

    public void setNi(float ni) {
        this.ni = ni;
    }

    public float getRecon() {
        return recon;
    }

    public void setRecon(float recon) {
        this.recon = recon;
    }

    public float getReloc() {
        return reloc;
    }

    public void setReloc(float reloc) {
        this.reloc = reloc;
    }

    public float getPca() {
        return pca;
    }

    public void setPca(float pca) {
        this.pca = pca;
    }

    public float getMeterSale() {
        return meterSale;
    }

    public void setMeterSale(float meterSale) {
        this.meterSale = meterSale;
    }

    public float getOthers() {
        return others;
    }

    public void setOthers(float others) {
        this.others = others;
    }

    public float getLossDisc() {
        return lossDisc;
    }

    public void setLossDisc(float lossDisc) {
        this.lossDisc = lossDisc;
    }

    public float getSrDisc() {
        return srDisc;
    }

    public void setSrDisc(float srDisc) {
        this.srDisc = srDisc;
    }

    public String getPmtType() {
        return pmtType;
    }

    public void setPmtType(String pmtType) {
        this.pmtType = pmtType;
    }

    public float getRunningBalance() {
        return runningBalance;
    }

    public void setRunningBalance(float runningBalance) {
        this.runningBalance = runningBalance;
    }
    
    public static Receipt getLatestReceipt(Customer customer) throws SQLException {
        ResultSet rs = Base.getConnection().createStatement().executeQuery(
                "SELECT id FROM receipt WHERE customerId=" + customer.getId() + " ORDER BY date DESC LIMIT 1");
        if(rs.first()) {
            return Receipt.load(rs.getInt("id"));
        }else {
            return null;
        }
    }
}
