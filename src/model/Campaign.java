/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;
import java.util.*;
/**
 *
 * @author Windows10
 */
public class Campaign {
     int campaignID;
    String campaignName;
    int campaignTotalVouchersNumber;
    int campaignAvailableVouchersNumber;
    enum CampaignStatusType {
        NEW,
        STARTED,
        EXPIRED,
        CANCELLED
    }
    CampaignStatusType campaignStatus;
    char campaignStrategyType;
    
    public Campaign(int id, String name, String description, int budget, char strategy_type, int becStatus)
    {
        this.campaignID = id;
        this.campaignName = name;
        this.campaignTotalVouchersNumber = budget;
        this.campaignAvailableVouchersNumber = this.campaignTotalVouchersNumber;
        this.campaignStrategyType = strategy_type;
        
        if(becStatus == 0)
            this.campaignStatus = this.campaignStatus.NEW;
        else if(becStatus == 1)
            this.campaignStatus = this.campaignStatus.STARTED;
        else if(becStatus == 2)
            this.campaignStatus = this.campaignStatus.EXPIRED;
    }
    
}
