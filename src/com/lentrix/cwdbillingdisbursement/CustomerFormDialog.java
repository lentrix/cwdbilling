/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lentrix.cwdbillingdisbursement;

import com.lentrix.cwdbillingdisbursement.models.Customer;
import com.lentrix.cwdbillingdisbursement.models.User;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author lentrix
 */
public class CustomerFormDialog extends javax.swing.JDialog {

    private Customer model;
    private User user;
    /**
     * Creates new form CustomerFormDialog
     */
    public CustomerFormDialog(java.awt.Frame parent, boolean modal, User user) {
        super(parent, modal);
        this.user = user;
        initComponents();
    }
    
    public void setModel(Customer model) {
        this.model = model;
        refreshView();
    }
    
    private void refreshView() {
        
        accountNumberField.setText(model.getAcctNumber());
        nameField.setText(model.getName());
        addressField.setText(model.getAddress());
        phoneField.setText(model.getPhone());
        typeField.setSelectedIndex(model.getType());
        zoneField.setText(String.valueOf(model.getZone()));
        statusField.setSelectedIndex(model.getStatus());
        meterNumberField.setText(model.getMeterNumber());
        brandField.setText(model.getBrand());
        sizeField.setText(model.getSize());
        if(model.getDate()!=null && !model.getDate().isEmpty())
            dateInstalledField.setDate(Date.valueOf(model.getDate()));
        initialReadingField.setText(String.valueOf(model.getInitialReading()));
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        accountNumberField = new javax.swing.JTextField();
        nameField = new javax.swing.JTextField();
        addressField = new javax.swing.JTextField();
        phoneField = new javax.swing.JTextField();
        typeField = new javax.swing.JComboBox();
        zoneField = new javax.swing.JTextField();
        statusField = new javax.swing.JComboBox();
        meterNumberField = new javax.swing.JTextField();
        brandField = new javax.swing.JTextField();
        sizeField = new javax.swing.JTextField();
        dateInstalledField = new com.toedter.calendar.JDateChooser();
        initialReadingField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Customer");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/lentrix/cwdbillingdisbursement/icons/Add-Male-User.png"))); // NOI18N
        jButton1.setToolTipText("New customer record.");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/lentrix/cwdbillingdisbursement/icons/Save-icon.png"))); // NOI18N
        jButton2.setToolTipText("Delete customer record.");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Noto Sans", 0, 24)); // NOI18N
        jLabel12.setText("Customer Details");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addContainerGap())
        );

        jPanel2.setLayout(new java.awt.GridLayout(12, 1, 5, 5));

        jLabel1.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel1.setText("Account Number:");
        jPanel2.add(jLabel1);

        jLabel2.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel2.setText("Name:");
        jPanel2.add(jLabel2);

        jLabel3.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel3.setText("Address:");
        jPanel2.add(jLabel3);

        jLabel11.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel11.setText("Phone:");
        jPanel2.add(jLabel11);

        jLabel4.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel4.setText("Type:");
        jPanel2.add(jLabel4);

        jLabel13.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel13.setText("Zone:");
        jPanel2.add(jLabel13);

        jLabel5.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel5.setText("Status:");
        jPanel2.add(jLabel5);

        jLabel6.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel6.setText("Meter Number:");
        jPanel2.add(jLabel6);

        jLabel7.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel7.setText("Brand:");
        jPanel2.add(jLabel7);

        jLabel8.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel8.setText("Size:");
        jPanel2.add(jLabel8);

        jLabel9.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel9.setText("Date Installed:");
        jPanel2.add(jLabel9);

        jLabel10.setFont(new java.awt.Font("Noto Sans", 1, 14)); // NOI18N
        jLabel10.setText("Initial Reading:");
        jPanel2.add(jLabel10);

        jPanel3.setLayout(new java.awt.GridLayout(12, 1, 5, 5));

        accountNumberField.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jPanel3.add(accountNumberField);

        nameField.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jPanel3.add(nameField);

        addressField.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jPanel3.add(addressField);

        phoneField.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jPanel3.add(phoneField);

        typeField.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        typeField.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Residential", "Commercial" }));
        jPanel3.add(typeField);

        zoneField.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jPanel3.add(zoneField);

        statusField.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        statusField.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Active", "Disconnected" }));
        jPanel3.add(statusField);

        meterNumberField.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jPanel3.add(meterNumberField);

        brandField.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jPanel3.add(brandField);

        sizeField.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jPanel3.add(sizeField);

        dateInstalledField.setDateFormatString("yyyy-MM-dd");
        jPanel3.add(dateInstalledField);

        initialReadingField.setFont(new java.awt.Font("Noto Sans", 0, 14)); // NOI18N
        jPanel3.add(initialReadingField);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE))
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(603, 549));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            model.setAcctNumber(accountNumberField.getText());
            model.setName(nameField.getText());
            model.setAddress(addressField.getText());
            model.setPhone(phoneField.getText());
            model.setType(typeField.getSelectedIndex());
            model.setZone(Integer.parseInt(zoneField.getText()));
            model.setStatus(statusField.getSelectedIndex());
            model.setMeterNumber(meterNumberField.getText());
            model.setBrand(brandField.getText());
            model.setSize(sizeField.getText());
            Calendar cdl = dateInstalledField.getCalendar();
            model.setDate(cdl.get(Calendar.YEAR) + "-" + cdl.get(Calendar.MONTH) + "-" + cdl.get(Calendar.DATE));
            model.setInitialReading(Integer.parseInt(initialReadingField.getText()));
            
            model.save(Base.getConnection(), user);
            
            Base.info("The customer data has been saved successfully!", jLabel1);
        }catch(NumberFormatException ex) {
            Base.error(ex.getMessage(), this);
        }catch(SQLException ex) {
            Base.error(ex.getMessage(), this);
        }catch(Exception ex) {
            Base.error(ex.getMessage(), this);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        setModel(new Customer());
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField accountNumberField;
    private javax.swing.JTextField addressField;
    private javax.swing.JTextField brandField;
    private com.toedter.calendar.JDateChooser dateInstalledField;
    private javax.swing.JTextField initialReadingField;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
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
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField meterNumberField;
    private javax.swing.JTextField nameField;
    private javax.swing.JTextField phoneField;
    private javax.swing.JTextField sizeField;
    private javax.swing.JComboBox statusField;
    private javax.swing.JComboBox typeField;
    private javax.swing.JTextField zoneField;
    // End of variables declaration//GEN-END:variables
}
