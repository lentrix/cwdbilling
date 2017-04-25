/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lentrix.cwdbillingdisbursement.models;

/**
 *
 * @author sirbenj
 */
public class Balances {
    private float arrears;
    private float meterSale;
    private float ni;
    private float pca;
    private float recon;
    private float reloc;
    private float other1;
    private float other2;
    private float other3;

    public float getArrears() {
        return arrears;
    }

    public void setArrears(float arrears) {
        this.arrears = arrears;
    }

    public float getMeterSale() {
        return meterSale;
    }

    public void setMeterSale(float meterSale) {
        this.meterSale = meterSale;
    }

    public float getNi() {
        return ni;
    }

    public void setNi(float ni) {
        this.ni = ni;
    }

    public float getPca() {
        return pca;
    }

    public void setPca(float pca) {
        this.pca = pca;
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

    public float getOther1() {
        return other1;
    }

    public void setOther1(float other1) {
        this.other1 = other1;
    }

    public float getOther2() {
        return other2;
    }

    public void setOther2(float other2) {
        this.other2 = other2;
    }

    public float getOther3() {
        return other3;
    }

    public void setOther3(float other3) {
        this.other3 = other3;
    }

    public Balances(float arrears, float meterSale, float ni, float pca, float recon, float reloc, float other1, float other2, float other3) {
        this.arrears = arrears;
        this.meterSale = meterSale;
        this.ni = ni;
        this.pca = pca;
        this.recon = recon;
        this.reloc = reloc;
        this.other1 = other1;
        this.other2 = other2;
        this.other3 = other3;
    }
    
    public Balances() {
        this.arrears = 0.0f;
        this.meterSale = 0.0f;
        this.ni = 0.0f;
        this.pca = 0.0f;
        this.recon = 0.0f;
        this.reloc = 0.0f;
        this.other1 = 0.0f;
        this.other2 = 0.0f;
        this.other3 = 0.0f;
    }
    
    public float getTotal() {
        return arrears+meterSale+ni+pca+recon+reloc+other1+other2+other3;
    }
}
