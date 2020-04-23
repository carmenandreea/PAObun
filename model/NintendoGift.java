/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.util.*;
import java.lang.*;
import java.io.*;
import java.time.*;
import java.time.format.*;

/**
 *
 * @author Windows10
 */

abstract public class NintendoGift {
    
    int giftID;
    String giftCode;
       LocalDateTime giftUsingDate;
    int giftCampaignID;
    enum GiftStatusType {
        USED,
        UNUSED
    }
    GiftStatusType giftStatus;
    String giftUserEmail;
    
    
     public int getGiftId() {
        return giftID;
    }

    public  int getCampaign() {
        return giftCampaignID;
    }

    public String getUser() {
        return giftUserEmail;
    }

    public String getGiftCode() {
        return giftCode;
    }

    public void setGiftCode(String code) {
        this.giftCode = code;
    }

    public void setGiftID (int id) {
        this.giftID = id;
    }
    

    public NintendoGift (int id, String code, String email) {
    giftID=id;
    giftCode=code;
    giftUserEmail=email;
}
}