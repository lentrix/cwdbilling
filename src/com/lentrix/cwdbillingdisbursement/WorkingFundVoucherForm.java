/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lentrix.cwdbillingdisbursement;

import com.lentrix.cwdbillingdisbursement.models.AccountTitle;
import com.lentrix.cwdbillingdisbursement.models.Log;
import com.lentrix.cwdbillingdisbursement.models.Particulars;
import com.lentrix.cwdbillingdisbursement.models.User;
import com.lentrix.cwdbillingdisbursement.models.WorkingFundVoucher;
import java.sql.SQLException;
import java.util.ListIterator;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lentrix
 */
public class WorkingFundVoucherForm extends javax.swing.JDialog {

    private User user;
    private Vector parts;
    /**
     * Creates new form WorkingFundVoucher
     */
    public WorkingFundVoucherForm(java.awt.Frame parent, boolean modal, User user) {
        super(parent, modal);
        initComponents();
        rootPane.setDefaultButton(addButton);
        this.user = user;
        initAccounts();
        dateField.setCalendar(java.util.Calendar.getInstance());
        parts = new Vector();
    }

    private void initAccounts() {
        try{
            AccountTitle accts[] = AccountTitle.loadAll("");
            DefaultComboBoxModel model = new DefaultComboBoxModel(accts);
            model.insertElementAt(null, 0);
            accountField.setModel(model);
            accountField.setSelectedIndex(0);
        }catch(SQLException ex) {
            Base.error(ex.getMessage(), this);
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

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        accountField = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        amountField = new javax.swing.JTextField();
        addButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        dateField = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        payeeField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        addressField = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        saveButton = new javax.swing.JButton();

        jMenuItem1.setText("Remove");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItem1);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Working Fund Voucher");
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        jLabel1.setText("Working Fund Voucher");

        jLabel2.setText("Account:");

        accountField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accountFieldActionPerformed(evt);
            }
        });

        jLabel3.setText("Amount:");

        amountField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        jLabel4.setText("Date:");

        dateField.setDateFormatString("yyyy-MM-dd");

        jLabel5.setText("Payee:");

        jLabel6.setText("Address:");

        jPanel1.setLayout(new java.awt.BorderLayout());

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
        jTable1.setComponentPopupMenu(jPopupMenu1);
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        saveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/lentrix/cwdbillingdisbursement/icons/Save-icon24.png"))); // NOI18N
        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addComponent(jSeparator2)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(accountField, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(amountField, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(34, 34, 34)
                                        .addComponent(dateField, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(payeeField)
                                    .addComponent(addressField))))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(saveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(dateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(payeeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(addressField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(accountField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(amountField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(saveButton, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        if(!amountField.getText().isEmpty() && accountField.getSelectedIndex()>0) {
            try{
                float amount = Float.parseFloat(amountField.getText());
                parts.add(new Particulars(-1,null,(AccountTitle)accountField.getSelectedItem(), amount));
                refreshTable();
                amountField.setText(null);
                accountField.setSelectedIndex(0);
            }catch(NumberFormatException ex) {
                Base.error("Invalid Amount Entry!", this);
            }
        }else {
            Base.error("Either you have not selected an account or your amount is empty.", this);
        }
    }//GEN-LAST:event_addButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        try {
            if(parts.size()==0) {
                throw new Exception("The particulars is empty.");
            }
            if(payeeField.getText().isEmpty()) {
                throw new Exception("The payee field is empty.");
            }
            if(addressField.getText().isEmpty()) {
                throw new Exception("The address field is empty.");
            }
            
            java.sql.Date date = new java.sql.Date(dateField.getDate().getTime());
            
            WorkingFundVoucher voucher = new WorkingFundVoucher(-1, 
                    date, payeeField.getText(), addressField.getText());
            
            voucher.save();
            
            ListIterator iter = parts.listIterator();
            while(iter.hasNext()) {
                Particulars p = (Particulars)iter.next();
                p.setVoucher(voucher);
                p.save();
            }

            Log.log(user.getId(), "Recorded working fund voucher disbursement");
            
            int response = JOptionPane.showConfirmDialog(this, 
                    "The working fund voucher has been recorded successfully."
                    + "\nDo you want to record another?", "Success", JOptionPane.YES_NO_OPTION);
            if(response == JOptionPane.YES_OPTION) {
                parts = new Vector();
                payeeField.setText(null);
                addressField.setText(null);
                accountField.setSelectedIndex(0);
                refreshTable();
                payeeField.grabFocus();
            }else {
                this.dispose();
            }
            
        }catch(Exception ex) {
            Base.error(ex.getMessage(), this);
            ex.printStackTrace();
        }
    }//GEN-LAST:event_saveButtonActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        parts.remove(jTable1.getSelectedRow());
        refreshTable();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void accountFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accountFieldActionPerformed
        amountField.grabFocus();
    }//GEN-LAST:event_accountFieldActionPerformed

    private void refreshTable() {
        if(parts.size()==0) {
            jTable1.setModel(new DefaultTableModel());
            return;
        }
        Object partsArray[] = parts.toArray();
        Object content[][] = new Object[partsArray.length+1][4];
        String header[] = {"Account No.", "Account Title", "Debit", "Credit"};
        
        float total = 0f;
        
        for(int i=0; i<partsArray.length; i++) {
            Particulars p = (Particulars)partsArray[i];
            content[i][0] = p.getAccount().getAccountNumber();
            content[i][1] = p.getAccount().getAccountTitle();
            content[i][2] = p.getAmount();
            content[i][3] = null;
            total += p.getAmount();
        }
        
        content[partsArray.length][0] = "103";
        content[partsArray.length][1] = "Working Fund";
        content[partsArray.length][2] = null;
        content[partsArray.length][3] = total;
        
        DefaultTableModel model = new DefaultTableModel(content, header){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        jTable1.setModel(model);
        jTable1.getColumnModel().getColumn(2).setCellRenderer(Base.getRightRender());
        jTable1.getColumnModel().getColumn(3).setCellRenderer(Base.getRightRender());
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox accountField;
    private javax.swing.JButton addButton;
    private javax.swing.JTextField addressField;
    private javax.swing.JTextField amountField;
    private com.toedter.calendar.JDateChooser dateField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField payeeField;
    private javax.swing.JButton saveButton;
    // End of variables declaration//GEN-END:variables
}