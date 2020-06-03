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
import javax.swing.*;
import javax.swing.event.*;
//import services.VMS;

/**
 * 
 *
 * @author Windows10
 */
public class Campaign {
    public int campaignID;
    public String campaignName;
    public String campaignDescription;
    public int campaignTotalGiftsNumber;
    public int campaignAvailableGiftsNumber;
    public LocalDateTime campaignStartDate;
    public LocalDateTime campaignEndDate;
    CampaignVoucherMap campaignVouchersDictionary = new CampaignVoucherMap();

    private Campaign() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Campaign(int id, String name, String description, LocalDateTime start_date, LocalDateTime end_date, int budget, char stategy_type, int bec) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Object entrySet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public CampaignVoucherMap campaignGiftsDictionary = new CampaignVoucherMap();
    model.HashSet<User> observers = new HashSet<User>();

    private void addObserver(User i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Object getGifts() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public enum CampaignStatusType {
        NEW,
        STARTED,
        EXPIRED,
        CANCELLED
    }
    public CampaignStatusType campaignStatus;
    public char campaignStrategyType;
    public int getCampaignId (int id)
    {
        this.campaignID=id;
        return campaignID;
    }
    public Campaign(int id, String name, String description, int budget,  int becStatus)
    {
        this.campaignID = id;
        this.campaignName = name;
        this.campaignTotalGiftsNumber = budget;
        this.campaignAvailableGiftsNumber = this.campaignTotalGiftsNumber;
        //this.campaignStrategyType = strategy_type;
        switch (becStatus) {
            case 0:
                this.campaignStatus = this.campaignStatus.NEW;
                break;
            case 1:
                this.campaignStatus = this.campaignStatus.STARTED;
                break;
            case 2:
                this.campaignStatus = this.campaignStatus.EXPIRED;
                break;
            default:
                break;
        }
    }
    /** public Set<NintendoGift> getVouchers()
    {
        Set <NintendoGift> set;
        set = new Set<NintendoGift>();
        Iterator <Map.Entry<String,ArrayList>> it;
         it = campaignVouchersDictionary.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry<String,ArrayList> pair = it.next();
            ArrayList<NintendoGift> value = pair.getValue();
            Iterator setIt = value.iterator();
            while(setIt.hasNext())
            {
                set.add(( NintendoGift)setIt.next());
            }
        }
        return set;
    }*/
    
    public model.HashSet<NintendoGift> getVouchers()
    {
        model.HashSet<NintendoGift> set = new HashSet<NintendoGift>();
        Iterator<Map.Entry<String,ArrayList>> it = campaignVouchersDictionary.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry<String,ArrayList> pair = it.next();
            ArrayList<NintendoGift> value = pair.getValue();
            Iterator setIt = value.iterator();
            while(setIt.hasNext())
            {
                set.add((NintendoGift)setIt.next());
            }
        }
        return set;
    }
     public NintendoGift getGift(String code)
    {
        Iterator it = this.getVouchers().iterator();
        while(it.hasNext())
        {
            String str = ((NintendoGift)it.next()).giftCode;
            if(str.equals(code))
                return (NintendoGift)it.next();
        }
        return null;
    }
    public  NintendoGift getGiftCode(String code)
    {
        Iterator it = this.getVouchers().iterator();
        while(it.hasNext())
        {
            String str = (( NintendoGift)it.next()).giftCode;
            if(str.equals(code))
                return ( NintendoGift)it.next();
        }
        return null;
    }
    
    
    public void generateNintendoGift (String email, String giftType, float value)
    {
        if(this.campaignAvailableGiftsNumber == 0 || this.campaignStatus == CampaignStatusType.CANCELLED || this.campaignStatus == this.campaignStatus.EXPIRED)
            return;

        NintendoGift v;
        v = null;
        if(giftType.equals("CampaignGift"))
        {
            //v = new NintendoVoucher();
        }
        else if(giftType.equals("LoyaltyGift"))
        {
            //v = new LoyaltyPoints();
        }
        
        v.giftUserEmail = email;
        v.giftStatus = NintendoGift.giftStatusType.UNUSED;
        if(giftType.equals("GiftVoucher"))
        {
            ((NintendoVoucher)v).giftVoucherSum = value;
        }
        else if(giftType.equals("LoyaltyVoucher"))
        {
            ((LoyaltyPoints)v).loyaltyPointsSale = value;
        }
        this.campaignAvailableGiftsNumber --;
        v.giftID = this.campaignTotalGiftsNumber - this.campaignAvailableGiftsNumber;
        v.giftCode = "" + v.giftID;
        v.giftCampaignID = this.campaignID;
        
        this.campaignVouchersDictionary.addVoucher(v);
        
        VMS vms = VMS.getInstance();
        Set<User> users = vms.getUsers();
        Iterator it = users.iterator();
        
        while(it.hasNext())
        {   
            User i = (User)it.next();
            if(i.userEmail.equals(email))
            {
                this.addObserver(i);
                i.userVouchersDictionary.addVoucher(v);
                break;
            }
        }
    }
    
    
    
    
    
    
     public void redeemVoucher(String code, LocalDateTime date)
    {
        boolean isOK = true;
        if(!(date.isAfter(this.campaignStartDate) && date.isBefore(this.campaignEndDate)))
            isOK = false;
        if(this.campaignStatus != CampaignStatusType.STARTED)
            isOK = false;
        Set<NintendoGift> vSet;
        vSet = (Set<NintendoGift>) this.getVouchers();
        Iterator it = vSet.iterator();
        while(it.hasNext())
        {
            NintendoGift v = (NintendoGift)it.next();
            if(v.giftCode.equals(code))
            {
                if(v.giftStatus == v.giftStatus.UNUSED && isOK)
                {
                    v.giftStatus = NintendoGift.giftStatusType.USED;
                    v.giftUsingDate = date;
                    VMS vms = VMS.getInstance();
                    Set<User> users = vms.getUsers();
                    Iterator it2 = users.iterator();
                    while(it2.hasNext())
                    {
                        User i = (User)it2.next();
                        UserVoucherMap map = i.userVouchersDictionary;
                        Iterator it3 = map.entrySet().iterator();
                        while(it3.hasNext())
                        {
                            Map.Entry entry = (Map.Entry)it3.next();
                            if((int)entry.getKey() == this.campaignID)
                            {
                                if(((Set<NintendoGift>)entry.getValue()).contains(v))
                                {
                                    if(map.checkIfLast(v) == true)
                                    {
                                        this.removeObserver(i);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    
                }
                break;
            }
        }
    }
    public void removeObserver(User user)
    {
        
    }
     public model.HashSet<User> getObservers()
    {
        return this.observers;
    }
    
     
      public void generateVoucher(String email, String voucherType, float value)
    {
        if(this.campaignAvailableGiftsNumber == 0 || this.campaignStatus == this.campaignStatus.CANCELLED || this.campaignStatus == this.campaignStatus.EXPIRED)
            return;

        NintendoGift v = null;
        if(voucherType.equals("GiftVoucher"))
        {
            v = new NintendoVoucher();
        }
        else if(voucherType.equals("LoyaltyVoucher"))
        {
            v = new NintendoVoucher();
        }
        
        v.giftUserEmail = email;
        v.giftStatus = v.giftStatus.UNUSED;
        if(voucherType.equals("GiftVoucher"))
        {
            ((NintendoVoucher)v).giftVoucherSum = value;
        }
        else if(voucherType.equals("LoyaltyVoucher"))
        {
            ((LoyaltyPoints)v).loyaltyPointsSale = value;
        }
        this.campaignAvailableGiftsNumber --;
        v.giftID = this.campaignTotalGiftsNumber - this.campaignAvailableGiftsNumber;
        v.giftCode = "" + v.giftID;
        v.giftCampaignID = this.campaignID;
        
        this.campaignGiftsDictionary.addVoucher(v);
        
        VMS vms = VMS.getInstance();
        Set<User> users = vms.getUsers();
        Iterator it = users.iterator();
        while(it.hasNext())
        {   
            User i = (User)it.next();
            if(i.userEmail.equals(email))
            {
                this.addObserver(i);
                i.userVouchersDictionary.addVoucher(v);
                break;
            }
        }
    }
    
  
}
