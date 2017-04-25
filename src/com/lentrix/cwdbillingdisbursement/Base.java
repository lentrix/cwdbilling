/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lentrix.cwdbillingdisbursement;

import java.awt.Component;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Properties;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author lentrix
 */
public class Base {
    private static Connection conn;
    private static Properties creds;
    
    public static Connection getConnection(){
        
        if(conn==null){
            try{
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                
                if(creds==null) return null;
                
                conn = DriverManager.getConnection("jdbc:mysql://" 
                        + MyCrypto.decrypt(creds.getProperty("host")) + "/" 
                        + MyCrypto.decrypt(creds.getProperty("db")),
                        MyCrypto.decrypt(creds.getProperty("user")), 
                        MyCrypto.decrypt(creds.getProperty("password")));
                
            }catch(Exception ex) {
                error(ex.getMessage(), null);
                ex.printStackTrace();
            }
        }
        return conn;
    }
    
    public static Properties getProperties(){
        
        creds = new Properties();
        try{
            creds.load(new FileReader("creds.property"));
            
        }catch(FileNotFoundException ex){

            error("There was no credentials property file."
                    + "\nPlease contact your system administrator"
                    + "\nto fill up the following for with the server credentials.", null);
            return null;
        }catch(IOException ex) {
            ex.printStackTrace();
            
        }
        return creds;
    }
    
    public static void error(String message, Component parent) {
        JOptionPane.showMessageDialog(parent, message, "Error!", JOptionPane.ERROR_MESSAGE);
    }
    
    public static void info(String message, Component parent) {
        JOptionPane.showMessageDialog(parent, message, "Information!", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static DefaultTableCellRenderer getRightRender() {
        DefaultTableCellRenderer rightRender = new DefaultTableCellRenderer();
        rightRender.setHorizontalAlignment(javax.swing.JLabel.RIGHT);
        
        return rightRender;
    }
    
    public static float computeRate(int consumption){
        try {
            SAXBuilder builder = new SAXBuilder();
            Document doc = builder.build(new File("rates.xml"));
            Element root = doc.getRootElement();
            
            //check minimum..
            int minCeiling = Integer.parseInt(root.getChild("minimum").getChildText("to"));
            float minimum = Float.parseFloat(root.getChild("minimum").getChildText("amount"));

            if(consumption<=minCeiling) {
                return 0.0f;
            }else {
                Object[] rates = root.getChildren("rate").toArray();
                for(int i=0; i<rates.length; i++) {
                    Element rateElement = (Element)rates[i];
                    int from = Integer.parseInt(rateElement.getChildText("from"));
                    int to = Integer.parseInt(rateElement.getChildText("to"));
                    float rt = Float.parseFloat(rateElement.getChildText("percubic"));
                    if(consumption>=from && consumption<=to){
                        return rt;
                    }
                }
            }
        }catch(IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }catch(JDOMException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return 0f;
    }
    
    public static float computeAmount(int consumption) {
        try{
            SAXBuilder builder = new SAXBuilder();
            Document doc = builder.build(new File("rates.xml"));
            Element root = doc.getRootElement();
            
            //check minimum..
            int minCeiling = Integer.parseInt(root.getChild("minimum").getChildText("to"));
            float minimum = Float.parseFloat(root.getChild("minimum").getChildText("amount"));
            
            int c = consumption-minCeiling;
            float amount = minimum;
            
            List el = root.getChildren();
            
            float lastRate = 0.0f;
            
            for(int i=1; i<el.size() &&  c>0; i++) {
                int xc = (c>10) ? 10 : c;
                Element rate = (Element)el.get(i);
                lastRate = Float.parseFloat(rate.getChildText("percubic"));
                amount += xc*lastRate;
                c-=10;
            }
            
            if(c>0) {
                amount += c*lastRate;
            }
            
            return amount;
        }catch(IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }catch(JDOMException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        return 0.0f;
    }
    
    public static float floatify(Object object) {
        float val = 0f;
        if(object!=null) {
            try {
                val = Float.parseFloat(String.valueOf(object));
            }catch(NumberFormatException ex) {
                val = 0f;
            }
        }else {
            val = 0f;
        }
        return val;
    }
    
    public static String currency(float amount) {
        return String.format("%.2f", amount);
    }
    
    public static void main(String[] args) {
        for(int i=0; i<80; i++) {
            System.out.println(i + " : " + computeAmount(i));
        }
    }
    
    
}
