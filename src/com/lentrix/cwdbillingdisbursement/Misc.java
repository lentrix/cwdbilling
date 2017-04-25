/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lentrix.cwdbillingdisbursement;

import java.awt.print.PrinterJob;
import javax.print.PrintService;

public class Misc {
    public static void main(String[] args) {
        PrintService[] printServices;
        printServices = PrinterJob.lookupPrintServices();
        for(int i=0; i<printServices.length; i++) {
            System.out.println(printServices[i].getName());
        }
        if(printServices.length==0) {
            System.out.println("Got nothin.");
        }
    }
}