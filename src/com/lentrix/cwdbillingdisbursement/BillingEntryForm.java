/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lentrix.cwdbillingdisbursement;

import com.lentrix.cwdbillingdisbursement.models.Billing;
import com.lentrix.cwdbillingdisbursement.models.Customer;
import com.lentrix.cwdbillingdisbursement.models.User;
import java.awt.Frame;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.LinkedList;
import javax.swing.JOptionPane;

/**
 *
 * @author lentrix
 */
public class BillingEntryForm extends javax.swing.JDialog {

    /**
     * Creates new form BillingEntryForm
     */
    private User user;
    private Calendar cdl;
    private Billing selectedBilling;
    
    private Frame parent;
    
    public BillingEntryForm(java.awt.Frame parent, boolean modal, User user) {
        super(parent, modal);
        initComponents();
        this.user = user;
        this.parent = parent;
        
        cdl = Calendar.getInstance();
        yearField.setText(String.valueOf(cdl.get(Calendar.YEAR)));
        dateReadingField.setDate(cdl.getTime());
        dateDueField.setDate(cdl.getTime());
        int mo = cdl.get(Calendar.MONTH);
        monthField.setSelectedIndex( ((mo==0) ? 12 : mo));
        
        rootPane.setDefaultButton(updateButton);
    }
    
    private boolean filterCheck() {
        return (!yearField.getText().isEmpty() 
                && (monthField.getSelectedIndex()>0)
                && (zoneField.getSelectedIndex()>0));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        yearField = new javax.swing.JTextField();
        monthField = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        dateReadingField = new com.toedter.calendar.JDateChooser();
        zoneField = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        customerFilter = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        customerList = new javax.swing.JList();
        jPanel3 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        dateBilledField = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        previousReading = new javax.swing.JFormattedTextField();
        jLabel7 = new javax.swing.JLabel();
        presentReading = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        consumed = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        consumptionAmount = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        otherCharges = new javax.swing.JFormattedTextField();
        jLabel10 = new javax.swing.JLabel();
        totalAmount = new javax.swing.JFormattedTextField();
        jLabel13 = new javax.swing.JLabel();
        dateDueField = new com.toedter.calendar.JDateChooser();
        jLabel12 = new javax.swing.JLabel();
        runningBalanceField = new javax.swing.JTextField();
        updateButton = new javax.swing.JButton();
        closeButton = new javax.swing.JButton();
        status = new javax.swing.JLabel();
        deleteButton = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Billing Entry");

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel1.setLayout(new java.awt.GridLayout(4, 2, 3, 3));

        jLabel1.setText("Year:");
        jPanel1.add(jLabel1);

        jLabel2.setText("Month:");
        jPanel1.add(jLabel2);

        yearField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yearFieldActionPerformed(evt);
            }
        });
        jPanel1.add(yearField);

        monthField.setModel(new javax.swing.DefaultComboBoxModel(new String[] { " ", "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" }));
        monthField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monthFieldActionPerformed(evt);
            }
        });
        jPanel1.add(monthField);

        jLabel3.setText("Date Reading:");
        jPanel1.add(jLabel3);

        jLabel11.setText("Zone");
        jPanel1.add(jLabel11);

        dateReadingField.setDateFormatString("yyyy-MM-dd");
        jPanel1.add(dateReadingField);

        zoneField.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "  ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        zoneField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zoneFieldActionPerformed(evt);
            }
        });
        jPanel1.add(zoneField);

        jLabel4.setText("Filter Customer Name:");

        customerFilter.setEnabled(false);
        customerFilter.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                customerFilterFocusGained(evt);
            }
        });
        customerFilter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                customerFilterKeyTyped(evt);
            }
        });

        customerList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                customerListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(customerList);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(customerFilter)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(customerFilter, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        jPanel3.setLayout(new java.awt.GridLayout(18, 0, 5, 5));

        jLabel14.setText("Date Billed:");
        jPanel3.add(jLabel14);

        dateBilledField.setDate(new java.util.Date());
        dateBilledField.setDateFormatString("yyyy-MM-dd");
        dateBilledField.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateBilledFieldPropertyChange(evt);
            }
        });
        jPanel3.add(dateBilledField);

        jLabel6.setText("Previous Reading");
        jPanel3.add(jLabel6);

        previousReading.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###0"))));
        previousReading.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        previousReading.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                previousReadingFocusLost(evt);
            }
        });
        jPanel3.add(previousReading);

        jLabel7.setText("Present Reading");
        jPanel3.add(jLabel7);

        presentReading.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###0"))));
        presentReading.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        presentReading.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                presentReadingFocusLost(evt);
            }
        });
        presentReading.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                presentReadingActionPerformed(evt);
            }
        });
        presentReading.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                presentReadingKeyTyped(evt);
            }
        });
        jPanel3.add(presentReading);

        jLabel8.setText("Consumed");
        jPanel3.add(jLabel8);

        consumed.setEditable(false);
        consumed.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("####0"))));
        consumed.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel3.add(consumed);

        jLabel5.setText("Consumption Amount");
        jPanel3.add(jLabel5);

        consumptionAmount.setEditable(false);
        consumptionAmount.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jPanel3.add(consumptionAmount);

        jLabel9.setText("Other Charges");
        jPanel3.add(jLabel9);

        otherCharges.setEditable(false);
        otherCharges.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        otherCharges.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        otherCharges.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                otherChargesMouseClicked(evt);
            }
        });
        jPanel3.add(otherCharges);

        jLabel10.setText("Total Amount");
        jPanel3.add(jLabel10);

        totalAmount.setEditable(false);
        totalAmount.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        totalAmount.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        jPanel3.add(totalAmount);

        jLabel13.setText("Date Due:");
        jPanel3.add(jLabel13);

        dateDueField.setDateFormatString("yyyy-MM-dd");
        jPanel3.add(dateDueField);

        jLabel12.setText("Running Balance:");
        jPanel3.add(jLabel12);

        runningBalanceField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                runningBalanceFieldFocusLost(evt);
            }
        });
        jPanel3.add(runningBalanceField);

        updateButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/lentrix/cwdbillingdisbursement/icons/Save-icon24.png"))); // NOI18N
        updateButton.setText("Update");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        closeButton.setText("Close");
        closeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeButtonActionPerformed(evt);
            }
        });

        status.setText("...");

        deleteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/lentrix/cwdbillingdisbursement/icons/Remove18.png"))); // NOI18N
        deleteButton.setText("Del");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(status, javax.swing.GroupLayout.PREFERRED_SIZE, 436, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 105, Short.MAX_VALUE)
                        .addComponent(deleteButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(closeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(closeButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(deleteButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(status)))
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(860, 664));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void yearFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yearFieldActionPerformed
        filterCheck();
    }//GEN-LAST:event_yearFieldActionPerformed

    private void monthFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monthFieldActionPerformed
        if(filterCheck()){
            customerFilter.setEnabled(true);
            customerFilter.grabFocus();
        }
    }//GEN-LAST:event_monthFieldActionPerformed

    private void zoneFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zoneFieldActionPerformed
        if(filterCheck()){
            customerFilter.setEnabled(true);
            customerFilter.grabFocus();
        }
    }//GEN-LAST:event_zoneFieldActionPerformed

    private void closeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeButtonActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_closeButtonActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        try {
            if(selectedBilling!=null) {
                selectedBilling.save(user);
                if(selectedBilling.hasOtherCharges()) {
                    selectedBilling.getOtherCharges().save(user);
                }
                
                status.setText("Billing of " + selectedBilling.getCustomer().getName() 
                        + " for " + Billing.months[selectedBilling.getMonth()] 
                        + " " + selectedBilling.getYear() + "\nhas been updated.");
                customerFilter.grabFocus();
            }
        }catch(SQLException ex) {
            Base.error(ex.getMessage(), this);
            ex.printStackTrace();
        }
    }//GEN-LAST:event_updateButtonActionPerformed

    private void presentReadingFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_presentReadingFocusLost
        computeValues();
    }//GEN-LAST:event_presentReadingFocusLost

    private void presentReadingKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_presentReadingKeyTyped
        if(evt.getKeyCode()==java.awt.event.KeyEvent.VK_ENTER){
            computeValues();
        }
    }//GEN-LAST:event_presentReadingKeyTyped

    private void customerFilterKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_customerFilterKeyTyped
        
        try{
            LinkedList customers = Customer.getAll(Base.getConnection(), 
                    "WHERE name LIKE '" + customerFilter.getText() + evt.getKeyChar() + "%' "
                            + "AND zone=" + zoneField.getSelectedIndex());
            customerList.setListData(customers.toArray());

        }catch(SQLException ex) {
            Base.error(ex.getMessage(), this);
            ex.printStackTrace();
        }
        
    }//GEN-LAST:event_customerFilterKeyTyped

    private void customerListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_customerListValueChanged
        if(customerList.getSelectedValue() !=null) {
            selectedBilling = findOrCreateBilling();
            refreshForm();
        }
        
    }//GEN-LAST:event_customerListValueChanged

    private void customerFilterFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_customerFilterFocusGained
        
    }//GEN-LAST:event_customerFilterFocusGained

    private void previousReadingFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_previousReadingFocusLost
        computeValues();
    }//GEN-LAST:event_previousReadingFocusLost

    private void presentReadingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_presentReadingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_presentReadingActionPerformed

    private void otherChargesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_otherChargesMouseClicked
        new OtherChargesForm(parent, true, selectedBilling).setVisible(true);
        computeValues();        
    }//GEN-LAST:event_otherChargesMouseClicked

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        int confirm = JOptionPane.showConfirmDialog(parent, 
                "You are about to delete this billing!\nDo you want to continue?", 
                "Delete Billing", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if(confirm==JOptionPane.YES_OPTION) {
            try {
                selectedBilling.delete(user);
                refreshForm();
            }catch(SQLException ex) {
                Base.error(ex.getMessage(), parent);
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void runningBalanceFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_runningBalanceFieldFocusLost
        selectedBilling.setRunningBalance(Float.valueOf(runningBalanceField.getText()));
    }//GEN-LAST:event_runningBalanceFieldFocusLost

    private void dateBilledFieldPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateBilledFieldPropertyChange
        java.util.Date dateBilled = dateBilledField.getDate();
        java.sql.Date sqlDate = new java.sql.Date(dateBilled.getTime());
        if(selectedBilling!=null)
            selectedBilling.setDateBilled(sqlDate);
    }//GEN-LAST:event_dateBilledFieldPropertyChange

    private void computeValues() {
        try {
            int pres = Integer.parseInt(presentReading.getText());
            int prev = Integer.parseInt(previousReading.getText());
            
            int cons = pres-prev;
            float rt = Base.computeRate(cons);
            float amt = Base.computeAmount(cons);
            
            float totAmt=amt;
            
            if(selectedBilling.hasOtherCharges()) {
                totAmt += selectedBilling.getOtherCharges().getTotal();
            }
            
            selectedBilling.setPrevious(prev);
            selectedBilling.setReading(pres);
            selectedBilling.setConsumption(cons);
            selectedBilling.setAmount(amt);
            selectedBilling.setRate(rt);
            
            consumed.setText(String.valueOf(cons));
            consumptionAmount.setText(String.format("%.2f", amt));
            otherCharges.setText(String.format("%.2f", selectedBilling.getTotalOtherCharges()));
            
            totalAmount.setText(String.format("%.2f",totAmt));
            
            Float rBalance = selectedBilling.generateRunningBalance();
            if(rBalance!=null) {
                runningBalanceField.setText(String.format("%.2f", rBalance));
            }else {
                if(runningBalanceField.getText().isEmpty()){
                    Base.error("There is no previous running balance found.\nPlease manually provide the running balance", this);
                    runningBalanceField.grabFocus();
                }
            }
            
        }catch(NumberFormatException ex) {
            Base.error("You entered an invalid value.\nPlease review your entries and try again.", this);
        }catch(SQLException ex) {
            Base.error(ex.getMessage(), this);
        }
    }
    
    private void refreshForm() {
        try {
            if(selectedBilling!=null) {
                dateBilledField.setDate(selectedBilling.getDateBilled());
                previousReading.setText(String.valueOf(selectedBilling.getPrevious()));
                presentReading.setText(String.valueOf(selectedBilling.getReading()));
                consumed.setText(String.valueOf(selectedBilling.getConsumption()));
                otherCharges.setText(String.format("%.2f",selectedBilling.getTotalOtherCharges()));
                consumptionAmount.setText(String.format("%.2f",selectedBilling.getAmount()));
                totalAmount.setText(String.format("%.2f", selectedBilling.getTotalAmount()));
                presentReading.grabFocus();
                presentReading.setSelectionStart(0);
                presentReading.setSelectionEnd(presentReading.getText().length());
            }
        }catch(SQLException ex) {
            Base.error(ex.getMessage(), this);
            ex.printStackTrace();
        }
    }
    
    private Billing findOrCreateBilling() {
        try{
            Customer customer = (Customer)customerList.getSelectedValue();
            java.sql.Date dateBilled = java.sql.Date.valueOf(LocalDate.now());
            int yr = Integer.parseInt(yearField.getText());
            java.sql.Date dtReading = new java.sql.Date(dateReadingField.getDate().getTime());
            java.sql.Date dtDue = new java.sql.Date(dateDueField.getDate().getTime());
            
            Billing billing = Billing.load(customer,
                    Integer.parseInt(yearField.getText()), monthField.getSelectedIndex());
            if(billing==null) {
                billing = new Billing(-1,customer, dateBilled, yr, monthField.getSelectedIndex(), 
                        customer.getLastReading(),0,dtReading,0,0.0f,0.0f,false,dtDue,0f
                );
                status.setText("New billing for " + customer.getName() + " created.");
            }else { 
                status.setText("Billing for " + customer.getName() + " found.");
            }
            return billing;
        }catch(SQLException ex) {
            Base.error(ex.getMessage(), this);
            ex.printStackTrace();
            return null;
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton closeButton;
    private javax.swing.JFormattedTextField consumed;
    private javax.swing.JTextField consumptionAmount;
    private javax.swing.JTextField customerFilter;
    private javax.swing.JList customerList;
    private com.toedter.calendar.JDateChooser dateBilledField;
    private com.toedter.calendar.JDateChooser dateDueField;
    private com.toedter.calendar.JDateChooser dateReadingField;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox monthField;
    private javax.swing.JFormattedTextField otherCharges;
    private javax.swing.JFormattedTextField presentReading;
    private javax.swing.JFormattedTextField previousReading;
    private javax.swing.JTextField runningBalanceField;
    private javax.swing.JLabel status;
    private javax.swing.JFormattedTextField totalAmount;
    private javax.swing.JButton updateButton;
    private javax.swing.JTextField yearField;
    private javax.swing.JComboBox zoneField;
    // End of variables declaration//GEN-END:variables
}