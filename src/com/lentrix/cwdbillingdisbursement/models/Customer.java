/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lentrix.cwdbillingdisbursement.models;

import com.lentrix.cwdbillingdisbursement.Base;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 *
 * @author lentrix
 */
public class Customer {
    private int id;
    private String acctNumber;
    private String name;
    private String address;
    private String phone;
    private int type;
    private int zone;
    private int status;
    private String meterNumber;
    private String brand;
    private String size;
    private String date;
    private int initialReading;
    
    private final int TYPE_RESIDENTIAL = 1;
    private final int TYPE_COMMERCIAL = 2;
    private final int STATUS_ACTIVE = 1;
    private final int STATUS_DISCONNECTED = 2;

    public Customer(int id, String acctNumber, String name, String address, String phone, int type, int zone, int status, String meterNumber, String brand, String size, String date, int initialReading) {
        this.id = id;
        this.acctNumber = acctNumber;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.type = type;
        this.zone = zone;
        this.status = status;
        this.meterNumber = meterNumber;
        this.brand = brand;
        this.size = size;
        this.date = date;
        this.initialReading = initialReading;
    }
    
    public Customer() {
        this.id= -1;
        this.acctNumber="";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAcctNumber() {
        return acctNumber;
    }

    public void setAcctNumber(String acctNumber) {
        this.acctNumber = acctNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMeterNumber() {
        return meterNumber;
    }

    public void setMeterNumber(String meterNumber) {
        this.meterNumber = meterNumber;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getInitialReading() {
        return initialReading;
    }

    public void setInitialReading(int initialReading) {
        this.initialReading = initialReading;
    }
    
    
    
    public static Customer load(Connection cxn, int id) throws SQLException {
        PreparedStatement ps = cxn.prepareStatement("SELECT * FROM customer where id=?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        
        if(rs.first()) {
            return new Customer(
                    rs.getInt("id"),
                    rs.getString("acctNumber"),
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getString("phone"),
                    rs.getInt("type"),
                    rs.getInt("zone"),
                    rs.getInt("status"),
                    rs.getString("meterNumber"),
                    rs.getString("brand"),
                    rs.getString("size"),
                    rs.getString("dateInstalled"),
                    rs.getInt("initReading")
            );
            
        }else{
            return null;
        }
    }
    
    public static LinkedList getAll(Connection cxn, String whereClause) throws SQLException{
        LinkedList customers = new LinkedList();
        ResultSet rs = cxn.createStatement().executeQuery("SELECT * FROM customer " + whereClause + " ORDER BY name");
        rs.beforeFirst();
        while(rs.next()) {
            customers.add(
                new Customer(
                    rs.getInt("id"),
                    rs.getString("acctNumber"),
                    rs.getString("name"),
                    rs.getString("address"),
                    rs.getString("phone"),
                    rs.getInt("type"),
                    rs.getInt("zone"),
                    rs.getInt("status"),
                    rs.getString("meterNumber"),
                    rs.getString("brand"),
                    rs.getString("size"),
                    rs.getString("dateInstalled"),
                    rs.getInt("initReading")
                )
            );
        }
        return customers;
    }
    
    
    public void save(Connection cxn, User user) throws SQLException, Exception {
        if(!isClean()) {
            throw new Exception("You have an invalid entry.\nPlease check and try again.");
        }
        if(this.id==-1) {
            create(cxn);
            Log.log(user.getId(), "Created the customer record of " + this.name + ".");
        }else {
            update(cxn);
            Log.log(user.getId(), "Updated the customer record of " + this.name + ".");
        }
    }
    
    private void create(Connection cxn) throws SQLException {
        PreparedStatement ps1 = cxn.prepareStatement(
                "INSERT INTO customer ("
                        + "acctNumber, name, address, phone, type, zone, "
                        + "meterNumber, status, dateInstalled, "
                        + "brand, size, initReading) "
                        + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
        ps1.setString(1, this.acctNumber);
        ps1.setString(2, this.name);
        ps1.setString(3, this.address);
        ps1.setString(4, this.phone);
        ps1.setInt(5, this.type);
        ps1.setInt(6, this.zone);
        ps1.setString(7, this.meterNumber);
        ps1.setInt(8, this.status);
        ps1.setString(9, this.date);
        ps1.setString(10, this.brand);
        ps1.setString(11, this.size);
        ps1.setInt(12, this.initialReading);
        ps1.executeUpdate();
        ResultSet keys = ps1.getGeneratedKeys();
        keys.first();
        this.id = keys.getInt(1);
    }
    
    private void update(Connection cxn) throws SQLException  {
        PreparedStatement ps1 = cxn.prepareStatement(
                "UPDATE customer SET "
                        + "acctNumber=?, "
                        + "name=?, "
                        + "address=?, "
                        + "phone=?, "
                        + "type=?, "
                        + "zone=?, "
                        + "meterNumber=?, "
                        + "status=?, "
                        + "dateInstalled=?, "
                        + "brand=?, "
                        + "size=?, "
                        + "initReading=? "
                        + "WHERE id=?");
        ps1.setString(1, this.acctNumber);
        ps1.setString(2, this.name);
        ps1.setString(3, this.address);
        ps1.setString(4, this.phone);
        ps1.setInt(5, this.type);
        ps1.setInt(6, this.zone);
        ps1.setString(7, this.meterNumber);
        ps1.setInt(8, this.status);
        ps1.setString(9, this.date);
        ps1.setString(10, this.brand);
        ps1.setString(11, this.size);
        ps1.setInt(12, this.initialReading);
        ps1.setInt(13, this.id);
        ps1.executeUpdate();
        ps1.close();
    }
    
    
    public boolean isClean() {
        return !this.acctNumber.isEmpty() &&
                !this.name.isEmpty() &&
                !this.address.isEmpty() &&
                !this.phone.isEmpty() &&
                !this.date.isEmpty() &&
                !this.brand.isEmpty() &&
                !this.size.isEmpty() &&
                this.type!=0 &&
                this.status!=0 &&
                this.initialReading!=0;
                
    }

    @Override
    public String toString() {
        return name;
    }
    
    public String getStatusText() {
        return status==1 ? "Active" : "Disconnected";
    }
    
    public String getTypeText() {
        return type==1 ? "Residential" : "Commercial";
    }
    
    public void delete(Connection cxn) throws SQLException{
        PreparedStatement ps = cxn.prepareStatement("DELETE FROM customer WHERE id=?");
        ps.setInt(1, this.id);
        ps.execute();
    }

    public int getZone() {
        return zone;
    }

    public void setZone(int zone) {
        this.zone = zone;
    }
    
    public int getLastReading() throws SQLException {
        ResultSet billRs = Base.getConnection().createStatement().executeQuery(
                "SELECT reading FROM billing WHERE customerId=" + this.id 
                        + " ORDER BY year DESC, month DESC LIMIT 1");
        if(billRs.first()) {
            return billRs.getInt("reading");
        }else {
            return this.initialReading;
        }
    }
    
    public LinkedList getUnsettledBills() throws SQLException {
        ResultSet rs = Base.getConnection().createStatement().executeQuery(
                "SELECT id FROM billing "
                        + "WHERE customerId=" + this.getId() + " AND settled=0");
        LinkedList unsettled = new LinkedList();
        rs.beforeFirst();
        while(rs.next()) {
            unsettled.add(Billing.load(rs.getInt("id")));
        }
        return unsettled;
    }
    
    public LinkedList getBills() throws SQLException {
        ResultSet rs = Base.getConnection().createStatement().executeQuery(
                "SELECT id FROM billing "
                        + "WHERE customerId=" + this.getId() + " ORDER BY dateBilled ASC" );
        LinkedList bills = new LinkedList();
        rs.beforeFirst();
        while(rs.next()) {
            bills.add(Billing.load(rs.getInt("id")));
        }
        return bills;
    }
    
    public LinkedList getPayments() throws SQLException  {
        ResultSet rs = Base.getConnection().createStatement().executeQuery(
                "SELECT id FROM receipt WHERE customerId=" + this.id + " ORDER BY date ASC");
        LinkedList receipts = new LinkedList();
        rs.beforeFirst();
        while(rs.next()) {
            receipts.add(Receipt.load(rs.getInt("id")));
        }
        return receipts;
    }
    
    public AmountSummary getBillingSummary() throws SQLException {
        ResultSet rs = Base.getConnection().createStatement().executeQuery(
                "SELECT SUM(amount) AS 'Water Bill', SUM(meterSale) AS 'Meter Sale', "
                        + "SUM(ni) AS 'N.I.', SUM(pca) AS 'PCA', SUM(recon) AS 'Recon', "
                        + "SUM(reloc) AS 'Reloc', SUM(other1+other2+other3) AS 'Others' "
                        + "FROM billing " +
                "LEFT JOIN otherCharges ON otherCharges.billingId = billing.id\n" +
                "WHERE billing.customerId = " + this.getId());
        if(rs.first()) {
            return new AmountSummary(
                    rs.getFloat("Water Bill"),
                    rs.getFloat("Recon"),
                    rs.getFloat("Reloc"),
                    rs.getFloat("PCA"),
                    rs.getFloat("N.I."),
                    rs.getFloat("Meter Sale"),
                    rs.getFloat("Others")
            );
        }else {
            return null;
        }
    }
    
    public AmountSummary getPaymentSummary() throws SQLException {
        ResultSet rs = Base.getConnection().createStatement().executeQuery(
                "SELECT \n" +
                "   SUM(current + arrearsCY + arrearsPY) AS 'Water Bill', \n" +
                "   SUM(ni) AS 'N.I.',\n" +
                "   SUM(recon) AS 'Recon',\n" +
                "   SUM(reloc) AS 'Reloc',\n" +
                "   SUM(pca) AS 'PCA',\n" +
                "   SUM(meterSale) AS 'Meter Sale',\n" +
                "   SUM(others) AS 'Others'\n" +
                "FROM receipt WHERE customerId=" + this.getId());
        if(rs.first()) {
            return new AmountSummary(
                    rs.getFloat("Water Bill"),
                    rs.getFloat("Recon"),
                    rs.getFloat("Reloc"),
                    rs.getFloat("PCA"),
                    rs.getFloat("N.I."),
                    rs.getFloat("Meter Sale"),
                    rs.getFloat("Others")
            );
        }else {
            return null;
        }
    }
    
    public Float getRunningBalance() throws SQLException {
        Receipt prevReceipt = Receipt.getLatestReceipt(this);
        Billing prevBilling = Billing.getLatestBilling(this);
        
        if(prevBilling==null) return null;
        
        if(prevReceipt!=null && prevReceipt.getDate().after(prevBilling.getDateBilled())){
            return prevReceipt.getRunningBalance();
        }else {
            return prevBilling.getRunningBalance();
        }
    }
}
