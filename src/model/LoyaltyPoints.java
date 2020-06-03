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
public class LoyaltyPoints extends NintendoGift {
    // reducerea care se poate face în cadrul campaniei
    float loyaltyPointsSale;

    public LoyaltyPoints(int id, String code, String email) {
        super(id, code, email);
    }

LoyaltyPoints(){};
    
    @Override
     public String toString()
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        if(this.giftUsingDate != null)
            return "[" + this.giftID + ";" + this.giftStatus + ";" + this.giftUserEmail + ";" + this.loyaltyPointsSale + ";" + this.giftCampaignID + ";" + this.giftUsingDate + "]";
        else
            return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm").format(this.giftUsingDate) + "[" + this.giftID + ";" + this.giftStatus + ";" + this.giftUserEmail + ";" + this.loyaltyPointsSale + ";" + this.giftCampaignID + ";" + "]";
    }
   // super();
}
