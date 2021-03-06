/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lentrix.cwdbillingdisbursement;

import com.lentrix.cwdbillingdisbursement.models.Customer;
import com.lentrix.cwdbillingdisbursement.models.User;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.ListIterator;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lentrix
 */
public class Customers extends javax.swing.JFrame {

    Customer selectedCustomer = null;
    JFrame parent;
    User user;
    /**
     * Creates new form Customers
     */
    public Customers(JFrame parent, User user) {
        initComponents();
        this.parent = parent;
        this.user = user;
        refreshView();
    }
    
    private void refreshView() {
        try{
            String whereClause = "";
            if(!filterText.getText().isEmpty()) {
                String filter = filterText.getText();
                whereClause = " WHERE name LIKE '%" + filter + 
                        "%' OR acctNumber LIKE '%" + filter + "%' " +
                        "OR address LIKE '%" + filter + "%' ";
            }
            LinkedList customers = Customer.getAll(Base.getConnection(), whereClause);
            Object content[][] = new Object[customers.size()][6];
            String columns[] = {"Account Number", "Name", "Address", "Status", "Type"};
            
            ListIterator iter = customers.listIterator();
            int i = 0;
            while(iter.hasNext()) {
                Customer c = (Customer)iter.next();
                content[i][0] = c.getAcctNumber();
                content[i][1] = c;
                content[i][2] = c.getAddress();
                content[i][3] = c.getStatusText();
                content[i][4] = c.getTypeText();
                i++;
            }
            
            DefaultTableModel model = new DefaultTableModel(content, columns) {

                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
                
            };
            jTable1.setModel(model);
        }catch(Exception ex) {
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

        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        ledgerButton = new javax.swing.JButton();
        filterText = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Customers ");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Noto Sans", 0, 24)); // NOI18N
        jLabel1.setText("List of Customers");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/lentrix/cwdbillingdisbursement/icons/Add-Male-User.png"))); // NOI18N
        jButton1.setToolTipText("Create new customer record.");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

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
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        ledgerButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/lentrix/cwdbillingdisbursement/icons/ledger.png"))); // NOI18N
        ledgerButton.setToolTipText("Open Customer Ledger");
        ledgerButton.setEnabled(false);
        ledgerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ledgerButtonActionPerformed(evt);
            }
        });

        filterText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterTextActionPerformed(evt);
            }
        });

        jLabel2.setText("Filter");

        jButton2.setText("X");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(1, 1, 1)
                        .addComponent(filterText, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)
                        .addComponent(ledgerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(filterText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addComponent(jButton2))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(ledgerButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );

        setSize(new java.awt.Dimension(739, 589));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        selectedCustomer = (Customer)jTable1.getValueAt(jTable1.getSelectedRow(), 1);
        if(evt.getClickCount()==2) {
            CustomerFormDialog dialog = new CustomerFormDialog(this, true, ((MainFrame)parent).getUser());
            dialog.setModel(selectedCustomer);
            dialog.setVisible(true);
            refreshView();
        }else {
            ledgerButton.setEnabled(true);
        }
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        CustomerFormDialog cform = new CustomerFormDialog(this, true, ((MainFrame)parent).getUser());
        cform.setModel(new Customer());
        cform.setVisible(true);
        refreshView();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        parent.setVisible(true);
    }//GEN-LAST:event_formWindowClosed

    private void ledgerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ledgerButtonActionPerformed
        new CustomerLedger(this, true, selectedCustomer, user).setVisible(true);
    }//GEN-LAST:event_ledgerButtonActionPerformed

    private void filterTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterTextActionPerformed
        refreshView();
    }//GEN-LAST:event_filterTextActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        filterText.setText(null);
        refreshView();
        filterText.grabFocus();
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField filterText;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton ledgerButton;
    // End of variables declaration//GEN-END:variables
}
