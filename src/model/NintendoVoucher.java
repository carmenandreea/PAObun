/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Windows10
 */
public class NintendoVoucher extends NintendoGift {
    float giftVoucherSum; //suma ce poate fi folosita in cadrul campaniei
    
    public  float getVoucherSum() {
        return giftVoucherSum;
    } 
    
    public void setVoucherSum (float x)
    {
        giftVoucherSum=x;
    }
}
