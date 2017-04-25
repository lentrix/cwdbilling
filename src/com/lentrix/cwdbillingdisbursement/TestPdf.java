/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lentrix.cwdbillingdisbursement;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;

/**
 *
 * @author lentrix
 */
public class TestPdf {
    public static void main(String[] args) throws Exception {
        Document doc = new Document();
        PdfWriter.getInstance(doc, new FileOutputStream("Test.pdf"));
        doc.open();
        
        Font largeFont = FontFactory.getFont("Helvetica", 15, Font.BOLD);
        
        PdfPTable table = new PdfPTable(3);
        PdfPCell cell1 = new PdfPCell(new Phrase("Test Large Text",largeFont));
        PdfPCell cell2 = new PdfPCell(new Phrase("Test Large Text",largeFont));
        PdfPCell cell3 = new PdfPCell(new Phrase("Test Large Text",largeFont));
        
        table.addCell(cell1);
        table.addCell(cell2);
        table.addCell(cell3);
        
        Font smallFont = FontFactory.getFont("Helvetica", 7);
        PdfPCell sCell1 = new PdfPCell(new Phrase("Small Text Here", smallFont));
        PdfPCell sCell2 = new PdfPCell(new Phrase("Small Text Here", smallFont));
        PdfPCell sCell3 = new PdfPCell(new Phrase("Small Text Here", smallFont));
        
        table.addCell(sCell1);
        table.addCell(sCell2);
        table.addCell(sCell3);
        
        Font tinyFont = FontFactory.getFont("Helvetica", 4);
        table.addCell(new PdfPCell(new Phrase("Tiny Text here", tinyFont)));
        table.addCell(new PdfPCell(new Phrase("Tiny Text here", tinyFont)));
        table.addCell(new PdfPCell(new Phrase("Tiny Text here", tinyFont)));
        
        doc.add(table);
        
        doc.close();
    }
}
