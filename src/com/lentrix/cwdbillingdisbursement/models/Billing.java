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
import java.sql.Date;

/**
 *
 * @author lentrix
 */
public class Billing {
    private int id;
    private Customer customer;
    private Date dateBilled;
    private int year;
    private int month;
    private int previous;
    private int reading;
    private Date dateReading;
    private int consumption;
    private float rate;
    private float amount;
    private boolean settled;
    private Date dateDue;
    private OtherCharges otherCharges;
    private float runningBalance;
        
    public static String[] months = 
    {
        "","January","February","March", "April", "May", "June", 
        "July", "August", "September", "October",  "November", "December"
    };

    public Billing(int id, Customer customer, Date dateBilled, int year, 
            int month,int previous, int reading, Date dateReading, int consumption, 
            float rate, float amount, boolean settled, Date dateDue, float runningBalance) {
        this.id = id;
        this.customer = customer;
        this.dateBilled = dateBilled;
        this.year = year;
        this.month = month;
        this.reading = reading;
        this.dateReading = dateReading;
        this.rate = rate;
        this.previous = previous;
        this.consumption = consumption;
        this.amount = amount;
        this.settled = settled;
        this.dateDue = dateDue;
        this.runningBalance = runningBalance;
    }
    
    public OtherCharges getOtherCharges() throws SQLException {
        if(otherCharges==null) {
            otherCharges = OtherCharges.load(this);
        }
        return otherCharges;
    }
    
    public boolean getSettled() {
        return settled;
    }
    
    public Date getDateDue() {
        return dateDue;
    }
    
    public void setOtherCharges(OtherCharges otherCharges) {
        this.otherCharges = otherCharges;
    }
    
    public Billing() {
        this.id = -1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getDateBilled() {
        return dateBilled;
    }

    public void setDateBilled(Date dateBilled) {
        this.dateBilled = dateBilled;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getReading() {
        return reading;
    }

    public void setReading(int reading) {
        this.reading = reading;
        this.recomputeConsumption();
        
    }

    public Date getDateReading() {
        return dateReading;
    }

    public void setDateReading(Date dateReading) {
        this.dateReading = dateReading;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }
    
    @Override
    public String toString() {
        return String.valueOf(id);
    }
    
    public static Billing load(Customer customer, int year, int month) throws SQLException {
        PreparedStatement ps = Base.getConnection().prepareStatement(
                "SELECT b.* FROM billing b "
                        + "JOIN customer c ON c.id=b.customerId "
                        + "WHERE c.id=? AND b.year=? AND b.month=?");
        ps.setInt(1, customer.getId());
        ps.setInt(2, year);
        ps.setInt(3, month);
        
        ResultSet rs = ps.executeQuery();
        if(rs.first()) {
            Billing billing = new Billing(
                    rs.getInt("id"),
                    Customer.load(Base.getConnection(), rs.getInt("customerId")),
                    rs.getDate("dateBilled"),
                    rs.getInt("year"),
                    rs.getInt("month"),
                    rs.getInt("previous"),
                    rs.getInt("reading"),
                    rs.getDate("dateReading"),
                    rs.getInt("consumption"),
                    rs.getFloat("rate"),
                    rs.getFloat("amount"),
                    rs.getBoolean("settled"),
                    rs.getDate("dateDue"),
                    rs.getFloat("runningBalance")
            );
            rs.close();
            ps.close();
            
            return billing;
        }else {
            ps.close();
            return null;
        }
    }
    
    public static Billing load(int id) throws SQLException {
        PreparedStatement ps = Base.getConnection().prepareStatement(
                "SELECT b.* FROM billing b "
                        + "JOIN customer c ON c.id=b.customerId "
                        + "WHERE b.id=?");
        ps.setInt(1,id);
        ResultSet rs = ps.executeQuery();
        if(rs.first()) {
            Billing billing = new Billing(
                    rs.getInt("id"),
                    Customer.load(Base.getConnection(), rs.getInt("customerId")),
                    rs.getDate("dateBilled"),
                    rs.getInt("year"),
                    rs.getInt("month"),
                    rs.getInt("previous"),
                    rs.getInt("reading"),
                    rs.getDate("dateReading"),
                    rs.getInt("consumption"),
                    rs.getFloat("rate"),
                    rs.getFloat("amount"),
                    rs.getBoolean("settled"),
                    rs.getDate("dateDue"),
                    rs.getFloat("runningBalance")
            );
            rs.close();
            ps.close();
            
            return billing;
        }else {
            ps.close();
            return null;
        }
    }

    public void resetOtherCharges() throws SQLException {
        this.otherCharges = OtherCharges.load(this);
    }

    public static Billing[] load(int year, int month, int zone) throws SQLException {
        PreparedStatement ps = Base.getConnection().prepareStatement(
                "SELECT b.* FROM billing b "
                        + "JOIN customer c ON c.id=b.customerId "
                        + "WHERE b.year=? AND b.month=? AND c.zone=? "
                        + "ORDER BY c.name");
        ps.setInt(1, year);
        ps.setInt(2, month);
        ps.setInt(3, zone);
        ResultSet rs = ps.executeQuery();
        rs.last();
        int rows = rs.getRow();
        rs.beforeFirst();
        Billing[] billings = new Billing[rows];
        int i = 0;
        while(rs.next()){
            billings[i] = new Billing(
                    rs.getInt("id"),
                    Customer.load(Base.getConnection(), rs.getInt("customerId")),
                    rs.getDate("dateBilled"),
                    rs.getInt("year"),
                    rs.getInt("month"),
                    rs.getInt("previous"),
                    rs.getInt("reading"),
                    rs.getDate("dateReading"),
                    rs.getInt("consumption"),
                    rs.getFloat("rate"),
                    rs.getFloat("amount"),
                    rs.getBoolean("settled"),
                    rs.getDate("dateDue"),
                    rs.getFloat("runningBalance")
            );
            OtherCharges other = OtherCharges.load(billings[i]);
            if(other!=null) {
                billings[i].setOtherCharges(other);
            }
            
            i++;
        }
        rs.close();
        ps.close();
        return billings;
    }
    
    public static Billing[] load(Customer customer, String order) throws SQLException {
        PreparedStatement ps = Base.getConnection().prepareStatement(
                "SELECT b.* FROM billing b "
                        + "JOIN customer c ON c.id=b.customerId "
                        + "WHERE c.id=? " + order );
        ps.setInt(1, customer.getId());
        ResultSet rs = ps.executeQuery();
        rs.last();
        int rows = rs.getRow();
        rs.beforeFirst();
        Billing[] billings = new Billing[rows];
        int i = 0;
        while(rs.next()){
            billings[i] = new Billing(
                    rs.getInt("id"),
                    Customer.load(Base.getConnection(), rs.getInt("customerId")),
                    rs.getDate("dateBilled"),
                    rs.getInt("year"),
                    rs.getInt("month"),
                    rs.getInt("previous"),
                    rs.getInt("reading"),
                    rs.getDate("dateReading"),
                    rs.getInt("consumption"),
                    rs.getFloat("rate"),
                    rs.getFloat("amount"),
                    rs.getBoolean("settled"),
                    rs.getDate("dateDue"),
                    rs.getFloat("runningBalance")
            );
            OtherCharges other = OtherCharges.load(billings[i]);
            if(other!=null) {
                billings[i].setOtherCharges(other);
            }
            
            i++;
        }
        rs.close();
        ps.close();
        return billings;
    }
    
    public static Billing[] load(Customer customer) throws SQLException {
        return load(customer, "");
    }
    
    public void save(User user) throws SQLException {
        if(id==-1){
            saveNew(user);
        }else {
            update(user);
        }
    }
    
    private void update(User user) throws SQLException {
        PreparedStatement ps = Base.getConnection().prepareStatement(
                "UPDATE billing SET "
                        + "customerId=?, dateBilled=?, year=?, month=?, "
                        + "reading=?, dateReading=?, rate=?, "
                        + "previous=?, consumption=?, amount=?, settled=?, dateDue=?, runningBalance=? WHERE id=?");
        ps.setInt(1, customer.getId());
        ps.setDate(2, dateBilled);
        ps.setInt(3,year);
        ps.setInt(4, month);
        ps.setInt(5, reading);
        ps.setDate(6, dateReading);
        ps.setFloat(7, rate);
        
        ps.setInt(8, previous);
        ps.setInt(9, consumption);
        ps.setFloat(10, amount);
        ps.setBoolean(11, settled);
        ps.setDate(12, dateDue);
        ps.setFloat(13, runningBalance);
        ps.setInt(14, id);
        ps.execute();
        ps.close();
        Log.log(user.getId(), "Updated the billing of " + customer.getName() 
                + " for the month of " + months[month] + " " + year + ".");
    }
    
    private void saveNew(User user) throws SQLException {
        PreparedStatement ps = Base.getConnection().prepareStatement(
                "INSERT INTO billing (customerId, dateBilled, year, month, "
                        + "previous, reading, dateReading, consumption, rate, discount, amount, settled, dateDue, runningBalance) "
                        + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)", 
                PreparedStatement.RETURN_GENERATED_KEYS);
        ps.setInt(1, customer.getId());
        ps.setDate(2, dateBilled);
        ps.setInt(3,year);
        ps.setInt(4, month);
        ps.setInt(5, previous);
        ps.setInt(6, reading);
        ps.setDate(7, dateReading);
        ps.setInt(8, consumption);
        ps.setFloat(9, rate);
        ps.setFloat(10, 0.0f);
        ps.setFloat(11, amount);
        ps.setBoolean(12, false);
        ps.setDate(13, dateDue);
        ps.setFloat(14, runningBalance);
        ps.execute();
        
        //retrieve generated id.
        ResultSet rs = ps.getGeneratedKeys();
        rs.first();
        int generatedId = rs.getInt(1);
        this.id = generatedId;
        
        Log.log(user.getId(), "Created the billing of " + customer.getName() 
                + " for the month of " + months[month] + " " + year + ".");
        ps.close();
        rs.close();
    }
    
    public int getPreviousReading() throws SQLException {
        PreparedStatement ps = Base.getConnection().prepareStatement(
                "SELECT * FROM billing WHERE customerId=? AND year=? AND month=?");
        int y;
        int m;
        if(month==1) {
            m = 12;
            y = year-1;
        }else {
            m = month-1;
            y = year;
        }
        
        ps.setInt(1, customer.getId());
        ps.setInt(2, y);
        ps.setInt(3, m);
        ResultSet rs = ps.executeQuery();
        if(rs.first()) {
            int initReading = rs.getInt("reading");
            rs.close();
            return initReading;
        }else {
            rs.close();
            return customer.getInitialReading();
        }
    }
    
    public int getArrears() throws SQLException {
        return reading - getPreviousReading();
    }
    
    public void delete(User user) throws SQLException{
        PreparedStatement ps = Base.getConnection().prepareStatement(
                "DELETE FROM billing WHERE id=?");
        ps.setInt(1, id);
        ps.executeUpdate();
        Log.log(user.getId(), "Deleted the " + Billing.months[month] + " " + year + " billing of " + customer.getName() + ".");
        ps.close();
    }
    
    public static boolean exists(int year, int month, int customerId) throws SQLException {
        ResultSet rs = Base.getConnection().createStatement().executeQuery(
                "SELECT * FROM billing WHERE year=" + year 
                        + " AND month=" + month 
                        + " AND year = " + year 
                        + " AND customerId = " + customerId
        );
        return rs.first();
    }

    public int getPrevious() {
        return previous;
    }

    public void setPrevious(int previous) {
        this.previous = previous;
        this.recomputeConsumption();
    }

    public int getConsumption() {
        return consumption;
    }

    public void setConsumption(int consumption) {
        this.consumption = consumption;
    }

    public float getAmount() {
        amount = Base.computeAmount(this.getConsumption());
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
    
    public float getTotalAmount() throws SQLException {
        OtherCharges oth = this.getOtherCharges();
        float amount = this.getAmount();
        if(oth!=null) {
            amount += oth.getTotal();
        }
        return amount;
    }
    
    public float getTotalPayments() throws SQLException {
        ResultSet rs = Base.getConnection().createStatement().executeQuery(
                "SELECT SUM('br.amount') FROM receipt r, billReceipt br "
                        + "WHERE r.id=br.receiptId "
                        + "AND billingId=" + this.id);
        if(rs.first()) {
            return rs.getFloat(1);
        }else {
            return 0.0f;
        }
    }
    
    private OtherCharges grabOrCreateOtherCharges() throws SQLException {
        OtherCharges otherCharges = OtherCharges.load(this);
        if(otherCharges==null) {
            otherCharges = new OtherCharges(-1,this, 0.0f, 0.0f, 0.0f, 0.0f,0.0f,0.0f,null,0.0f,null,0.0f,null);
        }
        return otherCharges;
    }
    
    public boolean hasOtherCharges() {
        return this.otherCharges!=null;
    }
    
    public boolean hasDBOtherCharges() throws SQLException {
        ResultSet rs = Base.getConnection().createStatement().executeQuery(
                "SELECT * FROM otherCharges WHERE billingId=" + this.id);
        return rs.first();
    }
    
    public float getTotalOtherCharges() throws SQLException{
        OtherCharges oth = getOtherCharges();
        if(oth==null){
            return 0.0f;
        }else {
            return oth.getTotal();
        }
    }
    
    public Billing getPreviousBilling() throws SQLException {
        int prevMonth;
        int prevYear;
        
        if(this.month==1) {
            prevMonth = 12;
            prevYear = year-1;
        }else {
            prevMonth = month-1;
            prevYear = year;
        }
        return Billing.load(customer, prevYear, prevMonth);
    }
    
    public Billing getNextBilling() throws SQLException  {
        int nextMonth;
        int nextYear;
        if(month==12) {
            nextMonth = 1;
            nextYear = year+1;
        }else {
            nextMonth = month + 1;
            nextYear = year;
        }
        return Billing.load(customer, nextYear, nextMonth);
    }
    
    private void recomputeConsumption() {
        consumption = reading - previous;
    }
    
    public float getBalance() throws SQLException {
        ResultSet rs = Base.getConnection().createStatement().executeQuery(
                "SELECT SUM(arrears), SUM(meterSale), SUM(ni), SUM(pca), "
                        + "SUM(recon), SUM(reloc), SUM(other1), SUM(other2), SUM(other3) "
                        + "FROM receipt WHERE billing_id=" + getId());
        rs.first();
        float totPaid = 0.0f;
        for(int i=1; i<=9; i++) {
            totPaid += rs.getFloat(i);
        }
        
        return getTotalAmount() - totPaid;
    }
    
    public String getMonthString() {
        String months[] = {"", "Jan","Feb", "Mar", "Apr", "May", "Jun","Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        return months[this.getMonth()];
    }
    
    public Balances getBalances() throws SQLException{
        return getBalances(false, false);
    }
    
    public Balances getBalances(boolean hasDiscount, boolean hasPenalty) throws SQLException {
        ResultSet rs = Base.getConnection().createStatement().executeQuery(
                "SELECT SUM(arrears), SUM(meterSale), SUM(ni), SUM(pca), "
                        + "SUM(recon), SUM(reloc), SUM(other1), SUM(other2), SUM(other3) "
                        + "FROM receipt WHERE billing_id=" + getId());
        if(rs.first()) {
            
            Balances balances = new Balances();
            balances.setArrears(this.getAmount()-rs.getFloat(1));
            OtherCharges otherCharges = this.getOtherCharges();
            if(otherCharges!=null) {
                balances.setMeterSale(otherCharges.getMeterSaleCharge()-rs.getFloat(2));
                balances.setNi(otherCharges.getNiCharge()-rs.getFloat(3));
                balances.setPca(otherCharges.getPcaCharge()-rs.getFloat(4));
                balances.setRecon(otherCharges.getReconCharge()-rs.getFloat(5));
                balances.setReloc(otherCharges.getRelocCharge()-rs.getFloat(6));
                balances.setOther1(otherCharges.getOther1Charge()-rs.getFloat(7));
                balances.setOther2(otherCharges.getOther2Charge()-rs.getFloat(8));
                balances.setOther3(otherCharges.getOther3Charge()-rs.getFloat(9));
            }
            
            return balances;
        }else {
            return null;
        }
    }
    
    public static Billing getLatestBilling(Customer customer) throws SQLException {
        ResultSet rs = Base.getConnection().createStatement().executeQuery(
                "SELECT id FROM billing WHERE customerId=" + customer.getId() + " ORDER BY year DESC, month DESC LIMIT 1");
        if(rs.first()) {
            return Billing.load(rs.getInt("id"));
        }else {
            return null;
        }
    }

    public float getRunningBalance() {
        return runningBalance;
    }

    public void setRunningBalance(float runningBalance) {
        this.runningBalance = runningBalance;
    }
    
    public Float generateRunningBalance() throws SQLException {
        Receipt prevReceipt = Receipt.getLatestReceipt(customer);
        Billing prevBilling = Billing.getLatestBilling(customer);
        
        if(prevBilling==null) return null;
        
        if(prevReceipt!=null && prevReceipt.getDate().after(prevBilling.getDateBilled())){
            if(prevReceipt.getRunningBalance()>0) {
                this.runningBalance = prevReceipt.getRunningBalance()+this.getAmount();
                return this.runningBalance;
            }else {
                return null;
            }
        }else {
            if(prevBilling.getRunningBalance()>0) {
                this.runningBalance = prevBilling.getRunningBalance()+this.getAmount();
                return this.runningBalance;
            }else {
                return null;
            }
        }
    }
}
