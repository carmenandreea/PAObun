/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.util.*;
import java.lang.*;
import java.io.*; 
/**
 * 
 *
 * @author Windows10
 */
public class Campaign {
    public int campaignID;
    String campaignName;
    int campaignTotalVouchersNumber;
    int campaignAvailableVouchersNumber;
    CampaignVoucherMap campaignVouchersDictionary = new CampaignVoucherMap();

    private Campaign() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private Object entrySet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    enum CampaignStatusType {
        NEW,
        STARTED,
        EXPIRED,
        CANCELLED
    }
    CampaignStatusType campaignStatus;
    char campaignStrategyType;
    public int getCampaignId (int id)
    {
        this.campaignID=id;
        return campaignID;
    }
    public Campaign(int id, String name, String description, int budget,  int becStatus)
    {
        this.campaignID = id;
        this.campaignName = name;
        this.campaignTotalVouchersNumber = budget;
        this.campaignAvailableVouchersNumber = this.campaignTotalVouchersNumber;
        //this.campaignStrategyType = strategy_type;
        
        if(becStatus == 0)
            this.campaignStatus = this.campaignStatus.NEW;
        else if(becStatus == 1)
            this.campaignStatus = this.campaignStatus.STARTED;
        else if(becStatus == 2)
            this.campaignStatus = this.campaignStatus.EXPIRED;
    }
     public Set<NintendoGift> getVouchers()
    {
        Set<NintendoGift> set = new HashSet<NintendoGift>();
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
}
