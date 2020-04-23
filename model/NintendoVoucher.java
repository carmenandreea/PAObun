/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.format.DateTimeFormatter;


/**
 *
 * @author Windows10
 */
public class NintendoVoucher extends NintendoGift {
    float giftVoucherSum; //suma ce poate fi folosita in cadrul campaniei

    public NintendoVoucher(int id, String code, String email) {
        super(id, code, email);
    }

   /* public NintendoVoucher(int parseInt, String string, String string0, String string1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
    public  float getVoucherSum() {
        return giftVoucherSum;
    } 
    
    public void setVoucherSum (float x)
    {
        giftVoucherSum=x;
    }
    
      @Override public String toString()
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        if(null != this.giftUsingDate)
            return "[" + this.giftID + ";" + this.giftStatus + ";" + this.giftUserEmail + ";" + this.giftVoucherSum + ";" + this.giftCampaignID + ";" + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(this.giftUsingDate) + "]";
        else
            return "[" + this.giftID + ";" + this.giftStatus + ";" + this.giftUserEmail + ";" + this.giftVoucherSum + ";" + this.giftCampaignID + ";" + this.giftUsingDate + "]";
    }
}
