/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lentrix.cwdbillingdisbursement;

/**
 *
 * @author lentrix
 */
public class MyCrypto {
    
    private static String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
    
    public static String encrypt(String str){
        int rnd = (int)(Math.random()*5);
        
        StringBuilder builder = new StringBuilder();
        builder.append(rnd);
        for(int i=0; i<str.length(); i++) {
            char c = (char) (str.charAt(i) + rnd);
            builder.append(c);
            builder.append(getRandomCharacter());
        }
        
        return builder.toString();
    }
    
    public static char getRandomCharacter(){
        int rnd = (int)(Math.random()*characters.length());
        return characters.charAt(rnd);
    }
    
    public static String getRandomString(int n) {
        char[] cSet = new char[n];
        for(int i=0; i<n; i++) {
            cSet[i] = getRandomCharacter();
        }
        return String.valueOf(cSet);
    }
    
    public static String decrypt(String str){
        int key = Integer.parseInt(str.substring(0,1));
        StringBuilder builder = new StringBuilder();
        for(int i=1; i<str.length(); i+=2){
            char c = (char)(str.charAt(i)-key);
            builder.append(c);
        }
        return builder.toString();
    }
    
    public static void main(String[] args) {
        String source = "TheMatrix";
        System.out.println("The source is " + source);
        String crypted = encrypt(source);
        System.out.println("The encrypted is " + crypted);
        String decrypted = decrypt(crypted);
        System.out.println("The decrypted is " + decrypted);
    }
}
