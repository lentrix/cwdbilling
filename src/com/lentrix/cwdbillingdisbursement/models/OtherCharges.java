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
public class OtherCharges {
    private int id;
    private Billing billing;
    private float niCharge=0.0f;
    private float reconCharge=0.0f;
    private float relocCharge=0.0f;
    private float pcaCharge=0.0f;
    private float meterSaleCharge=0.0f;
    private float other1Charge=0.0f;
    private String other1Name;
    private float other2Charge=0.0f;
    private String other2Name;
    private float other3Charge=0.0f;
    private String other3Name;

    public OtherCharges(int id, Billing billing, float niCharge, float reconCharge, 
            float relocCharge, float pcaCharge, float meterSaleCharge, 
            float other1Charge, String other1Name, float other2Charge, 
            String other2Name, float other3Charge, String other3Name) {
        this.id = id;
        this.billing = billing;
        this.niCharge = niCharge;
        this.reconCharge = reconCharge;
        this.relocCharge = relocCharge;
        this.pcaCharge = pcaCharge;
        this.meterSaleCharge = meterSaleCharge;
        this.other1Charge = other1Charge;
        this.other1Name = other1Name;
        this.other2Charge = other2Charge;
        this.other2Name = other2Name;
        this.other3Charge = other3Charge;
        this.other3Name = other3Name;
    }
    
    public OtherCharges() {
        this.id=-1;
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
        billing.setOtherCharges(this);
    }

    public float getNiCharge() {
        return niCharge;
    }

    public void setNiCharge(float niCharge) {
        this.niCharge = niCharge;
    }

    public float getReconCharge() {
        return reconCharge;
    }

    public void setReconCharge(float reconCharge) {
        this.reconCharge = reconCharge;
    }

    public float getRelocCharge() {
        return relocCharge;
    }

    public void setRelocCharge(float relocCharge) {
        this.relocCharge = relocCharge;
    }

    public float getPcaCharge() {
        return pcaCharge;
    }

    public void setPcaCharge(float pcaCharge) {
        this.pcaCharge = pcaCharge;
    }

    public float getMeterSaleCharge() {
        return meterSaleCharge;
    }

    public void setMeterSaleCharge(float meterSaleCharge) {
        this.meterSaleCharge = meterSaleCharge;
    }

    public float getOther1Charge() {
        return other1Charge;
    }

    public void setOther1Charge(float other1Charge) {
        this.other1Charge = other1Charge;
    }

    public String getOther1Name() {
        return other1Name;
    }

    public void setOther1Name(String other1Name) {
        this.other1Name = other1Name;
    }

    public float getOther2Charge() {
        return other2Charge;
    }

    public void setOther2Charge(float other2Charge) {
        this.other2Charge = other2Charge;
    }

    public String getOther2Name() {
        return other2Name;
    }

    public void setOther2Name(String other2Name) {
        this.other2Name = other2Name;
    }

    public float getOther3Charge() {
        return other3Charge;
    }

    public void setOther3Charge(float other3Charge) {
        this.other3Charge = other3Charge;
    }

    public String getOther3Name() {
        return other3Name;
    }

    public void setOther3Name(String other3Name) {
        this.other3Name = other3Name;
    }
    
    public float getTotal() {
        return niCharge + reconCharge + relocCharge + pcaCharge + meterSaleCharge 
                + other1Charge + other2Charge + other3Charge;
    }
    
    public void save(User user) throws SQLException {
        if(id==-1) {
            saveNew(user);
        }else {
            update(user);
        }
    }
    
    private void saveNew(User user) throws SQLException {
        PreparedStatement ps = Base.getConnection().prepareStatement(
                "INSERT INTO otherCharges (billingId, ni, recon, reloc, pca, "
                        + "meterSale, other1, other1Name, other2, other2Name,"
                        + "other3, other3Name) "
                        + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setInt(1, billing.getId());
        ps.setFloat(2, niCharge);
        ps.setFloat(3, reconCharge);
        ps.setFloat(4, relocCharge);
        ps.setFloat(5, pcaCharge);
        ps.setFloat(6, meterSaleCharge);
        ps.setFloat(7, other1Charge);
        ps.setString(8, other1Name=="null"?null:other1Name);
        ps.setFloat(9, other2Charge);
        ps.setString(10, other2Name=="null"?null:other2Name);
        ps.setFloat(11, other3Charge);
        ps.setString(12, other3Name=="null"?null:other3Name);
        ps.execute();
        
        ResultSet rs = ps.getGeneratedKeys();
        rs.first();
        int key = rs.getInt(1);
        rs.close();
        
        this.id = key;
        
        Log.log(user.getId(),"Added otherCharges to " + Billing.months[billing.getMonth()] 
                + " " + billing.getYear() + " billing of " + billing.getCustomer().getName() + ".");
        ps.close();
    }
    
    private void update(User user) throws SQLException {
        PreparedStatement ps = Base.getConnection().prepareStatement(
                "UPDATE otherCharges SET billingId=?, ni=?, recon=?, reloc=?, "
                        + "pca=?, meterSale=?, other1=?, other1Name=?, other2=?, "
                        + "other2Name=?, other3=?, other3Name=? "
                        + "WHERE id=?");
        ps.setInt(1, billing.getId());
        ps.setFloat(2, niCharge);
        ps.setFloat(3, reconCharge);
        ps.setFloat(4, relocCharge);
        ps.setFloat(5, pcaCharge);
        ps.setFloat(6, meterSaleCharge);
        ps.setFloat(7, other1Charge);
        ps.setString(8, other1Name=="null"?null:other1Name);
        ps.setFloat(9, other2Charge);
        ps.setString(10, other2Name=="null"?null:other2Name);
        ps.setFloat(11, other3Charge);
        ps.setString(12, other3Name=="null"?null:other3Name);
        ps.setInt(13, id);
        ps.execute();
        Log.log(user.getId(),"Updated the otherCharges to " + Billing.months[billing.getMonth()] 
                + " " + billing.getYear() + " billing of " + billing.getCustomer().getName() + ".");
        ps.close();
    }
    
    public static OtherCharges load(Billing billing) throws SQLException{
        PreparedStatement ps = Base.getConnection().prepareStatement(
                "SELECT * FROM otherCharges WHERE billingId=?");
        if(billing==null) {
            return null;
        }
        ps.setInt(1, billing.getId());
        ResultSet rs = ps.executeQuery();
        
        if(rs.first()) {
            OtherCharges charges = new OtherCharges(
                    rs.getInt("id"),
                    Billing.load(rs.getInt("billingId")),
                    rs.getFloat("ni"),
                    rs.getFloat("recon"),
                    rs.getFloat("reloc"),
                    rs.getFloat("pca"),
                    rs.getFloat("meterSale"),
                    rs.getFloat("other1"),
                    rs.getString("other1Name"),
                    rs.getFloat("other2"),
                    rs.getString("other2Name"),
                    rs.getFloat("other3"),
                    rs.getString("other3Name")
            );
            rs.close();
            ps.close();
            return charges;
        }else {
            rs.close();
            ps.close();
            return null;
        }
    }
}
