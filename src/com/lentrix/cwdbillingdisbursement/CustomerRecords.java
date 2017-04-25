/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lentrix.cwdbillingdisbursement;

import com.lentrix.cwdbillingdisbursement.models.Billing;
import com.lentrix.cwdbillingdisbursement.models.Customer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.LinkedList;
import java.util.ListIterator;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lentrix
 */
public class CustomerRecords extends javax.swing.JDialog {

    /**
     * Creates new form CustomerRecords
     */
    public CustomerRecords(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        initCustomers();
    }
    
    private void initCustomers() {
        try{
            LinkedList customers = Customer.getAll(Base.getConnection(), "");
            customers.addFirst(null);
            customersComboBox.setModel(new DefaultComboBoxModel(customers.toArray()));
        }catch(Exception ex) {
            Base.error(ex.getMessage(), this);
            ex.printStackTrace();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        customersComboBox = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        brandLabel = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        serialNumberLabel = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        sizeLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        readingHistoyTable = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        dateInstalledLabelField = new javax.swing.JLabel();
        addressLabelField = new javax.swing.JLabel();
        nameLabelField = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        accountNumberLabelField = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Customer Records");

        jLabel1.setFont(new java.awt.Font("Noto Sans", 0, 24)); // NOI18N
        jLabel1.setText("Customer Records");

        customersComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customersComboBoxActionPerformed(evt);
            }
        });

        jLabel2.setText("Select Customer:");

        jPanel1.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Customer Ledger", new javax.swing.ImageIcon(getClass().getResource("/com/lentrix/cwdbillingdisbursement/icons/ledger24.png")), jPanel1); // NOI18N

        jLabel7.setText("Brand:");

        brandLabel.setText(" ");

        jLabel8.setText("Serial Number:");

        serialNumberLabel.setText(" ");

        jLabel9.setText("Size:");

        sizeLabel.setText(" ");

        readingHistoyTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(readingHistoyTable);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(brandLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(serialNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(sizeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 225, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(brandLabel)
                    .addComponent(jLabel8)
                    .addComponent(serialNumberLabel)
                    .addComponent(jLabel9)
                    .addComponent(sizeLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Meter Reading History", new javax.swing.ImageIcon(getClass().getResource("/com/lentrix/cwdbillingdisbursement/icons/custom-reports24.png")), jPanel2); // NOI18N

        jLabel3.setText("Name:");

        jLabel4.setText("Address:");

        jLabel5.setText("Date Installed:");

        dateInstalledLabelField.setText(" ");

        addressLabelField.setText(" ");

        nameLabelField.setText(" ");

        jLabel6.setText("Account Number:");

        accountNumberLabelField.setText(" ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTabbedPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(customersComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel3))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(nameLabelField, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                            .addComponent(addressLabelField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(dateInstalledLabelField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(accountNumberLabelField, javax.swing.GroupLayout.DEFAULT_SIZE, 275, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(customersComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(nameLabelField)
                    .addComponent(jLabel6)
                    .addComponent(accountNumberLabelField))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(addressLabelField)
                    .addComponent(jLabel5)
                    .addComponent(dateInstalledLabelField))
                .addGap(18, 18, 18)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(932, 657));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void customersComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customersComboBoxActionPerformed
        Customer selectedCustomer = (Customer)customersComboBox.getSelectedItem();
        
        nameLabelField.setText(selectedCustomer.getName());
        addressLabelField.setText(selectedCustomer.getAddress());
        accountNumberLabelField.setText(selectedCustomer.getAcctNumber());
        dateInstalledLabelField.setText(selectedCustomer.getDate());
        
        loadCustomerLedger(selectedCustomer);
        loadMeterReadingHistory(selectedCustomer);
    }//GEN-LAST:event_customersComboBoxActionPerformed

    private void loadCustomerLedger(Customer customer) {
        try{
            PreparedStatement b = Base.getConnection().prepareStatement(
                    "SELECT b.dateBilled, b.id, b.reading, b.consumption, b.amount \n" +
                    "FROM billing b\n" +
                    "WHERE b.customerId = ?\n" +
                    "ORDER BY b.dateBilled");
            
            PreparedStatement r = Base.getConnection().prepareStatement(
                    "SELECT r.id, r.penalty, (r.current + r.arrearsCY + r.arrearsPY) AS 'arrears', r.srDisc, r.date\n" +
                    "FROM receipt r\n" +
                    "WHERE date BETWEEN ? AND ? AND customerId=? " +
                    "ORDER BY date");
            
            b.setInt(1, customer.getId());
            ResultSet brs = b.executeQuery();
            
            LinkedList rows = new LinkedList();
            
            java.sql.Date fromDate = null;
            
            if(brs.first()) {
                LedgerRow firstRow = new LedgerRow();
                firstRow.set("Water Bill", brs.getDate("dateBilled"), brs.getInt("id"), brs.getInt("reading"), brs.getInt("consumption"), brs.getFloat("amount"));
                fromDate = brs.getDate("dateBilled");
                rows.add(firstRow);
            }
            
            while(brs.next()) {
                r.setDate(1, fromDate);
                r.setDate(2, brs.getDate("dateBilled"));
                r.setInt(3, customer.getId());
                ResultSet rrs = r.executeQuery();
                rrs.beforeFirst();
                while(rrs.next()) {
                    LedgerRow rrow = new LedgerRow();
                    rrow.set("Payment", rrs.getInt("id"), rrs.getFloat("penalty"), rrs.getFloat("arrears"), rrs.getFloat("srDisc"), rrs.getDate("date"));
                    rows.add(rrow);
                }
                
                LedgerRow row = new LedgerRow();
                row.set("Water Bill", brs.getDate("dateBilled"), brs.getInt("id"), brs.getInt("reading"), brs.getInt("consumption"), brs.getFloat("amount"));
                rows.add(row);
                fromDate = brs.getDate("dateBilled");
            }
            
            r.setDate(1, fromDate);
            r.setDate(2, java.sql.Date.valueOf(LocalDate.now(ZoneId.systemDefault())));
            r.setInt(3, customer.getId());
            ResultSet rrs = r.executeQuery();
            rrs.beforeFirst();
            while(rrs.next()) {
                LedgerRow rrow = new LedgerRow();
                rrow.set("Payment", rrs.getInt("id"), rrs.getFloat("penalty"), rrs.getFloat("arrears"), rrs.getFloat("srDisc"), rrs.getDate("date"));
                rows.add(rrow);
            }
            
            renderRowsToLedgerTable(rows);
            
        }catch(SQLException ex) {
            Base.error(ex.getMessage(), this);
            ex.printStackTrace();
        }
    }
    
    private void renderRowsToLedgerTable(LinkedList rows) {
        ListIterator iter = rows.listIterator();
        Object[][] contents = new Object[rows.size()][12];
        String headers[] = {"Date Billed", "Bill No.","OR #", "Particulars",
            "Reading","Consumption","Billing Amount","Penalty","Arrears","Discount",
            "Date Paid","Running Balance"};
        int i=0;
        float runningBalance = 0.0f;
        while(iter.hasNext()) {
            LedgerRow row = (LedgerRow)iter.next();
            contents[i][0] = row.getDateBilled();
            contents[i][1] = row.getBillNumber();
            contents[i][2] = row.getOrNumber();
            contents[i][3] = row.getParticulars();
            contents[i][4] = row.getReading();
            contents[i][5] = row.getConsumption();
            contents[i][6] = row.getBillingAmount();
            contents[i][7] = row.getPenalty();
            contents[i][8] = row.getArrears();
            contents[i][9] = row.getDiscount();
            contents[i][10] = row.getDatePaid();
            
            if(row.getBillingAmount()>0) {
                runningBalance+=row.getBillingAmount();
            }
            if(row.getArrears()>0) {
                runningBalance-=row.getArrears();
            }
            
            contents[i++][11] = runningBalance;
        }
        
        DefaultTableModel model = new DefaultTableModel(contents, headers) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
        };
        
        jTable1.setModel(model);
        jTable1.getColumnModel().getColumn(6).setCellRenderer(Base.getRightRender());
        jTable1.getColumnModel().getColumn(7).setCellRenderer(Base.getRightRender());
        jTable1.getColumnModel().getColumn(8).setCellRenderer(Base.getRightRender());
        jTable1.getColumnModel().getColumn(9).setCellRenderer(Base.getRightRender());
        jTable1.getColumnModel().getColumn(11).setCellRenderer(Base.getRightRender());
        
    }
    
    private void loadMeterReadingHistory(Customer customer) {
        try{
            Billing billings[] = Billing.load(customer, "ORDER BY year, month DESC");
            Object content[][] = new Object[billings.length][5];
            String headers[] = {"Billing Month", "Date of Reading", "Reading", "Consumption", "Amount"};
            
            for(int i=0; i<billings.length; i++) {
                content[i][0] = Billing.months[billings[i].getMonth()] + " " + billings[i].getYear();
                content[i][1] = billings[i].getDateReading();
                content[i][2] = billings[i].getReading();
                content[i][3] = billings[i].getArrears();
                content[i][4] = billings[i].getTotalAmount();
            }
            
            DefaultTableModel model = new DefaultTableModel(content, headers) {

                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
                
            };
            
            readingHistoyTable.setModel(model);
            readingHistoyTable.getColumnModel().getColumn(2).setCellRenderer(Base.getRightRender());
            readingHistoyTable.getColumnModel().getColumn(3).setCellRenderer(Base.getRightRender());
            readingHistoyTable.getColumnModel().getColumn(4).setCellRenderer(Base.getRightRender());
            
            brandLabel.setText(customer.getBrand());
            serialNumberLabel.setText(customer.getMeterNumber());
            sizeLabel.setText(customer.getSize());
            
        }catch(Exception ex) {
            Base.error(ex.getMessage(), this);
        }
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel accountNumberLabelField;
    private javax.swing.JLabel addressLabelField;
    private javax.swing.JLabel brandLabel;
    private javax.swing.JComboBox customersComboBox;
    private javax.swing.JLabel dateInstalledLabelField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel nameLabelField;
    private javax.swing.JTable readingHistoyTable;
    private javax.swing.JLabel serialNumberLabel;
    private javax.swing.JLabel sizeLabel;
    // End of variables declaration//GEN-END:variables

    private class LedgerRow {
        private java.sql.Date dateBilled;
        private int billNumber;
        private int orNumber;
        private String particulars;
        private int reading;
        private int consumption;
        private float billingAmount;
        private float penalty;
        private float arrears;
        private float discount;
        private java.sql.Date datePaid;

        public java.sql.Date getDateBilled() {
            return dateBilled;
        }

        public void setDateBilled(java.sql.Date dateBilled) {
            this.dateBilled = dateBilled;
        }

        public int getBillNumber() {
            return billNumber;
        }

        public void setBillNumber(int billNumber) {
            this.billNumber = billNumber;
        }

        public int getOrNumber() {
            return orNumber;
        }

        public void setOrNumber(int orNumber) {
            this.orNumber = orNumber;
        }

        public String getParticulars() {
            return particulars;
        }

        public void setParticulars(String particulars) {
            this.particulars = particulars;
        }

        public int getReading() {
            return reading;
        }

        public void setReading(int reading) {
            this.reading = reading;
        }

        public float getBillingAmount() {
            return billingAmount;
        }

        public void setBillingAmount(float billingAmount) {
            this.billingAmount = billingAmount;
        }

        public float getPenalty() {
            return penalty;
        }

        public void setPenalty(float penalty) {
            this.penalty = penalty;
        }

        public float getArrears() {
            return arrears;
        }

        public void setArrears(float arrears) {
            this.arrears = arrears;
        }

        public float getDiscount() {
            return discount;
        }

        public void setDiscount(float discount) {
            this.discount = discount;
        }

        public java.sql.Date getDatePaid() {
            return datePaid;
        }

        public void setDatePaid(java.sql.Date datePaid) {
            this.datePaid = datePaid;
        }
        
        public void set(String particulars, java.sql.Date dateBilled, int billNumber, int reading, 
                int consumption, float billingAmount) {
            this.dateBilled = dateBilled;
            this.billNumber = billNumber;
            this.reading = reading;
            this.consumption = consumption;
            this.billingAmount = billingAmount;
            this.particulars = particulars;
        }
        
        public void set(String particulars, int orNumber, float penalty, float arrears, 
                float discount, java.sql.Date datePaid) {
            this.particulars = particulars;
            this.orNumber = orNumber;
            this.penalty = penalty;
            this.arrears = arrears;
            this.discount = discount;
            this.datePaid =datePaid;
        }

        public int getConsumption() {
            return consumption;
        }

        public void setConsumption(int consumption) {
            this.consumption = consumption;
        }
        
    }
}
