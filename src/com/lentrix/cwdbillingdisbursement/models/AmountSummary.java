/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lentrix.cwdbillingdisbursement.models;

/**
 *
 * @author lentrix
 */
public class AmountSummary {
    private float waterBill, recon, reloc, pca, ni, meterSale, others;

    public AmountSummary(float waterBill, float recon, float reloc, float pca, float ni, float meterSale, float others) {
        this.waterBill = waterBill;
        this.recon = recon;
        this.reloc = reloc;
        this.pca = pca;
        this.ni = ni;
        this.meterSale = meterSale;
        this.others = others;
    }
    
    public AmountSummary() {
        this(0f,0f,0f,0f,0f,0f,0f);
    }

    public float getWaterBill() {
        return waterBill;
    }

    public void setWaterBill(float waterBill) {
        this.waterBill = waterBill;
    }

    public float getRecon() {
        return recon;
    }

    public void setRecon(float recon) {
        this.recon = recon;
    }

    public float getReloc() {
        return reloc;
    }

    public void setReloc(float reloc) {
        this.reloc = reloc;
    }

    public float getPca() {
        return pca;
    }

    public void setPca(float pca) {
        this.pca = pca;
    }

    public float getNi() {
        return ni;
    }

    public void setNi(float ni) {
        this.ni = ni;
    }

    public float getMeterSale() {
        return meterSale;
    }

    public void setMeterSale(float meterSale) {
        this.meterSale = meterSale;
    }

    public float getOthers() {
        return others;
    }

    public void setOthers(float others) {
        this.others = others;
    }
    
    public float getTotal() {
        return meterSale+ni+others+pca+recon+reloc+waterBill;
    }
}
