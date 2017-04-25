/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lentrix.cwdbillingdisbursement;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.lentrix.cwdbillingdisbursement.models.Billing;
import com.lentrix.cwdbillingdisbursement.models.Customer;
import com.lentrix.cwdbillingdisbursement.models.Receipt;
import com.lentrix.cwdbillingdisbursement.models.User;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author lentrix
 */
public class CustomerLedger extends javax.swing.JDialog {
    Customer customer;
    User user;
    /**
     * Creates new form CustomerLedger
     */
    public CustomerLedger(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }
    
    public CustomerLedger(java.awt.Frame parent, boolean modal, Customer customer, User user) {
        this(parent, modal);
        this.customer = customer;
        this.user = user;
        renderDetails();
        renderLedger();
    }

    private void renderDetails() {
        customerLabel.setText(customer.getName());
        accountNumberLabel.setText(customer.getAcctNumber());
    }
    
    private void renderLedger() {
        try {
            LinkedList billings = customer.getBills();
            LinkedList receipts = customer.getPayments();
            System.out.println("Billings: " + billings.size() + " Receipts: " + receipts.size());
            int bIndex = 0;
            int rIndex = 0;
            
            DefaultTableModel model = new DefaultTableModel(){
                @Override
                public boolean isCellEditable(int row, int column) {
                    return column==11;
                }

                @Override
                public void setValueAt(Object aValue, int row, int column) {
                    if(column==11) {
                        Object billingNumberObject = getValueAt(row, 1);
                        if(billingNumberObject!=null) {
                            updateBillingRunningBalance(Integer.parseInt(billingNumberObject.toString()), Float.parseFloat(aValue.toString()));
                        }else {
                            updateReceiptRunningBalance(Integer.parseInt(getValueAt(row,2).toString()), Float.parseFloat(aValue.toString()));
                        }
                    }
                    
                    super.setValueAt(aValue, row, column);
                }
                
            };
            
            
            model.addColumn("Date Billed");
            model.addColumn("Bill Number");
            model.addColumn("OR #");
            model.addColumn("Particulars");
            model.addColumn("Reading");
            model.addColumn("Consumption");
            model.addColumn("Billing Amount");
            model.addColumn("Penalty");
            model.addColumn("Arrears & Others");
            model.addColumn("Sr. Citizen");
            model.addColumn("Date Paid");
            model.addColumn("Running Balance");
            
            while(bIndex<billings.size() && rIndex<receipts.size()) {
                
                Billing bill = (Billing)billings.get(bIndex);
                Receipt receipt = (Receipt)receipts.get(rIndex);
                
                if(bill.getDateBilled().before(receipt.getDate())) {
                    addBillingRow(bill, model);
                    bIndex++;
                }else {
                    addReceiptRow(receipt, model);
                    rIndex++;
                }
                
            }
            
            if(bIndex<billings.size()) {
                while(bIndex<billings.size()) {
                    Billing billing = (Billing)billings.get(bIndex++);
                    addBillingRow(billing, model);
                }
            }
            
            if(rIndex<receipts.size()) {
                while(rIndex<receipts.size()) {
                    Receipt receipt = (Receipt)receipts.get(rIndex++);
                    addReceiptRow(receipt, model);
                }
            }
            
            ledgerTable.setModel(model);
            
            ledgerTable.getColumnModel().getColumn(6).setCellRenderer(Base.getRightRender());
            ledgerTable.getColumnModel().getColumn(7).setCellRenderer(Base.getRightRender());
            ledgerTable.getColumnModel().getColumn(8).setCellRenderer(Base.getRightRender());
            ledgerTable.getColumnModel().getColumn(9).setCellRenderer(Base.getRightRender());
            ledgerTable.getColumnModel().getColumn(11).setCellRenderer(Base.getRightRender());
            
        }catch(SQLException ex) {
            Base.error(ex.getMessage(), this);
            ex.printStackTrace();
        }
    }
    
    private void updateBillingRunningBalance(int billingNumber, float amount) {
        try {
            Billing billing = Billing.load(billingNumber);
            billing.setRunningBalance(amount);
            billing.save(user);
        }catch(SQLException ex) {
            Base.error(ex.getMessage(), this);
            ex.printStackTrace();
        }
    }
    
    private void updateReceiptRunningBalance(int orNumber, float amount) {
        try {
            Receipt receipt = Receipt.load(orNumber);
            receipt.setRunningBalance(amount);
            receipt.save();
        }catch(SQLException ex) {
            Base.error(ex.getMessage(), this);
            ex.printStackTrace();
        }
    }
    
    private void addBillingRow(Billing bill, DefaultTableModel model) {
        model.addRow(new Object[] {
            bill.getDateBilled(),
            bill.getId(),
            null,
            "Water Bill",
            bill.getReading(), 
            bill.getConsumption(), 
            String.format("%.2f", bill.getAmount()),null,null,null,null,
            bill.getRunningBalance()
        });
    }
    
    private void addReceiptRow(Receipt receipt, DefaultTableModel model) {
        float penalty = receipt.getPenalty();
        float amount = receipt.getAmount();
        float srDisc = receipt.getSrDisc();
        
        model.addRow(new Object[] {
            null,null,receipt.getId(),"Payment",null,null,null,
            penalty==0?null:String.format("%.2f", penalty),amount==0?null:String.format("%.2f",amount),srDisc==0?null:String.format("%.2f", srDisc),
            receipt.getDate(), receipt.getRunningBalance()
        });
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        generateButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        customerLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        accountNumberLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ledgerTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Customer Ledger");

        generateButton.setText("Generate");
        generateButton.setToolTipText("Print Ledger");
        generateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        jLabel1.setText("Customer:");

        customerLabel.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        customerLabel.setText(" ");

        jLabel2.setFont(new java.awt.Font("Noto Sans", 0, 24)); // NOI18N
        jLabel2.setText("Customer Ledger");

        jLabel3.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        jLabel3.setText("Account #:");

        accountNumberLabel.setFont(new java.awt.Font("DejaVu Sans", 0, 18)); // NOI18N
        accountNumberLabel.setText(" ");

        ledgerTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(ledgerTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1046, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel3))
                                .addGap(24, 24, 24)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(accountNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(customerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(generateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(generateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(customerLabel))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(accountNumberLabel))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
                .addContainerGap())
        );

        setSize(new java.awt.Dimension(1068, 614));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void generateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateButtonActionPerformed
        
        try {
            Document doc = new Document();
            PdfWriter.getInstance(doc, new FileOutputStream("Ledger.pdf"));
            doc.open();
            
            Paragraph heading = new Paragraph();
            Chunk title1 = new Chunk("CLARIN WATER DISCTRICT");
            Font font1 = FontFactory.getFont("Helvetica", 12, Font.BOLD);
            title1.setFont(font1);
            
            Font contentFont = FontFactory.getFont("Helvetica", 8, Font.NORMAL);
            
            Chunk title2 = new Chunk("\nClarin, Bohol\n\n");
            Chunk title3 = new Chunk("CUSTOMER'S LEDGER");
            title3.setFont(FontFactory.getFont("Helvetica", 14, Font.BOLD));
            
            heading.add(title1);
            heading.add(title2);
            heading.add(title3);
            
            heading.setAlignment(Paragraph.ALIGN_CENTER);
            
            doc.add(heading);
            
            doc.add(new Phrase("\n"));
            
            Font boldContent = FontFactory.getFont("Helvetica", 8, Font.BOLD);
            PdfPTable infoTable = new PdfPTable(2);
            infoTable.setWidthPercentage(100);
            Chunk nameLabel = new Chunk("NAME: ", boldContent);
            Chunk nameContent = new Chunk(customer.getName() + "\n", contentFont);
            Chunk addressLabel = new Chunk("ADDRESS: ", boldContent);
            Chunk addressContent = new Chunk(customer.getAddress() + "\n", contentFont);
            Chunk dateInstallLabel = new Chunk("Date Installed: ", boldContent);
            Chunk dateInstallContent = new Chunk(customer.getDate(), contentFont);
            Phrase leftInfo = new Phrase();
            leftInfo.add(nameLabel);
            leftInfo.add(nameContent);
            leftInfo.add(addressLabel);
            leftInfo.add(addressContent);
            leftInfo.add(dateInstallLabel);
            leftInfo.add(dateInstallContent);
            
            infoTable.addCell(new PdfPCell(leftInfo));
            
            Chunk acctNoLabel = new Chunk("ACCT #: ", boldContent);
            Chunk acctNoContent = new Chunk(customer.getAcctNumber() + "\n", contentFont);
            Chunk brandLabel = new Chunk("Brand: ", boldContent);
            Chunk brandContent = new Chunk(customer.getBrand()+"\n", contentFont);
            Chunk serialLabel = new Chunk("Meter Serial: ", boldContent);
            Chunk serialContent = new Chunk(customer.getMeterNumber(), contentFont);
            
            Phrase rightInfo = new Phrase();
            rightInfo.add(acctNoLabel);
            rightInfo.add(acctNoContent);
            rightInfo.add(brandLabel);
            rightInfo.add(brandContent);
            rightInfo.add(serialLabel);
            rightInfo.add(serialContent);
            
            infoTable.addCell(new PdfPCell(rightInfo));
            
            doc.add(infoTable);
            
            doc.add(new Paragraph(" "));
            
            PdfPTable table = new PdfPTable(new float[]{1.5f,1f,1f,2.5f,1f,1f,1f,1f,1f,1f,1.5f,1f});
            table.setWidthPercentage(100);
            String headers[] ={"Date\nBilled", "Bill\nNumber", "O.R.\nNumber", "Particulars", 
                "Reading", "Consumption\nin cu.m.", "Billing\nAmount", "Penalty","Arrears &\nOther\nCredits",
                "Less:\nSr.Citzn\nDiscount", "Date\nPaid", "Running\nBalance"
            };
            Font headerFont = FontFactory.getFont("Helvetica", 8, Font.BOLD);
            
            for(int i=0;i<headers.length; i++) {
                PdfPCell cell = new PdfPCell(new Phrase(headers[i], contentFont));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
            }
            
            TableModel model = ledgerTable.getModel();
            int row = model.getRowCount();
            int col = model.getColumnCount();
            
            for(int i=0; i<row; i++) {
                for(int j=0; j<col; j++) {
                    Object value = model.getValueAt(i, j);
                    String str = value==null ? "" : value.toString();
                    PdfPCell cell = new PdfPCell(new Phrase(str, contentFont));
                    cell.setHorizontalAlignment(getAlignment(j));
                    table.addCell(cell);
                }
            }
            
            doc.add(table);
            
            doc.close();
            
            if(Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(new File("Ledger.pdf"));
            }else {
                Base.error("Desktop is not supported", this);
            }
            
        } catch (FileNotFoundException ex) {
            Base.error(ex.getMessage(), this);
        } catch (DocumentException ex) {
            Base.error(ex.getMessage(), this);
        } catch (IOException ex) {
            Base.error(ex.getMessage(), this);
        }
    }//GEN-LAST:event_generateButtonActionPerformed

    
    private int getAlignment(int col) {
        switch(col) {
            case 0:
            case 1:
            case 2:
            case 5:
            case 4:
            case 10: return Element.ALIGN_CENTER;
            case 3: return Element.ALIGN_LEFT;
            default: return Element.ALIGN_RIGHT;
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CustomerLedger.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CustomerLedger.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CustomerLedger.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CustomerLedger.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CustomerLedger dialog = new CustomerLedger(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel accountNumberLabel;
    private javax.swing.JLabel customerLabel;
    private javax.swing.JButton generateButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable ledgerTable;
    // End of variables declaration//GEN-END:variables
}
